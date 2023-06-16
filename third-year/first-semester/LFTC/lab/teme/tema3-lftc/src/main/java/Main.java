import af.AF;
import utils.Filter;
import utils.TableUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream("src/main/resources/input/pr1");
        OutputStream output = new FileOutputStream("src/main/resources/output/tables");

        AF integerAf = new AF("src/main/resources/af/integer");
        AF floatAf = new AF("src/main/resources/af/float");
        AF idAf = new AF("src/main/resources/af/id");
        integerAf.readFromFile();
        floatAf.readFromFile();
        idAf.readFromFile();

        Filter filter = new Filter(integerAf, floatAf, idAf);
        TableUtils tableUtils = new TableUtils(filter);
        Analyzer analyzer = new Analyzer(tableUtils, filter);
        analyzer.process(input, output);
    }
}
