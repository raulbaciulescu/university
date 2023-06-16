package java;

public class Main {
    public static void main(String[] args) {
        char[] text = {'1', '1', '1', '1', '0', '1', ' ', '1', '1', '1', ' ', '1', '0', '1', '0', '1'};
        int i = 15, numar = 0, aux = 1, suma = 0;
        while (i > 0) {
            if (text[i] == ' ') {
                //avem un nou numar
                if (suma != 0)
                    numar = numar * 10 + suma;
                System.out.println(numar);
                numar = 0;
                aux = 1;
                suma = 0;
            }
            else {
                if (text[i] == '1') {
                    suma += aux;
                }
                if (aux == 4) {
                    aux = 1;
                    numar = numar * 10 + suma;
                    suma = 0;
                }
                else
                    aux *= 2;
            }
            i--;
        }

    }
}
