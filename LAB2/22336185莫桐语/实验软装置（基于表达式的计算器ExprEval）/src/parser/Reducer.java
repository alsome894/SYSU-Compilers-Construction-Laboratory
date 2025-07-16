package parser;

import exceptions.*;
import parser.expr.*;
import parser.token.*;

import java.util.*;

/**
 * 给定栈，规约栈顶的一部分。
 */
public class Reducer {
    /** 操作符优先级栈。 */
    private ArrayList <Token> stack;
    
    /** 栈的长度。 */
    private int length;

    /**
     * 空构造函数。
     */
    public Reducer() {
        stack = new ArrayList <Token>();
        length = 0;
    }

    /**
     * 带栈的构造函数。
     * @param _stack 操作符优先级栈
     */
    public Reducer(ArrayList <Token> _stack) {
        stack = new ArrayList <Token>(_stack);
        length = stack.size();
    }

    /**
     * 从栈顶开始查找第一个终结符的索引。
     * @param last 查找的起始索引。
     * @return 终结符的索引。
     * @throws MissingOperatorException 如果未找到终结符，则抛出 MissingOperatorException。
     */
    private int getTerminalLocation(int last) throws MissingOperatorException {
        for (int i = last; i >= 0; i--)
            if (stack.get(i).isTerminal())
                return i;
        throw new MissingOperatorException();
    }

    /**
     * 查找栈顶的第一个左括号。
     * @return 左括号的索引。
     * @throws MissingLeftParenthesisException 如果未找到左括号。
     */
    private int findLeftParenthesis() throws MissingLeftParenthesisException {
        for (int i = length - 1; i >= 0; i--)
            if (stack.get(i).getValue().equals("("))
                return i;
        throw new MissingLeftParenthesisException();
    }

    /**
     * 替换栈中的部分内容。
     * 从栈中移除 [location, location + counts) 范围内的元素。
     * 并在 location 索引处添加一个新标记 (result)。
     * @param location 移除的起始索引
     * @param counts 移除的元素数量
     * @param result 替换的结果标记
     */
    private void reduce(int location, int counts, Token result) {
        for (int i = 0; i < counts; i++)
            stack.remove(location);
        stack.add(location, result);
    }

    /**
     * 根据表达式类型计算结果。
     * 支持以下类型：
     *   1. 十进制常量
     *   2. 布尔常量
     *   3. 十进制数操作符，例如 + - * /
     *   4. 布尔操作符，例如 &amp; |
     *   5. 一元操作符，例如负号和非
     *   6. 括号，包含常量和函数
     *   7. 关系运算符，例如 &gt; &gt;=
     *   8. 三元操作符，例如 ?:
     * @param type 表达式类型，例如 decimal_operator
     * @return 规约后的新栈。
     * @throws ExpressionException 各种表达式计算可能抛出的异常。
     */
    public ArrayList <Token> calculate(String type) throws ExpressionException {
        int i = getTerminalLocation(stack.size() - 1);
        Token result = null;
        switch(type) {
            case "decimal":
                result = new DecimalExpr(stack.get(i)).expr();
                reduce(i, 1, result);
                break;

            case "boolean":
                result = new BooleanExpr(stack.get(i)).expr();
                reduce(i, 1, result);
                break;

            case "decimal_operator":
                if (i - 1 < 0 || i + 1 >= length)
                    throw new MissingOperandException();
                if (stack.get(i - 1).isTerminal() || stack.get(i + 1).isTerminal())
                    throw new MissingOperandException();
                result = new DecimalOperatorExpr(stack.get(i), stack.get(i - 1), stack.get(i + 1)).expr();
                reduce(i - 1, 3, result);
                break;

            case "unary":
                if (i + 1 >= length)
                    throw new MissingOperandException();
                result = new UnaryExpr(stack.get(i + 1)).expr();
                reduce(i, 2, result);
                break;
                
            case "parenthesis":
                int left = findLeftParenthesis();
                ArrayList <Token> args = new ArrayList <Token> ();
                for (int j = left + 1; j < i; j++)
                    args.add(stack.get(j));
                if (left > 0 && stack.get(left - 1).getType().equals("function")) {
                    result = new FunctionExpr(stack.get(left - 1), args).expr();
                    reduce(left - 1, i - left + 2, result);
                }
                else {
                    if (args.size() <= 0)
                        throw new MissingOperandException();
                    if (args.size() > 1)
                        throw new SemanticException();
                    if (args.get(0).isTerminal())
                        throw new MissingOperandException();

                    result = args.get(0);
                    reduce(left, 3, result);
                }
                break;

            case "relation":
                if (i - 1 < 0 || i + 1 >= length)
                    throw new MissingOperandException();
                if (stack.get(i - 1).isTerminal() || stack.get(i + 1).isTerminal())
                    throw new MissingOperandException();
                result = new RelationExpr(stack.get(i), stack.get(i - 1), stack.get(i + 1)).expr();
                reduce(i - 1, 3, result);
                break;

            case "boolean_operator":
                if (i - 1 < 0 || i + 1 >= length)
                    throw new MissingOperandException();
                if (stack.get(i - 1).isTerminal() || stack.get(i + 1).isTerminal())
                    throw new MissingOperandException();
                result = new BooleanOperatorExpr(stack.get(i), stack.get(i - 1), stack.get(i + 1)).expr();
                reduce(i - 1, 3, result);
                break;

            case "trinary":
                if (i - 1 < 0 || i + 1 >= length)
                    throw new MissingOperandException();
                int j = getTerminalLocation(i - 1);
                if (j - 1 < 0 || i - j != 2)
                    throw new MissingOperandException();
                if (stack.get(j - 1).isTerminal() || stack.get(j + 1).isTerminal() 
                    || stack.get(i + 1).isTerminal())
                    throw new MissingOperandException();           
                result = new TrinaryExpr(stack.get(j - 1), stack.get(j + 1), stack.get(i + 1)).expr();
                reduce(j - 1, i + 1 - (j - 1) + 1, result);
                break;

            default:
                throw new MissingOperatorException();
        }
        return new ArrayList <Token>(stack);
    }
}
