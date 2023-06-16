import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import java.io.*;
import java.lang.reflect.Array;

public class Client {

    public static void main(String args[]) throws Exception {
        //Socket c = new Socket("127.0.0.1", 1234);
        Socket c = new Socket("192.168.0.101", 5656);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream socketIn = new DataInputStream(c.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(c.getOutputStream());
        int a, b, s;
        int x;
        System.out.print("a = ");
        a = Integer.parseInt(reader.readLine());
        socketOut.writeShort(a);
        socketOut.flush();
        for (int i = 0; i < a; i++) {
            x = Integer.parseInt(reader.readLine());
            socketOut.writeShort(x);
        }
        s = socketIn.readUnsignedShort();
        System.out.println("s = " + s);

        int sumaMaxima = 0;
        sumaMaxima = socketIn.readUnsignedShort();
        System.out.println("Suma maxima pana acum = " + sumaMaxima);
        reader.close();
        c.close();
    }

}