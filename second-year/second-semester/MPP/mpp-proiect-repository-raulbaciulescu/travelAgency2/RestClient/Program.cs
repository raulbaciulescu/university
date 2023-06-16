// See https://aka.ms/new-console-template for more information

using System.Net.Http.Headers;
using persistance;
using persistance.Database;

// using model;

public class MainClass
{
    private static HttpClient _client = new();
    
    static void Main(string[] args)
    {
        Console.WriteLine("Hello World!");
        RunAsync().Wait();
    }
    
    static async Task RunAsync()
    {
        _client.BaseAddress = new Uri("http://localhost:8080/flight");
        _client.DefaultRequestHeaders.Accept.Clear();
        //client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("text/plain"));
        _client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        // Get the string
        //String text = await GetTextAsync("http://localhost:8080/chat/greeting");
        //Console.WriteLine("am obtinut {0}", text);
        //Get one user
        
        String id = "1";
        Flight? flight = await GetFlightAsync("http://localhost:8080/flight/" + id);
        Console.WriteLine("find by id: " + flight);

        Flight[] flights = await GetAllAsync("http://localhost:8080/flight");
        Console.WriteLine("get all: ");
        foreach (var f in flights)
        {
            Console.WriteLine(f);
        }

        Flight flightUpdate = flight;
        flightUpdate.nrOfSeats = 3000;
        string response = await UpdateFlightAsync("http://localhost:8080/flight/" + id, flightUpdate);
        Console.WriteLine("update: " + response);
        
        flight.id = 1000L;
        Flight? flight4 = await AddFlightAsync("http://localhost:8080/flight/", flight);
        Console.WriteLine("add: " + flight4);

        response = await DeleteFlightAsync("http://localhost:8080/flight/" + 1000L);
        Console.WriteLine("delete: " + response);
    }
    
    static async Task<Flight> AddFlightAsync(string path, Flight flight1)
    {
        Flight flight = null;
        HttpResponseMessage response = await _client.PostAsJsonAsync(path, flight1);
        if (response.IsSuccessStatusCode)
        {
            flight = await response.Content.ReadAsAsync<Flight>();
        }
        return flight;
    }
    
    static async Task<string> UpdateFlightAsync(string path, Flight flight)
    {
        HttpResponseMessage response = await _client.PutAsJsonAsync(path, flight);
        if (response.IsSuccessStatusCode)
        {
            return "success";
        }
        return "error";
    }
    
    static async Task<string> DeleteFlightAsync(string path)
    {
        HttpResponseMessage response = await _client.DeleteAsync(path);
        if (response.IsSuccessStatusCode)
        {
            return "success";
        }
        return "error";
    }
    
    static async Task<Flight[]> GetAllAsync(string path)
    {
        Flight[] flights = null;
        HttpResponseMessage response = await _client.GetAsync(path);
        if (response.IsSuccessStatusCode)
        {
            flights = await response.Content.ReadAsAsync<Flight[]>();
        }
        return flights;
    }
    static async Task<Flight?> GetFlightAsync(string path)
    {
        Flight? flight = null;
        HttpResponseMessage response = await _client.GetAsync(path);
        if (response.IsSuccessStatusCode)
        {
            flight = await response.Content.ReadAsAsync<Flight>();
        }
        return flight;
    }
    public class Entity<ID>
    {
        public ID Id { get; set; }

        public Entity(ID id)
        {
            Id = id;
        }

    }
    public class Flight
    {
        public long id { get; set; }
        public Location start { get; set; }
        public Location destination { get; set; }
        public DateTime startDate { get; set; }
        public int nrOfSeats { get; set; }

        
        public Flight()
        {
            
        }
        
        public Flight(long id, Location start, Location destination, DateTime startDate, int nrOfSeats)
        {
            this.start = start;
            this.destination = destination;
            this.startDate = startDate;
            this.nrOfSeats = nrOfSeats;
        }

        public override string ToString()
        {
            return id + " " + start + " " + destination + " " + startDate + " " + nrOfSeats;
        }
    }
    public class Location
    {
        public long id { get; set; }
        public string name { get; set; }
        public string airport { get; set; }

        public Location(long id, string name, string airport)
        {
            this.id = id;
            this.name = name;
            this.airport = airport;
        }
        
        public Location()
        {
            
        }

        public override string ToString()
        {
            //base.ToString() + ": " +
            return name + " " + airport;
        }
    }
}
