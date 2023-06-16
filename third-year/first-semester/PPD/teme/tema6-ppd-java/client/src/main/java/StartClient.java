import utils.api.Service;
import utils.networking.ServiceRpcProxy;


public class StartClient {
    private static final int defaultPort = 55555;
    private static final String defaultServer = "localhost";

    public static void main(String[] args) {
        Service server = new ServiceRpcProxy(defaultServer, defaultPort);
    }
}
