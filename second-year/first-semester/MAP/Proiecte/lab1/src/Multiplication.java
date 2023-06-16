import java.util.Vector;

public class Multiplication extends ComplexExpression{
    public Multiplication(Operation operation, Vector<Complex> args) {
        super(operation, args);
    }

    @Override
    Complex executeOneOperation(Complex a, Complex b) {
        return new Complex(a.get_re() * b.get_re() - a.get_im() * b.get_im(), a.get_re() * b.get_im() + a.get_im() * b.get_re());
    }
}
