import java.io.*;
import java.util.List;

/**
 * 中缀表达式转换命令行工具
 * <p>
 * 功能特性：
 * <ul>
 *   <li>交互式输入处理</li>
 *   <li>错误信息彩色输出，标准错误流</li>
 *   <li>支持显示部分转换结果</li>
 * </ul>
 * 使用示例：
 * <pre>
 * Input an infix expression and output its postfix notation:
 * 1+2-3
 * 123+-
 * End of program.
 * </pre>
 */
public class Postfix {
    /**
     * 主入口方法
     * @param args 命令行参数
     * @throws IOException 当发生I/O错误时抛出
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            printWelcomeMessage();
            
            final String input = readInput(reader);
            final Parser parser = new Parser(input);
            parser.parse();

            outputResult(parser);
        }
    }

    /**
     * 输出欢迎信息
     */
    private static void printWelcomeMessage() {
        System.out.println("Input an infix expression and output its postfix notation:");
    }

    /**
     * 读取用户输入
     * @param reader 输入流读取器
     * @return 用户输入的表达式
     * @throws IOException 当读取失败时抛出
     */
    private static String readInput(BufferedReader reader) throws IOException {
        return reader.readLine().trim();
    }

    /**
     * 输出转换结果和错误信息
     * @param parser 完成解析的解析器实例
     */
    private static void outputResult(Parser parser) {
        final List<ParseError> errors = parser.getErrors();
        final String output = parser.getOutput();

        if (!errors.isEmpty()) {
            System.out.println(output + " (error)");
            printColoredErrors(errors);
        } else {
            System.out.println(output);
        }
        System.out.println("\nEnd of program.");
    }

    /**
     * @param errors 错误列表
     */
    private static void printColoredErrors(List<ParseError> errors) {
        System.err.println("Parsing errors were found:");
        for (ParseError err : errors) {
            System.err.println(err);
        }
    }
}