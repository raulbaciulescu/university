using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text.Json;
using model;
using services;
using travelAgency2.Service;

namespace networking
{
    public class Proxy : IService
    {
        private string host;
        private int port;

        private IObserver client;

        private NetworkStream stream;
		
        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle _waitHandle;
        
        public Proxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses = new Queue<Response>();
        }
        
        private void InitializeConnection() {
            try
            {
                connection = new TcpClient(host,port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                _waitHandle = new AutoResetEvent(false);
                StartReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }
        private void StartReader()
        {
            Thread tw = new Thread(Run);
            tw.Start();
        }
        
        private bool IsUpdate(Response response){
            return response.type == ResponseType.FLIGHT;
        }
        
        private void HandleUpdate(Response response) {
            if (response.type == ResponseType.FLIGHT) {
                Console.WriteLine("handle update in proxy");
                Flight flight = (Flight) response.data;
                client.UpdateFlight(flight);
            }
        }
        public virtual void Run()
        {
            while(!finished)
            {
                try
                {
                   // JsonSerializer serializer = new JsonSerializer();
                    Response response = (Response) formatter.Deserialize(stream);
                    Console.WriteLine("response received " + response);
                    if (IsUpdate(response))
                    {
                        HandleUpdate(response);
                    }
                    else
                    {
                        lock (responses)
                        {
                            responses.Enqueue((Response) response);
                        }
                        _waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error " + e);
                }
            }
        }
        private void SendRequest(Request request)
        {
            try
            {
                formatter.Serialize(stream, request);
                stream.Flush();
            }
            catch (Exception e)
            {
                throw new Exception("Error sending object " + e);
            }
        }
        private Response ReadResponse()
        {
            Response response = null;
            try
            {
                _waitHandle.WaitOne();
                lock (responses)
                {
                    //Monitor.Wait(responses); 
                    response = responses.Dequeue();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            return response;
        }
        private void CloseConnection()
        {
            finished=true;
            try
            {
                stream.Close();
                connection.Close();
                _waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }
        public void Login(User user, IObserver client)
        {
            InitializeConnection();
            Request request = new Request.Builder().Type(RequestType.LOGIN).Data(user).Build();
            SendRequest(request);

            Response response = ReadResponse();
            if (response.type == ResponseType.OK)
            {
                Console.Write("raspuns la login de ok!\n");
                this.client = client;
                return;
            }
            if (response.type == ResponseType.ERROR)
            {
                CloseConnection();
                throw new LoginException(response.data.ToString());
            }
        }

        public void Purchase(Purchase purchase)
        {
            Request request = new Request.Builder().Type(RequestType.PURCHASE).Data(purchase).Build();
            SendRequest(request);
            Response response = ReadResponse();
            if (response.type == ResponseType.ERROR){
                throw new PurchaseException(response.data.ToString());
            }
        }

        public void Logout(User user, IObserver client)
        {
            Request request = new Request.Builder().Type(RequestType.LOGOUT).Data(user).Build();
            SendRequest(request);
            Response response = ReadResponse();
            if (response.type == ResponseType.ERROR){
                throw new LoginException("error in logout proxy");
            }
            CloseConnection();
        }

        public List<Flight> GetFlights()
        {
            Request request = new Request.Builder().Type(RequestType.GET_FLIGHTS).Build();
            SendRequest(request);

            Response response = ReadResponse();
            if (response.type == ResponseType.ERROR) {
                CloseConnection();
                throw new FlightException("getFlights error");
            }
            List<Flight> flights = (List<Flight>) response.data;
            return flights;
        }
    }    
}
