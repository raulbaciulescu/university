package utils;

import af.AF;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
    private final List<String> KEY_WORDS = List.of("include", "iostream", "cmath",
            "int", "double", "struct", "main", "cin", "cout",
            "while", "if", "else", "struct", "MyType");
    private final AF integerAF;
    private final AF floatAf;
    private final AF idAf;

    public Filter(AF integerAF, AF floatAf, AF idAf) {
        this.integerAF = integerAF;
        this.floatAf = floatAf;
        this.idAf = idAf;
    }

    public boolean isOperator(String c) {
        return Objects.equals(c, "+") || Objects.equals(c, "-") || Objects.equals(c, "*") ||
                Objects.equals(c, "%") || Objects.equals(c, "/") || Objects.equals(c, "=") ||
                Objects.equals(c, "{") || Objects.equals(c, "}") || Objects.equals(c, "<") ||
                Objects.equals(c, ">") || Objects.equals(c, "!=") || Objects.equals(c, ",") ||
                Objects.equals(c, ";") || Objects.equals(c, "(") || Objects.equals(c, ")") ||
                Objects.equals(c, "<<") || Objects.equals(c, ">>") || Objects.equals(c, "==");
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '%' || c == '/' || c == '=' ||
                c == '{' || c == '}' || c == '<' || c == '>' || c == '!' || c == ',' ||
                c == ';' || c == '(' || c == ')';
    }

    public boolean isKeyWord(String atom) {
        return KEY_WORDS.contains(atom);
    }

    public boolean isSeparator(String atom) {
        return Objects.equals(atom, ";");
    }

    public boolean isIdentifier(String atom) {
        return idAf.isValidSequence(atom);
    }

    public boolean isConst(String atom) {
        return integerAF.isValidSequence(atom) || floatAf.isValidSequence(atom);
    }

    public boolean is2CharactersOperator(String c1, String c2) {
        return (Objects.equals(c1, "<") && Objects.equals(c2, "<")) || (Objects.equals(c1, ">") && Objects.equals(c2, ">")) ||
                (Objects.equals(c1, "!") && Objects.equals(c2, "=")) || (Objects.equals(c1, "=") && Objects.equals(c2, "=")) ||
                (Objects.equals(c1, ">") && Objects.equals(c2, "="))  || (Objects.equals(c1, "<") && Objects.equals(c2, "="));
    }

    public boolean isOperatorOrSpace(char c) {
        return c == ' ' || isOperator(c);
    }
}
