import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Ui {
    private final Worker worker;
    private final String fileName = "src/main/resources/input/float";

    public Ui() {
        this.worker = new Worker();
    }

    public void printMenu() {
        System.out.println("1. Citeste automat din consola");
        System.out.println("2. multimea starilor");
        System.out.println("3. alfabetul");
        System.out.println("4. tranzitiile");
        System.out.println("5. multimea starilor finale");
        System.out.println("6. verifica secventa");
        System.out.println("7. determina cel mai lung prefix");
        System.out.println("8. citeste din fisier");
        System.out.println("9. e determinist");
        System.out.println("0. inchide");
        System.out.print(">> ");
    }

    public void run() {
        try {
            runUi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runUi() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            printMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 0:
                    finished = true;
                case 1:
                    handleReadAutomat();
                    break;
                case 2:
                    handlePrintStateSet();
                    break;
                case 3:
                    handlePrintAlphabet();
                    break;
                case 4:
                    handlePrintTransitions();
                    break;
                case 5:
                    handlePrintFinalStates();
                    break;
                case 6:
                    handleCheckSequence();
                    break;
                case 7:
                    handleLongestPrefix();
                    break;
                case 8:
                    handleReadFromFile();
                    break;
                case 9:
                    handleDeterminist();
                    break;
                default:
                    break;
            }
        }
    }

    private void handleDeterminist() {
        if (worker.isDeterminist())
            System.out.println("AF determinist!");
        else
            System.out.println("AF nedeterminist!");
    }

    private void handleReadFromFile() throws FileNotFoundException {
        InputStream input = new FileInputStream(fileName);
        worker.readFromFile(input);
    }

    private void handleLongestPrefix() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduc secventa: ");
        String sequence = scanner.nextLine();

        System.out.println(worker.getLongestPrefix(sequence));
    }

    private void handleCheckSequence() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduc secventa: ");
        String sequence = scanner.nextLine();

        if (worker.isValidSequence(sequence))
            System.out.println("Secventa e acceptata!");
        else
            System.out.println("Secventa nu e acceptata!");
    }

    private void handlePrintAlphabet() {
        System.out.println(worker.getAlphabet());
        System.out.println();
    }

    private void handlePrintFinalStates() {
        System.out.println(worker.getFinalStates());
        System.out.println();
    }

    private void handlePrintTransitions() {
        System.out.println(worker.getTransitionsMap());
        System.out.println();
    }


    private void handlePrintStateSet() {
        System.out.println(worker.getStates());
    }

    private void handleReadAutomat() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdu multimea starilor: ");
        String states = scanner.nextLine();
        System.out.print("Introdu alfabetul: ");
        String alphabet = scanner.nextLine();
        System.out.print("Introdu starea initiala: ");
        String initialState = scanner.nextLine();
        System.out.print("Introdu stari finale despartite prin spatiu: ");
        String finalState = scanner.nextLine();

        worker.processStatesString(states);
        worker.setInitialState(initialState);
        worker.processFinalStateString(finalState);
        worker.processAlphabetString(alphabet);

        System.out.print("Introdu numar tranzitii: ");
        int transitionNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println(transitionNumber);
        while (transitionNumber > 0) {
            System.out.println("Introdu tranzitii: ");
            String transitions = scanner.nextLine();
            worker.processLineOfTransitions(transitions);
            transitionNumber--;
        }
    }
}
