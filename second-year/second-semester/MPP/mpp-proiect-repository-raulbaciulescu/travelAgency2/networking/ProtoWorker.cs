using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using model;
using services;
using System.Reflection;
using travelAgency2.Service;


namespace networking
{
    public class ProtoWorker : IObserver
    {
        
        private IService server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;
        private static Response okResponse = new Response.Builder().Type(ResponseType.OK).Build();
        public ProtoWorker(IService server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private Response HandleRequest(Request request)
        {
            Response response = null;
            string handlerName = "Handle" + (request).type;
            Console.WriteLine("handler name: " + handlerName);
            try
            {
                MethodInfo dynMethod = this.GetType().GetMethod(handlerName, BindingFlags.NonPublic | BindingFlags.Instance);
                response = (Response?) dynMethod.Invoke(this, new object[] { request });
            } catch (Exception e) {
                Console.Write("exception handleRequest!");
            }
            return response;
        }
    
        private Response HandleLOGIN(Request request){
            Console.WriteLine("Login request ..." + request.type);
            User user = (User) request.data;
            try 
            {
                server.Login(user, this);
                return new Response.Builder().Type(ResponseType.OK).Build();
            } catch (LoginException e) {
                connected = false;
                return new Response.Builder().Type(ResponseType.ERROR).Data(e.Message).Build();
            }
        }
        private Response HandleGET_FLIGHTS(Request request){
            Console.WriteLine("Get flights ..." + request.type);
            try {
                List<Flight> flightList = server.GetFlights();
                return new Response.Builder().Type(ResponseType.GET_FLIGHTS).Data(flightList).Build();
            } catch (FlightException e) {
                connected = false;
                return new Response.Builder().Type(ResponseType.ERROR).Data(e.Message).Build();
            }
        }
        
        private Response HandlePURCHASE(Request request) {
            Console.WriteLine("handle Purchase ..." + request.type);
            try {
                server.Purchase((Purchase) request.data);
                return new Response.Builder().Type(ResponseType.OK).Build();
            } catch (PurchaseException e) {
                return new Response.Builder().Type(ResponseType.ERROR).Data(e.Message).Build();
            }
        }
        
        private Response HandleLOGOUT(Request request){
            Console.WriteLine("Logout request...");
            User user = (User)request.data;
            try {
                server.Logout(user, this);
                connected = false;
                return new Response.Builder().Type(ResponseType.OK).Build();
            } catch (LoginException e) {
                return new Response.Builder().Type(ResponseType.ERROR).Data(e.Message).Build();
            }
        }
        public virtual void Run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = HandleRequest((Request) request);
                    if (response != null)
                    {
                        SendResponse((Response) response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
                try
                {
                    Thread.Sleep(200);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
            try
            {
                stream.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error "+e);
            }
        }
        
        private void SendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            lock (stream)
            {
                formatter.Serialize(stream, response);
                stream.Flush();
            }

        }
        
        public void UpdateFlight(Flight flight)
        {
            Response resp = new Response.Builder().Type(ResponseType.FLIGHT).Data(flight).Build();
            Console.WriteLine("notify users in worker " + flight);
            SendResponse(resp);
        }
    }    
}

