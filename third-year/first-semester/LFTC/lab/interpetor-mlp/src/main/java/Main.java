import tree.BinaryTree;
import utils.TableUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream("src/main/resources/input/pr3");
        OutputStream output = new FileOutputStream("src/main/resources/output/tables");
        TableUtils tableUtils = new TableUtils();
        Analyzer analyzer = new Analyzer(tableUtils);
        analyzer.process(input, output);
    }
}
