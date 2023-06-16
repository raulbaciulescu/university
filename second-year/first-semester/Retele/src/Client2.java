import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import java.io.*;
import java.lang.reflect.Array;

public class Client2 {

    public static void main(String args[]) throws Exception {
        //Socket c = new Socket("127.0.0.1", 1234);
        Socket c = new Socket("192.168.0.101", 5656);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream socketIn = new DataInputStream(c.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(c.getOutputStream());
        int a, b, s;
        String x, y;

        x = new String(reader.readLine());
        char[] z = x.toCharArray();
        socketOut.writeUTF(x);
        //y = socketIn.rea
        //System.out.println(x);
//        s = socketIn.readUnsignedShort();
//        System.out.println("s = " + s);

        int sumaMaxima = 0;
        sumaMaxima = socketIn.readUnsignedShort();
        System.out.println("Suma maxima pana acum = " + sumaMaxima);
        reader.close();
        c.close();
    }

}