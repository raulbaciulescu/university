using System.IO.Compression;
using System.Net.Sockets;
using System.Text.Json;
using model;
using networking;
using persistance;
using persistance.Database;
using services;
namespace server;

public class StartServer
{
    private static void Test()
    {
        User user = new User("nume", "parola");
        string stringg = JsonSerializer.Serialize(user);
        Console.Write(stringg);

        User user2 = (User) JsonSerializer.Deserialize(stringg, user.GetType());
        Console.Write(user2);
    }
    static void Main(string[] args)
    {
        Test();
        TableFactory factory = new TableFactory();
        UserRepository userRepository = new UserRepository(factory);
        LocationRepository locationRepository = new LocationRepository(factory);
        FlightRepository flightRepository = new FlightRepository(locationRepository, factory);
        PurchaseRepository purchaseRepository = new PurchaseRepository(factory, flightRepository);
        
        IService service = new ServiceImpl(userRepository, flightRepository, purchaseRepository);
        SerialChatServer server = new SerialChatServer("127.0.0.1", 54321, service);
        server.Start();
        Console.WriteLine("Server started ...");
        //Console.WriteLine("Press <enter> to exit...");
        Console.ReadLine();
    }
}
public class SerialChatServer: ConcurrentServer 
{
    private IService server;
    private Worker worker;
    public SerialChatServer(string host, int port, IService server) : base(host, port)
    {
        this.server = server;
        Console.WriteLine("SerialChatServer...");
    }
    protected override Thread CreateWorker(TcpClient client)
    {
        worker = new Worker(server, client);
        return new Thread(worker.Run);
    }
}