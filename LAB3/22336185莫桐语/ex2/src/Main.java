import java.io.*;
import exceptions.*;
import scanner.*;

/**
 * 主类，负责读取输入文件并调用 OberonScanner 进行词法分析。
 */
public class Main {
    /**
     * 程序入口，读取参数中的文件并输出词法分析结果。
     * @param argv 输入文件路径数组
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

                do {
                    System.out.println(scanner.next_token());
                } while (!scanner.yyatEOF());
            } catch (OberonException e) {
                System.out.println("Lexical error \"" + argv[i] + "\":");
                System.out.println(e);
            } catch (FileNotFoundException e) {
                System.out.println("File not found \"" + argv[i] + "\"");
            } catch (IOException e) {
                System.out.println("IO error \"" + argv[i] + "\":");
                System.out.println(e);
            } catch (Exception e) {
                System.out.println("Unexpected error:");
                e.printStackTrace();
            }
        }
    }
}