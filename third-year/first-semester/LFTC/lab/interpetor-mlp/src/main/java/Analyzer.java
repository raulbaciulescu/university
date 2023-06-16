import utils.Regex;
import utils.SyntaxException;
import utils.TableUtils;
import utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class Analyzer {
    private final TableUtils tableUtils;

    public Analyzer(TableUtils tableUtils) {
        this.tableUtils = tableUtils;
    }

    /**
     * read from file the source program and send to interpreter line by line to be processed
     * @param inputStream an input stream with the program path
     */
    public void process(InputStream inputStream, OutputStream outputStream) {
        try {
            processMLP(inputStream, outputStream);
        } catch (Exception e) {
            logError(e);
        }
    }

    private void logError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * read the MLP and send to other processors
     */
    public void processMLP(InputStream inputStream, OutputStream outputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        int lineContor = 0;
        while (scanner.hasNextLine()) {
            lineContor++;
            String line = scanner.nextLine();
            List<String> lineOfAtoms = processLine(line);
            filterAtoms(lineOfAtoms, lineContor);
        }
        tableUtils.printTables(outputStream);
    }

    /**
     * process a line of code
     * @param line a line of code
     * @return a list with atoms
     */
    public List<String> processLine(String line) {
        List<String> lineSplit = List.of(line.split("\\s+"));
        List<String> atoms = new ArrayList<>();

        for (String elem : lineSplit) {
            atoms.addAll(combineOperators(List.of(elem.split(Regex.OPERATOR_MATCH))));
            System.out.println(combineOperators(List.of(elem.split(Regex.OPERATOR_MATCH))));
        }

        return atoms;
    }

    /**
     * check if an atom is key word or operator or separator or identifier or constant
     * @param atoms a list of atoms
     */
    public void filterAtoms(List<String> atoms, Integer lineContor) {
        for (String atom : atoms) {
            if (Utils.isKeyWord(atom) || Utils.isOperator(atom) || Utils.isSeparator(atom)) {
                tableUtils.addInAtoms(atom);
                tableUtils.addInFIP(atom, 0);
            }
            else if (Utils.isIdentifier(atom) || Utils.isConst(atom)) {
                if (!tableUtils.tsContains(atom))
                    tableUtils.addInTS(atom);
                int index = tableUtils.getIndexOfAtom(atom);
                tableUtils.addInAtoms(atom);
                tableUtils.addInFIP(atom, index);
            }
            else
                throw new SyntaxException("Error on line: " + lineContor);
        }
    }

    /**
     * creates a new list with all operators with 2 characters merged
     */
    public List<String> combineOperators(List<String> atoms) {
        int i = 0;
        List<String> atomsNew = new ArrayList<>();
        while (i < atoms.size()) {
            if (i < atoms.size() - 1 && Utils.is2CharactersOperator(atoms.get(i), atoms.get(i + 1))) {
                atomsNew.add(atoms.get(i) + atoms.get(i + 1));
                i++;
            }
            else if (!Objects.equals(atoms.get(i), "")) {
                atomsNew.add(atoms.get(i));
            }
            i++;
        }
        return atomsNew;
    }
}
