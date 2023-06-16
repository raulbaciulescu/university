import java.util.Vector;

public class Test {

    private void test_is_complex_number()
    {
        ExpressionParser expressionParser = new ExpressionParser("aaa");
        assert expressionParser.is_complex_number().get(0) == '0';

        ExpressionParser expressionParser1 = new ExpressionParser("2+i");
        assert expressionParser1.is_complex_number().get(0) == '0';

        ExpressionParser expressionParser2 = new ExpressionParser("2+i+2+i");
        assert expressionParser2.is_complex_number().get(0) == '+';

        ExpressionParser expressionParser3 = new ExpressionParser("2.2+i*2.3+i");
        assert expressionParser3.is_complex_number().get(0) == '*';

        ExpressionParser expressionParser4 = new ExpressionParser("2.2+12.4*i-2.323+56.5*i");
        assert expressionParser4.is_complex_number().get(0) == '-';

        ExpressionParser expressionParser5 = new ExpressionParser("2.2+12.4*i-2.323++56.5*i");
        assert expressionParser5.is_complex_number().get(0) == '0';

        ExpressionParser expressionParser6 = new ExpressionParser("2.2+12.4*i-2.323++5..6.5*i");
        assert expressionParser6.is_complex_number().get(0) == '0';

        ExpressionParser expressionParser7 = new ExpressionParser("2.2+12.4*i-2.323+5.6.5*i/34+3*i");
        assert expressionParser7.is_complex_number().get(0) == '0';

        ExpressionParser expressionParser8 = new ExpressionParser("2.2+12.4*i-2.323+56.5*i-2");
        assert expressionParser8.is_complex_number().get(0) == '0';

    }
    private void test_make_vector()
    {
        ExpressionParser expressionParser = new ExpressionParser("-2-12*i-3-56*i-3434+i");

        Vector<Complex> vect = expressionParser.make_vector(expressionParser.is_complex_number());
        assert vect.get(0).get_re() == -2.0;
        assert vect.get(0).get_im() == -12.0;

        assert vect.get(1).get_re() == -3.0;
        assert vect.get(1).get_im() == -56.0;

        assert vect.get(2).get_re() == -3434;
        assert vect.get(2).get_im() == 1.0;

    }
    private void test_result()
    {
        ExpressionParser expressionParser = new ExpressionParser("-2-2*i-3-3*i-4+i");
        Complex result = expressionParser.run();
        assert result.get_re() == 5.0;
        assert result.get_im() == 0.0;

        ExpressionParser expressionParser2 = new ExpressionParser("-2-2*i+3-3*i+4+i");
        result = expressionParser2.run();
        assert result.get_re() == 5.0;
        assert result.get_im() == -4.0;


        ExpressionParser expressionParser3 = new ExpressionParser("-2-2*i*3-3*i*4+i");
        result = expressionParser3.run();
        assert result.get_re() == -48.0;
        assert result.get_im() == -12.0;

        ExpressionParser expressionParser4 = new ExpressionParser("-2000-2000*i/2-2*i");
        result = expressionParser4.run();
        assert result.get_re() == 0.0;
        assert result.get_im() == 1000.0;
    }
    public void run_all()
    {
        test_is_complex_number();
        test_make_vector();
        test_result();
    }
}
