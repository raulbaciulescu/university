import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Worker worker = new Worker();
        if (worker.isRegulara())
            System.out.println("Gramatica este regulara");
        else
            System.out.println("Gramatica nu este regulara");
    }
}
