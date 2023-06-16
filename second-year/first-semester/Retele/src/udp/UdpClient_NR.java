package udp;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient_NR {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;
    private byte[] buffer = new byte[300];

    public UdpClient_NR(DatagramSocket var1, InetAddress var2) {
        this.datagramSocket = var1;
        this.inetAddress = var2;
    }

    private void run() throws IOException {
        while(true) {
            this.send_then_receive();
        }
    }

    private void send_then_receive() throws IOException {
        Scanner var1 = new Scanner(System.in);
        String var2 = var1.nextLine();
        this.buffer = var2.getBytes();
        DatagramPacket var3 = new DatagramPacket(this.buffer, this.buffer.length, this.inetAddress, 2345);
        this.datagramSocket.send(var3);
        this.datagramSocket.receive(var3);
        var2 = new String(var3.getData(), 0, var3.getLength());
        System.out.println("From sever: " + var2);
    }

    public static void main(String[] var0) throws IOException {
        DatagramSocket var1 = new DatagramSocket();
        InetAddress var2 = InetAddress.getByName("localhost");
        UdpClient var3 = new UdpClient(var1, var2);
        var3.run();
    }
}
