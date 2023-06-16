using model;

namespace client;

public class FlightEventArgs : EventArgs
{
    public Flight data { set; get; }

    public FlightEventArgs(object data)
    {
        this.data = (Flight) data;
    }
}