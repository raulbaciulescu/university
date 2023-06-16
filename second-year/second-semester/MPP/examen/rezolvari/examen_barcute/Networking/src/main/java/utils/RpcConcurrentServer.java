package utils;


import rpcprotocol.Worker;
import services.Service;

import java.net.Socket;


public class RpcConcurrentServer extends AbsConcurrentServer {
    private final Service server;

    public RpcConcurrentServer(int port, Service server) {
        super(port);
        this.server = server;
        System.out.println("Game- RpcConcurrentServer");
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
