using System.Runtime.CompilerServices;
using client;
using com.grpc;
using Grpc.Core;
using Grpc.Net.Client;
using model;
using model.Dto;
using services;
using FlightGrpc = com.grpc.Flight;
using FlightModel = model.Flight;
using Purchase = model.Purchase;
using UserModel = model.User;
using UserGrpc = com.grpc.User;
using PurchaseGrpc = com.grpc.Purchase;

namespace grpc2;

public class GrpcClient : IObserver
{
    private static GrpcChannel channel;
    private FlightService.FlightServiceClient client;
    private IObserver observer;
    public GrpcClient()
    {
        AppContext.SetSwitch("System.Net.Http.SocketsHttpHandler.Http2UnencryptedSupport", true);
        channel = GrpcChannel.ForAddress("http://localhost:9999");
        client = new FlightService.FlightServiceClient(channel);
    }

    private void Run(IObserver observer)
    {
        var streamingCall = client.addObserver();
        Task.Run(() => UpdateFlight(streamingCall, observer));
    }

    private async Task UpdateFlight(AsyncDuplexStreamingCall<Empty, Response> streamingCall, IObserver observer)
    {
        while (await streamingCall.ResponseStream.MoveNext(CancellationToken.None))
        {
            FlightGrpc flightGrpc = streamingCall.ResponseStream.Current.UpdateFlight;
            FlightModel flight = new FlightModel(flightGrpc.Id, new Location(flightGrpc.Start),
                new Location(flightGrpc.Destination), Convert.ToDateTime(flightGrpc.StartDate), flightGrpc.NrOfSeats);
            observer.UpdateFlight(flight);
        }
    }

    public void Login(UserModel user, IObserver observer)
    {
        string username = user.username;
        string password = user.password;
        var userProto = new UserGrpc { Username = username, Password = password };
        
        Request request = new Request { Type = Request.Types.Type.Login, User = userProto };
        Response response = client.login(request);
        
        if (response.Type == Response.Types.Type.Ok)
            Console.WriteLine("Logare cu succes!");
        this.observer = observer;
        Run(observer);
    }

    public async Task<List<FlightModel>> GetFlights()
    {
        Console.Write("get flights");
        List<FlightModel> flights = new List<FlightModel>();
        Request request = new Request {Type = Request.Types.Type.GetFlights};
        AsyncServerStreamingCall<Response> streamingCall = client.getFlights(request);
        await foreach (var response in streamingCall.ResponseStream.ReadAllAsync())
        {
            FlightGrpc flightGrpc = response.Flight;
            FlightModel flight = new FlightModel(flightGrpc.Id, new Location(flightGrpc.Start),
                new Location(flightGrpc.Destination), Convert.ToDateTime(flightGrpc.StartDate), flightGrpc.NrOfSeats);
            flights.Add(flight);
        }
        return flights;
    }

    public void Purchase(Purchase purchase)
    {
        var purchaseGrpc = new PurchaseGrpc
        {
            FlightId = purchase.flight.Id, ClientName = purchase.clientName,
            ClientAddress = purchase.clientAddress, NrOfSeats = purchase.nrOfSeats
        };
        
        Request request = new Request { Type = Request.Types.Type.Purchase, Purchase = purchaseGrpc };
        Response response = client.purchase(request);
        
        if (response.Type == Response.Types.Type.Ok)
            Console.WriteLine("Cumparare cu succes!");
    }

    public void Logout(UserModel currentUser)
    {
        string username = currentUser.username;
        string password = currentUser.password;
        var userProto = new UserGrpc { Username = username, Password = password };
        
        Request request = new Request { Type = Request.Types.Type.Logout, User = userProto };
        Response response = client.logout(request);
        
        if (response.Type == Response.Types.Type.Ok)
            Console.WriteLine("Logout cu succes!");
    }

    public void UpdateFlight(FlightModel flight)
    {
        throw new NotImplementedException();
    }
}