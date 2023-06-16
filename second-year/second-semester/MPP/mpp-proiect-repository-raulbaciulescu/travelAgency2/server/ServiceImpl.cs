using model;
using persistance;
using services;
using System.Collections.Generic;
namespace server;

public class ServiceImpl : IService
{
    private UserRepository userRepository;
    private FlightRepository flightRepository;
    private PurchaseRepository purchaseRepository;
    private readonly IDictionary<long, IObserver> loggedUsers;
    private Random random;
    
    public ServiceImpl(UserRepository userRepository, FlightRepository flightRepository, PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.purchaseRepository = purchaseRepository;
        loggedUsers = new Dictionary<long, IObserver>();
        random = new Random();
    }
    
    public void Login(User user, IObserver client)
    {
        User user2 = userRepository.FindUser(user.username, user.password);
        if (user2 != null) 
        {
            if(loggedUsers.ContainsKey(user2.Id))
                throw new LoginException("User already logged in.");
            loggedUsers[user2.Id] = client;
        }
        else
            throw new LoginException("Authentication failed!");
    }

    public void Purchase(Purchase purchase)
    {
        Flight flight = purchase.flight;
        try {
            flight = flightRepository.FindByID(purchase.flight.Id);
        } catch (Exception e) {
            Console.Write(e.Message);
        }
        if (flight.nrOfSeats < purchase.nrOfSeats) {
            throw new PurchaseException("not enough number of seats!");
        }
        Flight flightNew = new Flight(flight.Id, flight.start, flight.destination, flight.startDate, flight.nrOfSeats - purchase.nrOfSeats);
        flightRepository.Update(flight, flightNew);
        long id = random.Next(0, 999999999);
        Purchase purchase2 = new Purchase(id, flight, purchase.clientName, purchase.clientAddress, purchase.tourists, purchase.nrOfSeats);
        purchaseRepository.Add(purchase2);
        NotifyUsers(flightNew);
    }

    private void NotifyUsers(Flight flight) {
        foreach (KeyValuePair<long, IObserver> entry in loggedUsers) {
            Task.Run(() =>  entry.Value.UpdateFlight(flight));
        }
    }
    
    public void Logout(User user, IObserver client)
    {
        bool boolean = loggedUsers.Remove(userRepository.FindUser(user.username, user.password).Id);
        // if (boolean == false) {
        //     throw new LoginException("User " + user.username + " is not logged in.");
        // }
    }

    public List<Flight> GetFlights()
    {
        return flightRepository.GetAll();
    }
}