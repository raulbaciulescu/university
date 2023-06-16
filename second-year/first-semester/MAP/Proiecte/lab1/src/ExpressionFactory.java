import java.util.Vector;

public class ExpressionFactory {
    private static ExpressionFactory instance = null;

    private ExpressionFactory(){}

    public static ExpressionFactory getInstance(){
        if (instance == null)
            instance = new ExpressionFactory();
        return instance;
    }


    public ComplexExpression createExpression(Operation operation, Vector<Complex> args)
    {
        /*
        Creates an expression with a specific operation
        in: operatation(Operation), args(complex numbers)
        out: a new ComplexExpression object
         */
        if (operation == Operation.ADDITION)
            return new Addition(operation, args);
        if (operation == Operation.SUBSTRACTION)
            return new Substraction(operation, args);
        if (operation == Operation.MULTIPLICATION)
            return new Multiplication(operation, args);
        if (operation == Operation.DIVISION)
            return new Division(operation, args);
        return null;
    }
}
