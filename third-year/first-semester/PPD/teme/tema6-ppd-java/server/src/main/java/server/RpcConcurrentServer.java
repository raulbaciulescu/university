package server;


import utils.api.Service;

import java.net.Socket;


public class RpcConcurrentServer extends AbsConcurrentServer {
    private final Service server;

    public RpcConcurrentServer(int port, Service server) {
        super(port);
        this.server = server;
        System.out.println("ConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket socket) {
        ReflectionRpcWorker worker = new ReflectionRpcWorker(server, socket);
        return new Thread(worker);
    }

    @Override
    public void stop() {
        System.out.println("Stopping services ...");
    }
}
