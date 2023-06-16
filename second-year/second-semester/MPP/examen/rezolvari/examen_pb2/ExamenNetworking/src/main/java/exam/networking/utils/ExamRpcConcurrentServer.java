package exam.networking.utils;





import exam.networking.rpcprotocol.ExamClientRpcWorker;
import exam.services.IExamServices;

import java.net.Socket;

public class ExamRpcConcurrentServer extends AbsConcurrentServer {
    private IExamServices server;
    public ExamRpcConcurrentServer(int port, IExamServices modelServer) {
        super(port);
        this.server = modelServer;
        System.out.println("Model-ModelRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        // ChatClientRpcWorker worker=new ChatClientRpcWorker(chatServer, client);
        ExamClientRpcWorker worker=new ExamClientRpcWorker(server, client);

        Thread tw=new Thread(worker);
        return tw;
    }

    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}
