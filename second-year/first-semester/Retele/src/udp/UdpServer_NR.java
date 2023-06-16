package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer_NR {
    private final DatagramSocket datagramSocket;
    private final byte[] buffer = new byte[300];

    public UdpServer_NR(DatagramSocket var1) {
        this.datagramSocket = var1;
    }

    public void run() throws IOException {
        while(true) {
            this.receive_then_send();
        }
    }

    private void receive_then_send() throws IOException {
        DatagramPacket var1 = new DatagramPacket(this.buffer, 0, this.buffer.length);
        this.datagramSocket.receive(var1);
        InetAddress var2 = var1.getAddress();
        int var3 = var1.getPort();
        String var4 = new String(var1.getData(), 0, var1.getLength());
        System.out.println("Messaje from client " + var2 + ": " + var4);
        var1 = new DatagramPacket(this.buffer, this.buffer.length, var2, var3);
        this.datagramSocket.send(var1);
    }

    public static void main(String[] var0) throws SocketException {
        DatagramSocket var1 = new DatagramSocket(2345);
        UdpServer_NR var2 = new UdpServer_NR(var1);

        try {
            var2.run();
        } catch (IOException var4) {
            System.out.println(var4.getMessage());
        }

    }
}
