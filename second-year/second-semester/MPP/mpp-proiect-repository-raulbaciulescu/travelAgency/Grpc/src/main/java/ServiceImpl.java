import api.FlightRepository;
import api.PurchaseRepository;
import api.UserRepository;
import com.grpc.Empty;
import com.grpc.FlightServiceGrpc;
import com.grpc.Request;
import com.grpc.Response;
import domain.Flight;
import domain.Purchase;
import domain.User;
import io.grpc.Context;
import io.grpc.ServerCall;
import io.grpc.stub.StreamObserver;
import services.Observer;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl extends FlightServiceGrpc.FlightServiceImplBase {
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PurchaseRepository purchaseRepository;
    private final LinkedHashSet<StreamObserver<Response>> observers;
    private final Random random;

    public ServiceImpl(UserRepository userRepository, FlightRepository flightRepository, PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.purchaseRepository = purchaseRepository;
        observers = new LinkedHashSet<>();
        random = new Random();
    }

    @Override
    public void login(Request request, StreamObserver<Response> responseObserver) {
        System.out.println("--login server");
        String username = request.getUser().getUsername();
        String password = request.getUser().getPassword();
        Optional<User> userOptional = userRepository.findUser(username, password);

        Response.Builder response = Response.newBuilder();
        if (userOptional.isPresent()) {
            response.setType(Response.Type.Ok);
        }
        else {
            response.setType(Response.Type.Error);
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        System.out.println("--login server dupa onCompleted");
    }

    @Override
    public void logout(Request request, StreamObserver<Response> responseObserver) {
        System.out.println("--logout server");
        Response.Builder response = Response.newBuilder();
        response.setType(Response.Type.Ok);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        System.out.println("--logout server dupa onCompleted");
    }

    @Override
    public void purchase(Request request, StreamObserver<Response> responseObserver) {
        System.out.println("inceput la purchase");
        Response.Builder response = Response.newBuilder();
        com.grpc.Purchase purchase = request.getPurchase();
        try {
            long idd = purchase.getFlightId();
            Optional<Flight> flightOptional = flightRepository.findByID(purchase.getFlightId());
            long id = random.nextLong();
            if (flightOptional.isPresent()) {
                Purchase purchaseModel = new Purchase(flightOptional.get(), purchase.getClientName(),
                        purchase.getClientAddress(), purchase.getNrOfSeats());
                purchaseModel.setId(id);
                Flight flight = purchaseModel.getFlight();
                if (purchaseModel.getFlight().getNrOfSeats() >= purchaseModel.getNrOfSeats()) {

                    Flight flightNew = new Flight(flight.getStart(), flight.getDestination(), flight.getStartDate(),
                            flight.getNrOfSeats() - purchaseModel.getNrOfSeats());
                    flightNew.setId(flight.getId());
                    flightRepository.update(flight, flightNew);
                    purchaseRepository.add(purchaseModel);
                    notifyUsers(flightNew);
                    response.setType(Response.Type.Ok);
                }
                else
                    response.setType(Response.Type.Error);
            }
            else response.setType(Response.Type.Error);
        } catch (SQLException e) {
            System.out.println("I don't find any flight!");
            response.setType(Response.Type.Error);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
        System.out.println("final la purchase");
    }

    @Override
    public void getFlights(Request request, StreamObserver<Response> responseObserver) {
        System.out.println("-----get flights");
        List<Flight> flightList = flightRepository.getAll();
        Response.Builder response = Response.newBuilder();

        for (Flight flight: flightList) {
            com.grpc.Flight flight1 = com.grpc.Flight.newBuilder()
                    .setId(flight.getId())
                    .setStart(flight.getStart().toString())
                    .setDestination(flight.getDestination().toString())
                    .setStartDate(flight.getStartDate().toString())
                    .setNrOfSeats(flight.getNrOfSeats()).build();
            response.setType(Response.Type.GetFlights).setFlight(flight1).build();
            responseObserver.onNext(response.build());
        }
        responseObserver.onCompleted();
        System.out.println("-----get flights on completed");
    }


    private void notifyUsers(Flight flight) {
        System.out.println("inceput la notify users");
        Response.Builder response = Response.newBuilder();
        for (StreamObserver<Response> observer: observers) {
            com.grpc.Flight flight1 = com.grpc.Flight.newBuilder()
                    .setId(flight.getId())
                    .setStart(flight.getStart().toString())
                    .setDestination(flight.getDestination().toString())
                    .setStartDate(flight.getStartDate().toString())
                    .setNrOfSeats(flight.getNrOfSeats()).build();
            response.setType(Response.Type.Flight).setUpdateFlight(flight1);
            observer.onNext(response.build());
        }
        System.out.println("sfarsit la notify users");
    }



    @Override
    public StreamObserver<Empty> addObserver(StreamObserver<Response> responseObserver) {
        observers.add(responseObserver);
        return new StreamObserver<>() {
            @Override
            public void onNext(Empty value) {}

            @Override
            public void onError(Throwable t) {
                observers.remove(responseObserver);
            }

            @Override
            public void onCompleted() {
                observers.remove(responseObserver);
            }
        };
    }
}
