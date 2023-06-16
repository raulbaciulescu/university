package socialnetwork.ui;

import socialnetwork.service.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private Controller controller;

    public Ui(Controller controller) {
        this.controller = controller;
    }

    private void print() {
        System.out.println("1. Adauga utilizator");
        System.out.println("2. Adauga relatie de prietenie");
        System.out.println("3. Sterge utilizator");
        System.out.println("4. Sterge prietenie");
        System.out.println("5. Afisare numar de comunitati");
        System.out.println("0. x");
        System.out.println("Introdu optiunea");
    }
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Reading data using readLine
            String name = null;
            name = reader.readLine();
            // Printing the read line
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
