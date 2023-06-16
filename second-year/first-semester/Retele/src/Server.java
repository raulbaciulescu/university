
import java.net.*;
import java.io.*;

public class Server {

    public static void main(String args[]) throws Exception {
        ServerSocket s = new ServerSocket(1234);
        byte b[] = new byte[100];
        int a = 0;
        int d = 0;
        int suma = 0;
        while(true) {
            Socket c = s.accept();
            System.out.println("Client connected!");

            //DataOutputStream out;
            try {
                DataOutputStream out = new DataOutputStream(c.getOutputStream());
                DataInputStream in = new DataInputStream(c.getInputStream());

                a = in.readShort();
                for (int i = 0; i < a; i++) {
                    d = in.readShort();
                    suma += d;
                }
                out.writeShort(suma);

            } catch (IOException e) {
                //Bail out
            }
            //c.getInputStream().read(a);
            //c.getInputStream().read(b);
            //System.out.println(new String(b));

            c.close();
        }
    }

}