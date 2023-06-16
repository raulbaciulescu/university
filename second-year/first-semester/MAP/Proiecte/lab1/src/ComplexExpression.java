import java.util.Vector;

public abstract class ComplexExpression {
    protected Operation operation;
    protected Vector<Complex> args;

    public ComplexExpression(Operation operation, Vector<Complex> args)
    {
        this.operation = operation;
        this.args = args;
    }

    abstract Complex executeOneOperation(Complex a, Complex b);

    public Complex execute()
    {
        Complex result = args.get(0);
        for (int i = 1; i < args.size(); i++)
            result = executeOneOperation(result, args.get(i));
        return result;
    }
}
