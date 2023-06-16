using grpc2;
using model;
using services;

namespace client;

public class Controller : IObserver
{
    public event EventHandler<FlightEventArgs> updateEvent; //ctrl calls it when it has received an update
    private GrpcClient server;
    private User currentUser;
    
    public Controller(GrpcClient server)
    {
        this.server = server;
        currentUser = null;
    }
    
    public void OnUserEvent(FlightEventArgs eventArgs)
    {
        if (updateEvent == null) return;
        Console.WriteLine("Update Event before call");
        updateEvent(this, eventArgs);
        Console.WriteLine("Update Event called");
    }
    public void Login(string username, string password)
    {
        User user = new User(username, password);
        server.Login(user,this);
        Console.WriteLine("Login succeeded ....");
        currentUser = user;
        Console.WriteLine("Current user {0}", user);
    }

    public void Logout()
    {
        Console.WriteLine("controller logout");
        server.Logout(currentUser);
        currentUser = null; 
    }

    public async Task<List<Flight>> GetFlights()
    {
        
        return await server.GetFlights();
    }

    public void Purchase(Flight flight, string clientName, string clientAddress, List<string> tourists, int nrOfSeats)
    {
        Purchase purchase = new Purchase(flight, clientName, clientAddress, tourists, nrOfSeats);
        server.Purchase(purchase);
    }

    public void UpdateFlight(Flight flight)
    {
        OnUserEvent(new FlightEventArgs(flight));
    }
}