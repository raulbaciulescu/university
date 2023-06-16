package utils;

public class Regex {
    public static String KEY_WORD = "^\s*(include|iostream|cmath|int|double|struct|main|cin|cout|while|if|else|struct|MyType)\s*$";
    public static String OPERATOR = "\\(|\\)|\\{|\\}|\\+|-|,|/|%|\\*|;|>|<|<=|>=|==|!=|<<|>>|=";
    public static String OPERATOR_MATCH = "((?=(\\(|\\)|\\{|\\}|\\+|-|,|/|%|\\*|;|<=|>=|==|!=|<<|>>|=|>|<))|(?<=(\\(|\\)|\\{|\\}|\\+|-|,|/|%|\\*|;|<=|>=|==|!=|<<|>>|=|>|<)))";
    public static String VARIABLE = "^\s*([a-z]{1,255})\s*$";
    public static String CONST = "^([1-9]\\d*|\\d+.\\d+|0)$";
}
