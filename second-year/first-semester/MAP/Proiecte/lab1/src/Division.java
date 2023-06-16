import java.util.Vector;

public class Division extends ComplexExpression{
    public Division(Operation operation, Vector<Complex> args) {
        super(operation, args);
    }

    @Override
    Complex executeOneOperation(Complex a, Complex b) {
        double nr  = b.get_re() * b.get_re() + b.get_im() * b.get_im();
        return new Complex((a.get_re() * b.get_re() + a.get_im() * b.get_im()) / nr,(a.get_re() * b.get_im() - b.get_re() * a.get_im()) / nr);
    }
}
