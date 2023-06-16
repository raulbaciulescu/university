package utils;

import tree.BinaryTree;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TableUtils {
    private final Map<Integer, Integer> FIP;
    private final Map<String, Integer> atoms;
    private final BinaryTree idTS;
    private final BinaryTree constTS;
    private final Filter filter;

    public TableUtils(Filter filter) {
        this.FIP = new TreeMap<>((a, b) -> 1);;
        this.atoms = new LinkedHashMap<>();
        this.idTS = new BinaryTree();
        this.constTS = new BinaryTree();
        this.filter = filter;
    }

    public void addInTS(String atom) {
        if (filter.isIdentifier(atom)) {
            idTS.add(atom);
        } else
            constTS.add(atom);
    }

    public void addInFIP(String atom, Integer index) {
        if (filter.isIdentifier(atom) && !filter.isKeyWord(atom)) {
            FIP.put(atoms.get("ID"), index);
        }
        else if (filter.isConst(atom)) {
            FIP.put(atoms.get("CONST"), index);
        }
        else
            FIP.put(atoms.get(atom), index);
    }

    public void addInAtoms(String atom) {
        String newAtom = "";
        if (filter.isIdentifier(atom) && !filter.isKeyWord(atom)) {
            newAtom = "ID";
        }
        else if (filter.isConst(atom))
            newAtom = "CONST";
        else
            newAtom = String.valueOf(atom);

        if (atoms.isEmpty() && !atoms.containsKey(newAtom))
            atoms.put(newAtom, 0);
        else if (!atoms.containsKey(newAtom))
            atoms.put(newAtom, atoms.size());
    }

    public boolean tsContains(String atom) {
        return (idTS.getContorByValue(atom) != -1) || (constTS.getContorByValue(atom) != -1);
    }

    public int getIndexOfAtom(String atom) {
        return Math.max(idTS.getContorByValue(atom), constTS.getContorByValue(atom));
    }

    public void printTables(OutputStream outputStream) throws IOException {
        outputStream.write("Id TS:\n".getBytes());
        idTS.traverseLevelOrder((FileOutputStream) outputStream);
        outputStream.write("\n".getBytes());
        outputStream.write("Const TS:\n".getBytes());
        constTS.traverseLevelOrder((FileOutputStream) outputStream);

        outputStream.write("\n".getBytes());
        outputStream.write("FIP:\n".getBytes());
        for (Map.Entry<Integer, Integer> entry : FIP.entrySet()) {
            outputStream.write((entry.getKey() + " : " + entry.getValue() +"\n").getBytes());
        }

        outputStream.write("\n".getBytes());
        outputStream.write("Atoms:\n".getBytes());
        for (Map.Entry<String, Integer> entry : atoms.entrySet()) {
            outputStream.write((entry.getKey() + " : " + entry.getValue() +"\n").getBytes());
        }
    }
}
