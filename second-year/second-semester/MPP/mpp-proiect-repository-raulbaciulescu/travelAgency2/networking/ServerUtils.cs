﻿using System.Net;
using System.Net.Sockets;

namespace networking
{ 
    public abstract class AbstractServer
    {
        private TcpListener server;
        private String host;
        private int port;
        public AbstractServer(String host, int port)
        {
            this.host = host;
            this.port = port;
        }
        public void Start()
        {
            IPAddress adr = IPAddress.Parse(host);
            IPEndPoint ep=new IPEndPoint(adr,port);
            server=new TcpListener(ep);
            server.Start();
            while (true)
            {
                Console.WriteLine("Waiting for clients ...");
                TcpClient client = server.AcceptTcpClient();
                Console.WriteLine("Client connected ...");
                ProcessRequest(client);
            }
        }

        public abstract void ProcessRequest(TcpClient client);
        
    }

    
        public abstract class ConcurrentServer:AbstractServer
        {
            
            public ConcurrentServer(string host, int port) : base(host, port)
            {}

            public override void ProcessRequest(TcpClient client)
            {
                Thread t = CreateWorker(client);
                t.Start();
            }

            protected abstract  Thread CreateWorker(TcpClient client);
            
        }
   
}
