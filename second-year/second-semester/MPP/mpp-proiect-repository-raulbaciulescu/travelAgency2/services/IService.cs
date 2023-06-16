using model;

namespace services;

public interface IService
{
    void Login(User user, IObserver client);
    void Purchase(Purchase purchase);
    void Logout(User user, IObserver client);
    List<Flight> GetFlights();
}