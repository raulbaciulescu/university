package utils;


import rpcprotocol.Worker;
import services.Service;

import java.net.Socket;


public class RpcConcurrentServer extends AbsConcurrentServer {
    private Service server;
    public RpcConcurrentServer(int port, Service chatServer) {
        super(port);
        this.server = chatServer;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket socket) {
        Worker worker = new Worker(server, socket);
        return new Thread(worker);
    }

    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}
