package udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpServer2 {
    private final DatagramSocket datagramSocket;
    private byte[]buffer=new byte[300];

    public UdpServer2(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    public void run() throws IOException {
        while (true) {
            receive_then_send();
        }
    }
    private int echilibrat(int x) {
        int sumaPar, sumaImpar, aux, contor;
        boolean gasit = false;
        while (!gasit) {
            aux = x;
            sumaImpar = 0;
            sumaPar = 0;
            contor = 0;
            while (aux > 0) {
                if (contor % 2 == 0)
                    sumaPar += aux % 10;
                else
                    sumaImpar += aux % 10;
                aux /= 10;
                contor++;
            }
            if (sumaImpar == sumaPar)
                gasit = true;
            else
                x++;
        }
        return x;
    }
    private void receive_then_send()throws IOException {
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        final DataOutputStream dataOut = new DataOutputStream(byteOut);

        final byte[] bytes = byteOut.toByteArray();
        final ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        final DataInputStream dataIn = new DataInputStream(byteIn);

        int x = 1;
        while (x != 0) {
            x = dataIn.readInt();
            System.out.println("Serverul a primit numarul " + x);
            dataOut.writeInt(echilibrat(x));
        }
    }

    public static void main(String[] args)throws SocketException {
        DatagramSocket socket= new DatagramSocket(2345);
        UdpServer2 server=new UdpServer2(socket);
        try {
            server.run();
        }catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }

    }
}
