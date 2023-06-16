import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    String expression;

    public ExpressionParser(String expression)
    {
        this.expression = expression;
    }
    public Vector<Complex> make_vector(Vector<Character> vect)
    {
        /*
        With the vector returned by is_complex_number creates the list of complex numbers
         */
        String strPattern = "-?\\d+(\\.\\d+)?";
        Vector<Complex> result = new Vector<Complex>();
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(this.expression);

        Vector<Double> numbers = new Vector<Double>();
        while (matcher.find())
            numbers.add(Double.parseDouble(matcher.group()));

        int i = 0;
        for (int k = 0; k < vect.size(); k++)
        {
            if (vect.get(k) == 'f')
            {
                result.add(new Complex(numbers.get(i), numbers.get(i + 1)));
                i += 2;
            }
            if (vect.get(k) == 'm')
            {
                result.add(new Complex(numbers.get(i), -1));
                i++;
            }
            if (vect.get(k) == 'p')
            {
                result.add(new Complex(numbers.get(i), 1));
                i++;
            }
        }
        return result;
    }
    public Vector<Character> is_complex_number()
    {
        /*
        Check if a string is a complex expression
        in: a string
        out: vect(Vector) with: '0', on first position, if it's not an expression, the sign of the expression otherwise
             the other positions: f if we have(a+b*i)
                                  m if we have(a+i)
                                  p if we have(a-i)
         */
        char c;
        int i = 0;
        int loop_counter = 0;
        char sign = '0';
        boolean stop = false;
        Vector<Character> help = new Vector<Character>();
        help.add('0');
        while (!stop && i < expression.length())
        {
            if (loop_counter == 1)
                //sign = expression.charAt(i);
                help.set(0, expression.charAt(i));
            if (loop_counter > 1 && help.get(0) != expression.charAt(i))
                stop = true;

            if (loop_counter > 0)
                i++;

            // - or first digit
            if(!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '-')
                stop = true;
            if (expression.charAt(i) == '-')
                i++;
            //first number
            boolean ok2 = false, ok3 = true;
            while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'))
            {
                if (!ok3 && expression.charAt(i) == '.')
                    stop = true;
                if (expression.charAt(i) == '.')
                    ok3 = false;

                i++;
                ok2 = true;
            }
            if (i >= expression.length())
            {
                stop = true;
            }
            //if there is no number
            if (!ok2)
                stop = true;

            //sign between numbers
            if (i < expression.length() && expression.charAt(i) != '-' && expression.charAt(i) != '+')
                stop = true;


            boolean minus = false;
            if (i < expression.length() && expression.charAt(i) == '-')
                minus = true;
            i++;


            boolean ok1 = false;
            ok3 = true;
            //second number or i
            while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.'))
            {
                if (!ok3 && expression.charAt(i) == '.')
                    stop = true;
                if (expression.charAt(i) == '.')
                    ok3 = false;
                i++;
                ok1 = true;
            }

            //2 + 2*i
            if (i < expression.length() &&  ok1 && expression.charAt(i) != '*')
                stop = true;
            if (i < expression.length() && ok1 && expression.charAt(i + 1) != 'i')
                stop = true;

            // 2 + i
            if (i < expression.length() && !ok1 && expression.charAt(i) != 'i')
                stop = true;
            if (ok1)
            {
                i += 2;
                help.add('f');
            }
            else
            {
                i++;
                if (minus)
                    help.add('m');
                else
                    help.add('p');
            }
            loop_counter++;
        }
        if (stop)
            help.set(0, '0');
        return help;
    }
    public Complex run()
    {
        Operation operation = Operation.ADDITION;
        Vector<Character> vect = is_complex_number();
        if (vect.get(0) == '-')
            operation = Operation.SUBSTRACTION;
        if (vect.get(0) == '*')
            operation = Operation.MULTIPLICATION;
        if (vect.get(0) == '/')
            operation = Operation.DIVISION;
        if (vect.get(0) == '0')
            return null;
        Vector<Complex> args = make_vector(vect);
        ExpressionFactory expressionFactory = ExpressionFactory.getInstance();
        ComplexExpression complexExpression = expressionFactory.createExpression(operation, args);
        return complexExpression.execute();
    }
}
