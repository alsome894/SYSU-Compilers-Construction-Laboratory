import java.io.*;
import exceptions.*;
import scanner.*;
import parser.*;

/**
 * 程序主入口，负责参数解析和文件处理。
 */
public class Main {
    /**
     * 打印用法提示信息。
     */
    private static void printUsage() {
        System.out.println("\nUsage: java Main -e,--encoding <encoding> <inputfile>");
        System.out.println("\t<encoding>: Encoding of input file(default: UTF-8)");
    }

    /**
     * 主方法，程序入口。
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing arguments.");
            printUsage();
            return;
        }

        String encodingName = "UTF-8";
        String inputFileName = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-e") || args[i].equals("--encoding")) {
                if (i + 1 < args.length) {
                    encodingName = args[++i];
                } else {
                    System.out.println("Missing encoding name");
                    printUsage();
                    return;
                }
            } else {
                inputFileName = args[i];
            }
        }

        if (inputFileName == null) {
            System.out.println("Missing input file");
            printUsage();
            return;
        }

        try {
            FileInputStream stream = new FileInputStream(inputFileName);
            Reader reader = new InputStreamReader(stream, encodingName);

            OberonScanner scanner = new OberonScanner(reader);
            Parser parser = new Parser(scanner::next_token);

            parser.parse();
        } catch (FileNotFoundException e) {
            System.out.println("File not found \"" + inputFileName + "\"");
        } catch (IOException e) {
            System.out.println("IO error:" + e);
        } catch (OberonException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}
