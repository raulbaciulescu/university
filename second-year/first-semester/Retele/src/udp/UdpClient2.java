package udp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient2 {
    private final DatagramSocket datagramSocket;
    private final InetAddress inetAddress;
    private byte[] buffer= new byte [300];

    public UdpClient2(DatagramSocket datagramSocket,InetAddress inetAddress) {
        this.datagramSocket=datagramSocket;
        this.inetAddress=inetAddress;
    }

    private void run () throws IOException{
        while (true) {
            send_then_receive();
        }
    }
    private void send_then_receive()throws IOException{
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        final DataOutputStream dataOut = new DataOutputStream(byteOut);

        final byte[] bytes = byteOut.toByteArray();
        final ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        final DataInputStream dataIn = new DataInputStream(byteIn);

        Scanner scanner = new Scanner(System.in);
        int nr = 1;
        while (nr != 0) {
            System.out.println("nr = ");
            nr = scanner.nextInt();
            dataOut.writeInt(nr);

            int x = dataIn.readInt();
            System.out.println("Numarul echilibrat e: " + x);
        }
    }

    public static void main(String[] args) throws IOException{
        DatagramSocket datagramSocket= new DatagramSocket(2345);
        InetAddress inetAddress= InetAddress.getByName("localhost");
        UdpClient2 client = new UdpClient2(datagramSocket,inetAddress);
        client.run();
    }

}
