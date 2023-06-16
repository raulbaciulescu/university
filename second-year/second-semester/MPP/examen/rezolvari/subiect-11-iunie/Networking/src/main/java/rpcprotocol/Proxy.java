package rpcprotocol;

import domain.Game;
import domain.Player;
import domain.Proposal;
import domain.ProposedWord;
import org.hibernate.sql.Update;
import services.LoginException;
import services.Observer;
import services.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Proxy implements Service {
    private String host;
    private int port;
    private Observer client;
    private Observer mainCtrl;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> queueResponses;
    private volatile boolean finished;

    public Proxy(String host, int port) {
        this.host = host;
        this.port = port;
        queueResponses = new LinkedBlockingQueue<>();
    }

    private void startReader(){
        Thread thread = new Thread(new ReaderThread());
        thread.start();
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player login(Player player, Observer client) throws LoginException {
        initializeConnection();
        Request request = new Request.Builder().type(RequestType.LOGIN).data(player).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.LOGIN) {
            this.client = client;
            return (Player) response.data();
        } else
        if (response.type() == ResponseType.ERROR) {
            closeConnection();
            throw new LoginException(response.data().toString());
        }
        throw new LoginException(response.data().toString());
    }

    @Override
    public void logout(Player player, Observer client) throws LoginException {
        Request request = new Request.Builder().type(RequestType.LOGOUT).data(player).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            throw new LoginException( response.data().toString());
        }
        closeConnection();
    }


    @Override
    public void startGame(ProposedWord proposedWord, Observer mainCtrl) {
        Request request = new Request.Builder().type(RequestType.START_GAME).data(proposedWord).build();
        sendRequest(request);

        Response response = readResponse();
        this.mainCtrl = mainCtrl;

        if (response.type() == ResponseType.ERROR) {
            System.out.println("Error start game proxy");
            closeConnection();
        }
    }

    @Override
    public void move(Proposal proposal) {
        Request request = new Request.Builder().type(RequestType.MOVE).data(proposal).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            System.out.println("exception in move proxy");
        }
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = queueResponses.take();
        } catch (InterruptedException e) {
            System.out.println("exception readResponse");
        }
        return response;
    }

    private void sendRequest(Request request) {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            System.out.println("exception sendRequest " + e);
        }
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isUpdate(Response response){
        return response.type()== ResponseType.START_GAME;
    }

    private boolean ok = true;
    private void handleUpdate(Response response) {
        if (response.type() == ResponseType.START_GAME && ok) {
            System.out.println("handle update in proxy");
            UpdateObject updateObject = (UpdateObject) response.data();
            client.startGameUpdate(updateObject.getProposedWords(), updateObject.getGame());
            ok = false;
        } else {
            System.out.println("handle update in proxy");
            UpdateObject updateObject = (UpdateObject) response.data();
            Game game = updateObject.getGame();
            game.setScore1(updateObject.getScore1());
            game.setScore2(updateObject.getScore2());
            game.setScore3(updateObject.getScore3());
            System.out.println("notify users in proxy " + " " +
                    updateObject.getScore1() + " " + updateObject.getScore2() + " " + updateObject.getScore3() + " " + game.getName());


            mainCtrl.startGameUpdate(updateObject.getProposedWords(), updateObject.getGame());
            ok = false;
        }
    }

    private class ReaderThread implements Runnable {
        @Override
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("Response received: " + response);
                    if (isUpdate((Response) response))
                        handleUpdate((Response) response);
                    else
                        try {
                            queueResponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                } catch (IOException e) {
                    System.out.println("Reading exception!! " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error!! " + e);
                }
            }
        }
    }
}
