import server.AbstractServer;
import server.RpcConcurrentServer;
import utils.api.Service;


import java.rmi.ServerException;

public class StartServer {
    private static final int port = 55555;

    public static void main(String[] args) {
        Service service = null;
        System.out.println("Starting server on port: " + port);
        AbstractServer server = new RpcConcurrentServer(port, service);

        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        } finally {
            stopServer(server);
        }
    }

    private static void stopServer(AbstractServer server) {
        try {
            server.stop();
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }
}
