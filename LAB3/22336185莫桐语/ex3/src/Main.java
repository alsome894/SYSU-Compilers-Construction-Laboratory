import java.io.*;
import exceptions.*;

/**
 * 主类 Main 处理输入文件并使用 OberonScanner 和 Parser 进行词法和语法分析。
 */
public class Main {

    /**
     * 程序的入口点。
     *
     * @param argv 命令行参数，包含要处理的文件名列表。
     */
    public static void main(String argv[]) {
        if (argv.length == 0) {
            System.out.println("Missing arguments.");
            System.out.println("\nUsage: java Main <inputfile>");
            return;
        }

        String encodingName = "UTF-8";

        for (int i = 0; i < argv.length; i++) {
            System.out.println("Loading \"" + argv[i] + "\"");

            OberonScanner scanner = null;

            try {
                FileInputStream stream = new FileInputStream(argv[i]);
                Reader reader = new InputStreamReader(stream, encodingName);
                scanner = new OberonScanner(reader);
            } catch (FileNotFoundException e) {
                System.out.println("File not found \"" + argv[i] + "\"");
            } catch (IOException e) {
                System.out.println("IO error \"" + argv[i] + "\":");
                System.out.println(e);
            }

            Parser p = new Parser(scanner);

            try {
                p.parse();
            } catch (Exception e) {
                int line = scanner.getLine() + 1;
                int column = scanner.getColumn() + 1;
                System.out.println("Error occurs at [" + line + "," + column + "]: " + e + "\n");
            }
        }
    }
}
