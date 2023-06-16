
import java.util.Arrays;

public class Calculator {
    /**
     *
     * @param numbers
     * @return
     */
    public double add(double... numbers) {
        return Arrays.stream(numbers).sum();
    }

    public double multiply(double... numbers) {
        return Arrays.stream(numbers).reduce(1, (a, b) -> a * b);
    }
}