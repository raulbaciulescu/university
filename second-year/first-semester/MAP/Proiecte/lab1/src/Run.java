

public class Run {


    public static void main(String[] args) {

        Test test = new Test();
        test.run_all();

        System.out.println(args[0]);
        ExpressionParser expressionParser = new ExpressionParser(args[0]);
        Complex result = expressionParser.run();
        if (result == null)
            System.out.println("Invalid expression!");
        else
            System.out.println(result);

    }
}