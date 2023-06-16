package udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;
    private byte[] buffer= new byte [300];

    public UdpClient(DatagramSocket datagramSocket,InetAddress inetAddress) {
        this.datagramSocket=datagramSocket;
        this.inetAddress=inetAddress;
    }

    public void run() throws IOException{
        while (true) {
            send_then_receive();
        }
    }
    private void send_then_receive()throws IOException{
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        buffer= message.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length,inetAddress,2345);
        datagramSocket.send(datagramPacket);
        datagramSocket.receive(datagramPacket);
        message = new String(datagramPacket.getData(),0,datagramPacket.getLength());
        System.out.println("From sever: " + message);
    }

    public static void main(String[] args) throws IOException{
        DatagramSocket datagramSocket= new DatagramSocket();
        InetAddress inetAddress= InetAddress.getByName("localhost");
        UdpClient client = new UdpClient(datagramSocket,inetAddress);
        client.run();
    }

}
