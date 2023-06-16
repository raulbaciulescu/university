package ro.ubbcluj.map.functional;


@FunctionalInterface //ca are o singura metoda abstracta
public interface AritmeticExpression {
    double  pi=3.14;
    double  calculate(double a, double b);  //abstract
    default double sqrt(double a) {
        return Math.sqrt(a);
    }

    default double power(double a, double  n) {
        return Math.pow(a, n);
    }

    default double numarLaPatrat(double n) {
        return power(n,2);
    }

    default double numarLaCub(double n) {
        return power(n,3);
    }

    default double patratBinom(double x, double y){ return Math.pow(x+y,2); }

    static double cubBinom(double x, double y){ return Math.pow(x+y,3); }

    static double suma(double x, double y) {return x+y;}
}
