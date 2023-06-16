namespace services;
using model;

public interface IObserver
{
    void UpdateFlight(Flight flight);
}