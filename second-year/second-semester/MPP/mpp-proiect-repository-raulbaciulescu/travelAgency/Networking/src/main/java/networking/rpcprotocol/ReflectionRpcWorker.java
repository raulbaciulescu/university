package networking.rpcprotocol;

import domain.Flight;
import domain.Purchase;
import domain.User;
import domain.dto.DtoUtils;
import domain.dto.UserDto;
import services.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

public class ReflectionRpcWorker implements Runnable, Observer {
    private Service server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;
    private static final Response okResponse = new Response.Builder().type(ResponseType.OK).build();

    public ReflectionRpcWorker(Service server, Socket connection) {
        this.server = server;
        this.connection = connection;

        try{
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFlight(Flight flight) {
        Response resp = new Response.Builder().type(ResponseType.FLIGHT).data(flight).build();
        System.out.println("notify users in worker " + flight);
        sendResponse(resp);
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
            System.out.println("Error "+e);
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
        System.out.println("Login request ..."+request.type());
        User user = (User) request.data();
        try {
            server.login(user, this);
            return okResponse;
        } catch (LoginException e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleLOGOUT(Request request){
        System.out.println("Logout request...");
        User user = (User)request.data();
        try {
            server.logout(user, this);
            connected = false;
            return okResponse;
        } catch (LoginException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_FLIGHTS(Request request){
        System.out.println("Get flights ..." + request.type());
        try {
            List<Flight> flightList = server.getFlights();
            return new Response.Builder().type(ResponseType.GET_FLIGHTS).data(flightList).build();
        } catch (FlightException e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handlePURCHASE(Request request) {
        System.out.println("handle Purchase ..." + request.type());
        try {
            server.purchase((Purchase) request.data());
            //return new Response.Builder().type(ResponseType.FLIGHT).data(flightDto).build();
            return okResponse;
        } catch (PurchaseException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }
}
