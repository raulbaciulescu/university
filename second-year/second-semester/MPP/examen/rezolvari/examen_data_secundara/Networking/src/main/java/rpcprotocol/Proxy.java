package rpcprotocol;

import domain.*;
import services.LoginException;
import services.Observer;
import services.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
    public void login(Player player, Observer client) throws LoginException {
        initializeConnection();
        Request request = new Request.Builder().type(RequestType.LOGIN).data(player).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.OK) {
            this.client = client;
            return;
        }
        if (response.type() == ResponseType.ERROR) {
            closeConnection();
            throw new LoginException(response.data().toString());
        }
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
    public GameDTO startGame(String alias) {
        Request request = new Request.Builder().type(RequestType.START_GAME).data(alias).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            System.out.println("Error start game proxy");
            closeConnection();
        }
        return (GameDTO) response.data();
    }

    @Override
    //public void updateGame(UpdateObject updateObject) {
    public void updateGame(Game game) {
        Request request = new Request.Builder().type(RequestType.UPDATE_GAME).data(game).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            System.out.println("Error start game proxy");
            closeConnection();
        }
    }

    @Override
    public List<Game> getFinishedGames() {
        List<Game> games = new ArrayList<>();
        Request request = new Request.Builder().type(RequestType.GET_FINISHED_GAMES).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.OK) {
            games = (List<Game>) response.data();
        } else
        if (response.type() == ResponseType.ERROR) {
            System.out.println("Error start game proxy");
            closeConnection();
        }
        return games;
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
        return response.type()== ResponseType.UPDATE_RESULTS;
    }

    private void handleUpdate(Response response) {
        System.out.println("handle update in proxy");
        List<Game> games = (List<Game>) response.data();
        client.updateResults(games);
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
