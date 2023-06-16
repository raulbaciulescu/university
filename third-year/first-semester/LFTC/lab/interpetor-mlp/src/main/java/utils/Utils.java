package utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isOperator(String c) {
        Matcher matcher = Pattern.compile(Regex.OPERATOR).matcher(c);
        return matcher.find();
    }

    public static boolean isKeyWord(String atom) {
        Matcher matcher = Pattern.compile(Regex.KEY_WORD).matcher(atom);
        return matcher.find();
    }

    public static boolean isSeparator(String atom) {
        return Objects.equals(atom, ";");
    }

    public static boolean isIdentifier(String atom) {
        Matcher matcher = Pattern.compile(Regex.VARIABLE).matcher(atom);
        return matcher.find();
    }

    public static boolean isConst(String atom) {
        Matcher matcher = Pattern.compile(Regex.CONST).matcher(atom);
        return matcher.find();
    }

    public static boolean is2CharactersOperator(String c1, String c2) {
        return (Objects.equals(c1, "<") && Objects.equals(c2, "<")) || (Objects.equals(c1, ">") && Objects.equals(c2, ">")) ||
                (Objects.equals(c1, "!") && Objects.equals(c2, "=")) || (Objects.equals(c1, "=") && Objects.equals(c2, "=")) ||
                (Objects.equals(c1, ">") && Objects.equals(c2, "="))  || (Objects.equals(c1, "<") && Objects.equals(c2, "="));
    }
}
