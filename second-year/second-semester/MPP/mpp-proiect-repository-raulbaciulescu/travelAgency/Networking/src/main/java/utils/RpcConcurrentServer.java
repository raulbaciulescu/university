package utils;


import networking.rpcprotocol.ReflectionRpcWorker;
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
        ReflectionRpcWorker worker = new ReflectionRpcWorker(server, socket);
        return new Thread(worker);
    }

    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}
