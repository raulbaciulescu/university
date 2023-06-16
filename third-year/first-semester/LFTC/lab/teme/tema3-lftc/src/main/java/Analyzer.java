import utils.SyntaxException;
import utils.TableUtils;
import utils.Filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Analyzer {
    private final TableUtils tableUtils;
    private final Filter filter;

    public Analyzer(TableUtils tableUtils, Filter filter) {
        this.tableUtils = tableUtils;
        this.filter = filter;
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
            if (!Objects.equals(line, "")) {
                List<String> lineOfAtoms = processLine(line);
                filterAtoms(combineOperators(lineOfAtoms), lineContor);
            }
        }
        tableUtils.printTables(outputStream);
    }

    /**
     * process a line of code
     * @param line a line of code
     * @return a list with atoms
     */
    public List<String> processLine(String line) {
        List<String> atoms = new ArrayList<>();
        int i = 0;
        while (i < line.length()) {
            while (line.charAt(i) == ' ')
                i++;
            int start = i;
            boolean ok = true;
            while (i < line.length() && ok) {
                if (!filter.isOperatorOrSpace(line.charAt(i)) || (line.charAt(i) == '-' && line.charAt(i - 1) == 'e'))
                    i++;
                else ok = false;
            }

            if (!line.substring(start, i).equals(""))
                atoms.add(line.substring(start, i));
            if (i < line.length() && line.charAt(i) != ' ')
                atoms.add(String.valueOf(line.charAt(i)));
            i++;
        }
        return atoms;
    }

    /**
     * check if an atom is key word or operator or separator or identifier or constant
     * @param atoms a list of atoms
     */
    public void filterAtoms(List<String> atoms, Integer lineContor) {
        for (String atom : atoms) {
            if (filter.isKeyWord(atom) || filter.isOperator(atom) || filter.isSeparator(atom)) {
                tableUtils.addInAtoms(atom);
                tableUtils.addInFIP(atom, 0);
            }
            else if (filter.isIdentifier(atom) || filter.isConst(atom)) {
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
            if (i < atoms.size() - 1 && filter.is2CharactersOperator(atoms.get(i), atoms.get(i + 1))) {
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
