package server;


import utils.api.Service;
import utils.networking.Request;
import utils.networking.Response;
import utils.networking.ResponseType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ReflectionRpcWorker implements Runnable {
    private final Service server;
    private final Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private final boolean connected;
    private static final Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    public ReflectionRpcWorker(Service server, Socket connection) {
        this.server = server;
        this.connection = connection;
        connected = true;
        openConnection();
    }

    @Override
    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        closeConnection();
    }

    private void openConnection() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
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
            response = (Response) method.invoke(this, request);
            System.out.println("Method " + handlerName + " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }
}
