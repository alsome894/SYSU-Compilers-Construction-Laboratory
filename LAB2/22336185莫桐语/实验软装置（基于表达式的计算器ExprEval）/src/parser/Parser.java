package parser;

import exceptions.*;
import parser.scanner.*;
import parser.token.*;

import java.util.*;

/**
 * 分析器 (Parser)。
 * 给定输入缓冲区 (expr)，使用算符优先分析法 (opp) 进行解析。
 */
public class Parser {
    /** 输入缓冲区 (expr)。 */
    private ArrayList <Token> buffer;

    /** 用于解析的操作符优先级栈。 */
    private ArrayList <Token> stack;

    /** 用于存储栈顶终结符。 */
    private Token topMostTerminal;

    /** 用于存储输入串头单词。 */
    private Token lookahead;

    /** 用于标识操作数。 */
    private int action;

    /**
     * 静态操作符优先级表。
     * 用于决定操作行为。
     * 0 : 移入 (shift)
     * 1 : 规约 (reduce)
     * 2 : 接受 (accept)
     * -7 : 缺少操作符
     * -8 : 缺少操作数
     * -9 : 缺少左括号 (
     * -10 : 缺少右括号 )
     * -11 : 函数调用错误
     * -12 : 三元操作符错误
     * -16 : 类型不匹配
     */
    private static int [][]table;

    /**
     * 构造函数。
     * 缓冲区是表达式的标记列表。
     * 栈初始化为空。
     * 在表达式末尾添加一个 $ 符号。
     * @param expression 表达式
     * @throws ExpressionException 分析器可能抛出的异常
     */
    Parser(String expression) throws ExpressionException {
        buffer = new ArrayList <Token>(new MyScanner(expression).scan());
        stack = new ArrayList <Token>();
        buffer.add(new Symbol("$"));
    }

    /**
     * 从栈顶获取第一个终结符。
     * @return 栈顶的终结符标记
     */
    private Token getTopMostTerminal() {
        int stackLength = stack.size();
        int i = stackLength - 1;
        for (; i >= 0; i--) {
            if (stack.get(i).isTerminal())
                break;
            }
        return stack.get(i);
    }

    /**
     * 将新标记添加到栈中。
     * @return 实际的标记类型。
     *         可能会将标记转换为 decimal 或 boolean 等类型。
     * @throws IllegalSymbolException 如果标记类型未定义
     */
    private Token addInStack() throws IllegalSymbolException {
        switch (lookahead.getType()) {
            case "decimal":
                return new Decimal(lookahead);
            case "boolean":
                return new MyBoolean(lookahead);
            case "trinary":
            case "decimal_operator":
            case "function":
            case "relation":
            case "boolean_operator":
            case "unary":
            case "parenthesis":
            case "comma":
            case "dollar":
                return new Symbol(lookahead);
            default:
                throw new IllegalSymbolException();
        }
    }

    /**
     * 从栈中获取表达式的答案。
     * @return 表达式的答案，一个标记。
     * @throws TypeMismatchedException 答案必须是 decimal 类型。
     */
    private double getResult() throws TypeMismatchedException {
        if (stack.size() == 2) {
            Token top = stack.get(stack.size() - 1);
            if (top.getType().equals("decimal"))
                return top.getDecimal();
            else
                throw new TypeMismatchedException();
        }
        return 0;
    }

    /**
     * 规约操作。
     * @throws ExpressionException 规约过程中可能抛出的异常
     */
    private void reduce() throws ExpressionException {
        topMostTerminal = getTopMostTerminal();
        lookahead = buffer.get(0);
        action = table[topMostTerminal.getPriority()][lookahead.getPriority()];
        stack = new Reducer(stack).calculate(topMostTerminal.getType());
    }

    /**
     * 移入操作。
     * 将标记添加到栈中，并从缓冲区中移除。
     * @throws IllegalSymbolException addInStack 方法可能抛出的错误
     */
    private void shift() throws IllegalSymbolException {
        stack.add(addInStack());
        buffer.remove(0);
    }

    /**
     * 算符优先分析法opp。
     * 比较栈顶和缓冲区顶的标记。
     * 根据操作符优先级表决定操作行为。
     * 0 表示移入，1 表示规约，2 表示接受。
     * 负数表示错误。
     * @return 解析结果。
     * @throws ExpressionException 表示操作符优先级表中的异常。
     */
    public double parse() throws ExpressionException {
        stack.add(new Symbol("$"));
        while (true) {
            topMostTerminal = getTopMostTerminal();
            lookahead = buffer.get(0);
            action = table[topMostTerminal.getPriority()][lookahead.getPriority()];

            switch (action) {
                case 0:
                    shift();
                    break;
                case 1:
                    reduce();
                    break;
                case 2:
                    double result = getResult();
                    return result;
                case -7:
                    throw new MissingOperatorException();
                case -8:
                    throw new MissingOperandException();
                case -9:
                    throw new MissingLeftParenthesisException();
                case -10:
                    throw new MissingRightParenthesisException();
                case -11:
                    throw new FunctionCallException();
                case -12:
                    throw new TrinaryOperationException();
                case -16:
                    throw new TypeMismatchedException();
                case -17:
                    throw new CommaException();
            }
        }
    }

    /**
     * 初始化操作符优先级表。
     */
    static {
        table = new int[][]{
            /* b    d    (    )    f    -    ^    *    +    r    !    &    |    ?    :    ,    $  */
            { -7,  -7,  -7,   1,  -7,  -7, -16, -16, -16, -16,  -7,   1,   1,   1,   1,   1,   1 }, // boolean 
            { -7,  -7,  -7,   1,  -7,  -7,   1,   1,   1,   1,  -7,   1,   1,   1,   1,   1,   1 }, // decimal 
            {  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, -12,   0, -10 }, // ( 
            { -7,  -7,  -7,   1,  -7,  -7,   1,   1,   1,   1,  -7,   1,   1,   1,   1,   1,   1 }, // ) 
            {-11, -11,   0,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9,  -9 }, // function 
            {  0,   0,   0,   1,   0,   0,   1,   1,   1,   1,   0,   1,   1,   1,   1,   1,   1 }, // unary - 
            {  0,   0,   0,   1,   0,   0,   0,   1,   1,   1,   0,   1,   1,   1,   1,   1,   1 }, // ^ 
            {  0,   0,   0,   1,   0,   0,   0,   1,   1,   1,   0,   1,   1,   1,   1,   1,   1 }, // op * / 
            {  0,   0,   0,   1,   0,   0,   0,   0,   1,   1,   0,   1,   1,   1,   1,   1,   1 }, // op + - 
            {  0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1,   1,   1,   1 }, // relation 
            {  0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1,   1,   1,   1 }, // ! 
            {  0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1,   1,   1,   1 }, // & 
            {  0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1,   1,   1 }, // | 
            {  0,   0,   0, -12,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, -17, -12 }, // ? 
            {  0,   0,   0,   1,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1 }, // : 
            {  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, -17,   0, -17 }, // , 
            {  0,   0,   0,  -9,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, -12, -17,   2 }  // $ 
        };
    }
}

    