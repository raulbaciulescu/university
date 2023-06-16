


using System.Windows.Forms;
using client;
using com.grpc;
using Grpc.Net.Client;
using grpc2;

public class StartClient
{
    static async Task Main(string[] args)
    {

        GrpcClient grpcClient = new GrpcClient();
        Controller controller = new Controller(grpcClient);
        LoginForm loginForm = new LoginForm(controller);
        Application.Run(loginForm);
        
        Console.WriteLine("Press any key to exit...");
        Console.ReadKey();
    }

    
}