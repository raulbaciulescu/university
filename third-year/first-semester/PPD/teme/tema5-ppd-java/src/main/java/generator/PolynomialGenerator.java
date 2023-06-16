package generator;

import util.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PolynomialGenerator {
    private static final Random random = new Random(System.currentTimeMillis());;
    private static List<List<Integer>> polynomials;
    private static List<Integer> usedExponents;

    public static void generatePolynomials() {
        int numberOfPolynomials = Constants.NUMBER_OF_POLYNOMIALS;
        int maxDegree = Constants.MAX_DEGREE;
        int maxMono = Constants.MAX_MONO;
        try {
            generatePoly(numberOfPolynomials, maxDegree, maxMono);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generatePoly(Integer numberOfPolynomials, Integer maxDegree, Integer maxMono) throws IOException {
        for (int i = 0; i < numberOfPolynomials; i++) {
            polynomials = new ArrayList<>();
            usedExponents = new ArrayList<>();
            generateOnePolynomial(i, maxDegree, maxMono);
        }
    }

    private static void generateOnePolynomial(Integer polynomialContor, Integer maxDegree, Integer maxMono) throws IOException {
        int degree = random.nextInt(maxDegree) + 1;
        int mono = random.nextInt(maxMono) + 1;
        for (int i = 0; i < mono; i++) {
            int value = random.nextInt(9);
            int exponent = random.nextInt(degree);
            if (value != 0 && !usedExponents.contains(exponent)) {
                polynomials.add(List.of(value, exponent));
                usedExponents.add(exponent);
            }
        }
        writeToFile(polynomialContor);
    }

    private static void writeToFile(Integer polynomialContor) throws IOException {
        String fileName = "src/main/resources/input/polinom" + polynomialContor + ".txt";
        CreateFile.create(fileName);
        OutputStream output = new FileOutputStream(fileName);
        for (List<Integer> list : polynomials) {
            output.write((list.get(0) + " " + list.get(1) + "\n").getBytes());
        }
    }
}
