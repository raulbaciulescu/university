package networking.rpcprotocol;

import domain.Flight;
import domain.Purchase;
import domain.User;
import domain.dto.DtoUtils;
import domain.dto.PurchaseDto;
import domain.dto.UserDto;
import services.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceRpcProxy implements Service {
    private String host;
    private int port;
    private Observer client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ServiceRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<>();
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
    public void login(User user, Observer client) throws LoginException {
        initializeConnection();
        //UserDto userDto = DtoUtils.getDTO(user);
        Request request = new Request.Builder().type(RequestType.LOGIN).data(user).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.OK){
            this.client = client;
            return;
        }
        if (response.type() == ResponseType.ERROR){
            closeConnection();
            throw new LoginException(response.data().toString());
        }
    }

    @Override
    public List<Flight> getFlights() throws FlightException {
        Request request = new Request.Builder().type(RequestType.GET_FLIGHTS).build();
        sendRequest(request);

        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            closeConnection();
            throw new FlightException("getFlights error");
        }
        List<Flight> flights = (List<Flight>) response.data();
        return flights;
    }

    private Response readResponse() {
        Response response = null;
        try {
            response = qresponses.take();
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
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void purchase(Purchase purchase) throws PurchaseException {
        Request request = new Request.Builder().type(RequestType.PURCHASE).data(purchase).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR){
            throw new PurchaseException(response.data().toString());
        }
    }

    @Override
    public void logout(User user, Observer client) throws LoginException {
        Request request = new Request.Builder().type(RequestType.LOGOUT).data(user).build();
        sendRequest(request);
        Response response=readResponse();
        if (response.type() == ResponseType.ERROR){
            throw new LoginException( response.data().toString());
        }
        closeConnection();
    }



    private boolean isUpdate(Response response){
        return response.type()== ResponseType.FLIGHT;
    }

    private void handleUpdate(Response response) {
        if (response.type() == ResponseType.FLIGHT) {
            System.out.println("handle update in proxy");
            Flight flight = (Flight) response.data();
            client.updateFlight(flight);
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
                            qresponses.put((Response) response);
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
