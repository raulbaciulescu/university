import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Worker {
    private List<String> lines;
    private char start;
    private int lineContor;
    private boolean isEpsilon = false;
    private InputStream input;

    public Worker() throws FileNotFoundException {
        input = new FileInputStream("D:\\Facultate\\LFTC\\inclass-lab10\\src\\main\\resources\\input");
        start = '!';
        lineContor = 0;
        lines = new ArrayList<>();
    }

    public boolean isRegulara() {
        Scanner scanner = new Scanner(input);
        boolean regulara = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
            List<String> lineSplit = List.of(line.split("-"));
            if (!checkLeft(lineSplit))
                regulara = false;

            String right = lineSplit.get(1);
            List<String> rightListString = List.of(right.split(" "));
            if (!checkRight(rightListString))
                regulara = false;

            lineContor++;
        }
        if (isEpsilon && regulara)
            if (!checkFinal())
                regulara = false;

        return regulara;
    }

    private boolean checkFinal() {
        boolean regulara = true;
        lineContor = 0;
        for (String line : lines) {
            List<String> lineSplit = List.of(line.split("-"));
            if (!checkLeft(lineSplit))
                regulara = false;

            String right = lineSplit.get(1);
            List<String> rightListString = List.of(right.split(" "));
            if (!checkRight(rightListString))
                regulara = false;

            lineContor++;
        }

        return regulara;
    }

    private boolean checkRight(List<String> rightListString) {
        for (String str : rightListString) {
            if (str.length() > 1)
                return false;
        }

        if (isEpsilon(rightListString.get(0).charAt(0))) {
            isEpsilon = true;
            if (rightListString.size() == 1)
                return true;
        }

        if (isUpper(rightListString.get(0).charAt(0)))
            return false;

        if (rightListString.size() > 1 && isLower(rightListString.get(1).charAt(0)))
            return false;

        if (rightListString.size() > 1 && isEpsilon && rightListString.get(1).charAt(0) == start) {
            return false;
        }

        return true;
    }

    private boolean checkLeft(List<String> lineSplit) {
        if (lineSplit.get(0).length() > 1) {
            return false;
        }
        char left = lineSplit.get(0).charAt(0);
        if (lineContor == 0)
            start = left;

        if (!isUpper(left)) {
            return false;
        }

        return true;
    }

    private boolean isUpper(char left) {
        return left >= 'A' && left <= 'Z';
    }

    private boolean isLower(char left) {
        return left >= 'a' && left <= 'z';
    }

    private boolean isEpsilon(char left) {
        return left == '#';
    }
}
