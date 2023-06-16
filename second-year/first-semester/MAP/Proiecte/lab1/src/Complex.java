public class Complex {
    private double re, im;

    public Complex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }
    public double get_re()
    {
        return this.re;
    }
    public double get_im()
    {
        return this.im;
    }

    @Override
    public String toString()
    {
        if (get_im() < 0)
            return get_re() + " " +  get_im() + " *i";
        return get_re() + " + " + get_im() + " *i";
    }
}