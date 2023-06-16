import java.io.InputStream;
import java.util.*;

public class Worker {
    private final Map<String, List<List<String>>> transitionsMap;
    private String initialState;
    private List<String> finalStates;
    private List<String> states;
    private List<String> alphabet;

    public Worker() {
        transitionsMap = new HashMap<>();
        finalStates = new ArrayList<>();
        alphabet = new ArrayList<>();
        states = new ArrayList<>();
    }

    public void readFromFile(InputStream input) {
        try {
            processLines(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * process a line
     * @param input
     * @throws Exception
     */
    public void processLines(InputStream input) throws Exception {
        Scanner scanner = new Scanner(input);
        String statesString = scanner.nextLine();
        processStatesString(statesString);

        String alphabetString = scanner.nextLine();
        processAlphabetString(alphabetString);

        initialState = scanner.nextLine();

        String finalStateString = scanner.nextLine();
        processFinalStateString(finalStateString);

        while (scanner.hasNextLine()) {
            processLineOfTransitions(scanner.nextLine());
        }
    }

    public void processFinalStateString(String finalStateString) throws Exception {
        List<String> list = List.of(finalStateString.split(" "));

        for (String el : List.of(finalStateString.split(" "))) {
            if (!states.contains(el))
                throw new Exception("exc processFinalStateString");
        }
        finalStates = list;
    }

    public void processAlphabetString(String alphabetString) {
        alphabet = List.of(alphabetString.split(" "));
    }

    public void processStatesString(String statesString) {
        states = List.of(statesString.split(" "));
    }

    public void processLineOfTransitions(String line) throws Exception {
        List<String> lineSplit = new java.util.ArrayList<>(List.of(line.split(" ")));
        putInMapStateAndTransition(lineSplit);
    }

    /**
     * put a line of transaction in transaction map
     * @param lineSplit
     * @throws Exception
     */
    public void putInMapStateAndTransition(List<String> lineSplit) throws Exception {
        String state = lineSplit.get(0);
        lineSplit.remove(0);
        List<List<String>> transitions = getTransitionsFromLineList(lineSplit);

        if (!states.contains(state) || !isValidTransactionList(transitions))
            throw new Exception("exc in putInMapStateAndTransition");

        transitionsMap.put(state, transitions);
    }

    private boolean isValidTransactionList(List<List<String>> transitions) {
        for (List<String> list : transitions) {
            if (!states.contains(list.get(1)))
                return false;
            if (!alphabet.contains(list.get(0)))
                return false;
        }

        return true;
    }

    public List<List<String>> getTransitionsFromLineList(List<String> lineSplit) {
        int i = 0;
        List<List<String>> transitions = new ArrayList<>();
        while (i < lineSplit.size()) {
            transitions.add(List.of(lineSplit.get(i), lineSplit.get(i + 1)));
            i += 2;
        }

        return transitions;
    }

    public Map<String, List<List<String>>> getTransitionsMap() {
        return transitionsMap;
    }

    public void setInitialState(String initialState) {
        if (!states.contains(initialState))
            throw new RuntimeException();
        this.initialState = initialState;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public List<String> getStates() {
        return states;
    }

    public String getLongestPrefix(String sequence) {
        if (!isDeterminist())
            return "";

        String currentState = initialState;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            boolean ok = false;
            for (List<String> list : transitionsMap.get(currentState)) {
                if (Objects.equals(list.get(0), String.valueOf(sequence.charAt(i)))) {
                    ok = true;
                    currentState = list.get(1);
                    System.out.println(currentState);
                    stringBuilder.append(sequence.charAt(i));
                }
            }
            if (!ok)
                return stringBuilder.toString();
        }

        if (finalStates.contains(currentState))
            return stringBuilder.toString();

        return "";
    }

    public boolean isValidSequence(String sequence) {
        if (!isDeterminist())
            return false;

        String currentState = initialState;
        for (int i = 0; i < sequence.length(); i++) {
            boolean ok = false;
            for (List<String> list : transitionsMap.get(currentState)) {
                if (Objects.equals(list.get(0), String.valueOf(sequence.charAt(i)))) {
                    ok = true;
                    currentState = list.get(1);
                    System.out.println(currentState);
                }
            }
            if (!ok)
                return false;
        }

        return finalStates.contains(currentState);
    }

    public boolean isDeterminist() {
        for(Map.Entry<String, List<List<String>>> mapEntry: transitionsMap.entrySet()) {
            List<List<String>> list = mapEntry.getValue();
            List<String> myStates = new ArrayList<>();
            for (List<String> transaction : list) {
                if (myStates.contains(transaction.get(0))) {
                    return false;
                }
                myStates.add(transaction.get(0));
            }
        }

        return true;
    }
}
