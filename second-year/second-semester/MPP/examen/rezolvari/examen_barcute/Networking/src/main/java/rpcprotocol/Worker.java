package rpcprotocol;

import domain.Game;
import domain.GameDTO;
import domain.Player;
import domain.Proposal;
import services.LoginException;
import services.Observer;
import services.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

public class Worker implements Runnable, Observer {
    private Service server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;
    private static final Response okResponse = new Response.Builder().type(ResponseType.OK).build();


    public Worker(Service server, Socket connection) {
        this.server = server;
        this.connection = connection;

        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response!=null){
                    sendResponse(response);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private void sendResponse(Response response) {
        try {
            System.out.println("sending response " + response);
            output.writeObject(response);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = null;
        String handlerName = "handle" + (request).type();
        System.out.println("handler name: " + handlerName);
        try {
            Method method = this.getClass().getDeclaredMethod(handlerName, Request.class);
            response = (Response)method.invoke(this, request);
            System.out.println("Method "+handlerName+ " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Response handleLOGIN(Request request){
        System.out.println("Login request ..." + request.type());
        Player player = (Player) request.data();
        try {
            server.login(player, this);
            return okResponse;
        } catch (LoginException e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleLOGOUT(Request request){
        System.out.println("Logout request...");
        Player player = (Player) request.data();
        try {
            server.logout(player, this);
            connected = false;
            return okResponse;
        } catch (LoginException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleSTART_GAME(Request request){
        System.out.println("start game request ..." + request.type());
        Game game = (Game) request.data();
        try {
            server.startGame(game, this);
            return okResponse;
        } catch (Exception e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }


//    private Response handleSTART_GAME(Request request) {
//        System.out.println("Start Game request...");
//        ProposedWord proposedWord = (ProposedWord) request.data();
//
//        try {
//            server.startGame(proposedWord, this);
//            return okResponse;
//        } catch (Exception e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }
//
//    private Response handleMOVE(Request request) {
//        System.out.println("Move request...");
//        Proposal proposal = (Proposal) request.data();
//
//        try {
//            server.move(proposal);
//            return okResponse;
//        } catch (Exception e) {
//            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
//        }
//    }

    @Override
    public void startGameUpdate(GameDTO gameDTO) {
        Response resp = new Response.Builder().type(ResponseType.START_GAME).data(gameDTO).build();
        sendResponse(resp);
    }
}
