import java.util.*;

/**
 * 中缀表达式到后缀表达式的转换器，支持错误恢复机制。
 * <p>
 * 实现基于状态机的解析算法，语法规则为：
 * <pre>
 * expr → term { ('+' | '-') term }
 * term → digit
 * </pre>
 * 特性：
 * <ul>
 *   <li>支持多错误收集，遇到错误后继续解析</li>
 *   <li>错误发生后冻结输出，保留有效前缀</li>
 *   <li>自动跳过空白字符并记录警告</li>
 * </ul>
 */
public class Parser {
    /**
     * 原始输入表达式字符串
     */
    private final String input;
    
    /**
     * 当前字符索引0-based，用于内部处理
     */
    private int lookahead = 0;
    
    /**
     * 当前字符位置1-based，用于错误报告
     */
    private int pos = 0;
    
    /**
     * 最终输出缓冲
     */
    private final StringBuilder fullOutput = new StringBuilder();
    
    /**
     * 收集到的错误列表
     */
    private final List<ParseError> errors = new ArrayList<>();
    
    /**
     * 输出冻结标志
     */
    private boolean freezeOutput = false;
    
    /**
     * 待处理的运算符
     */
    private char pendingOperator = 0;

    /**
     * 解析器状态枚举
     * <ul>
     *   <li>EXPECT_OPERAND - 期待操作数（数字）</li>
     *   <li>EXPECT_OPERATOR - 期待运算符（+/-）</li>
     * </ul>
     */
    private enum State { 
        /** 当前应出现操作数（数字） */
        EXPECT_OPERAND, 
        /** 当前应出现运算符（+或-） */
        EXPECT_OPERATOR 
    }
    
    /**
     * 当前解析器状态
     */
    private State state = State.EXPECT_OPERAND;

    /**
     * 初始化解析器实例
     * @param input 待解析的中缀表达式字符串
     * @throws NullPointerException 输入为null时抛出
     */
    public Parser(String input) {
        this.input = Objects.requireNonNull(input, "Input must not be null");
    }

    /**
     * 跳过连续空白字符并记录错误
     * <p>
     * 每跳过一个空白字符记录一个非严重错误</p>
     */
    private void skipWhitespace() {
        while (lookahead < input.length() && Character.isWhitespace(input.charAt(lookahead))) {
            pos = lookahead + 1; // 更新用户可见位置
            lookahead++;
            recordError("Whitespace detected", false, false); // 记录非严重错误
        }
    }

    /**
     * 记录错误并更新冻结状态
     * @param msg 错误描述信息
     * @param isSyntaxError 是否属于语法错误
     * @param isFreeze 是否触发输出冻结
     * @throws NullPointerException 当msg为null时抛出
     */
    private void recordError(String msg, boolean isSyntaxError, boolean isFreeze) {
        if (isSyntaxError) {
            errors.add(new ParseError(pos, msg, "Syntax Error"));
        }
        else{
            errors.add(new ParseError(pos, msg, "Lexical Error"));
        }
        if (isFreeze) {
            freezeOutput = true;
        }
    }

    /**
     * 执行完整的解析过程
     * <p>
     * 解析流程：
     * <ol>
     *   <li>初始化状态机</li>
     *   <li>循环处理每个字符</li>
     *   <li>根据当前状态处理输入</li>
     *   <li>错误发生时冻结输出</li>
     * </ol>
     * 注意：此方法可能抛出运行时异常
     */
    public void parse() {
        while (lookahead < input.length()) {
            skipWhitespace();
            if (lookahead >= input.length()) break;

            final char ch = input.charAt(lookahead);
            pos = lookahead + 1; // 更新用户可见位置

            if (state == State.EXPECT_OPERAND) {
                handleOperandState(ch);
            } else {
                handleOperatorState(ch);
            }
        }

        // 处理输入结束后的悬挂运算符
        if (state == State.EXPECT_OPERAND && pendingOperator != 0) {
            recordError("Missing operand after operator '" + pendingOperator + "'", true, true);
        }
    }

    /**
     * 处理期待操作数的状态
     * @param ch 当前字符
     */
    private void handleOperandState(char ch) {
        if (Character.isDigit(ch)) {
            processDigit(ch);
        } else if (isOperator(ch)) {
            recordError("Missing digit before operator '" + ch + "'", true, true);
            lookahead++;
        } else {
            recordError("Unexpected character '" + ch + "'", false, true);
            lookahead++;
        }
    }

    /**
     * 处理期待运算符的状态
     * @param ch 当前字符
     */
    private void handleOperatorState(char ch) {
        if (isOperator(ch)) {
            processOperator(ch);
        } else if (Character.isDigit(ch)) {
            recordError("Missing operator before digit '" + ch + "'", true, true);
            lookahead++;
        } else {
            recordError("Unexpected character '" + ch + "'", false, true);
            lookahead++;
        }
    }

    /**
     * 处理数字字符
     * @param ch 当前数字字符
     */
    private void processDigit(char ch) {
        if (!freezeOutput) {
            fullOutput.append(ch);
        }
        state = State.EXPECT_OPERATOR;
        flushPendingOperator();
        lookahead++;
    }

    /**
     * 处理运算符字符
     * @param ch 当前运算符
     */
    private void processOperator(char ch) {
        pendingOperator = ch;
        state = State.EXPECT_OPERAND;
        lookahead++;
    }

    /**
     * 输出缓存的运算符
     */
    private void flushPendingOperator() {
        if (pendingOperator != 0 && !freezeOutput) {
            fullOutput.append(pendingOperator);
            pendingOperator = 0;
        }
    }

    /**
     * 判断字符是否为合法运算符
     * @param ch 待检测字符
     * @return 是运算符返回true
     */
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-';
    }

    /**
     * 获取转换后的后缀表达式
     * @return 有效转换结果
     */
    public String getOutput() {
        return fullOutput.toString();
    }

    /**
     * 获取解析过程中发现的所有错误
     * @return 不可修改的错误列表
     */
    public List<ParseError> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}