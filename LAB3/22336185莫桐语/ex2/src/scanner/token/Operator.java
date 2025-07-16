package scanner.token;

/**
 * 表示操作符类型的词法单元。
 */
public class Operator extends Token {
    /**
     * 构造函数，私有化，仅通过静态方法创建。
     */
    private Operator(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 创建加号操作符。
     */
    public static Operator plus(int line, int column) {
        return new Operator(TokenType.PLUS, line, column);
    }

    /**
     * 创建减号操作符。
     */
    public static Operator minus(int line, int column) {
        return new Operator(TokenType.MINUS, line, column);
    }

    /**
     * 创建乘号操作符。
     */
    public static Operator mult(int line, int column) {
        return new Operator(TokenType.MULT, line, column);
    }

    /**
     * 创建除法操作符。
     */
    public static Operator div(int line, int column) {
        return new Operator(TokenType.DIV, line, column);
    }

    /**
     * 创建取模操作符。
     */
    public static Operator mod(int line, int column) {
        return new Operator(TokenType.MOD, line, column);
    }

    /**
     * 创建与操作符。
     */
    public static Operator and(int line, int column) {
        return new Operator(TokenType.AND, line, column);
    }

    /**
     * 创建或操作符。
     */
    public static Operator or(int line, int column) {
        return new Operator(TokenType.OR, line, column);
    }

    /**
     * 创建非操作符。
     */
    public static Operator not(int line, int column) {
        return new Operator(TokenType.NOT, line, column);
    }

    /**
     * 创建等于操作符。
     */
    public static Operator eq(int line, int column) {
        return new Operator(TokenType.EQ, line, column);
    }

    /**
     * 创建不等于操作符。
     */
    public static Operator neq(int line, int column) {
        return new Operator(TokenType.NEQ, line, column);
    }

    /**
     * 创建小于操作符。
     */
    public static Operator lt(int line, int column) {
        return new Operator(TokenType.LT, line, column);
    }

    /**
     * 创建小于等于操作符。
     */
    public static Operator lteq(int line, int column) {
        return new Operator(TokenType.LTEQ, line, column);
    }

    /**
     * 创建大于操作符。
     */
    public static Operator gt(int line, int column) {
        return new Operator(TokenType.GT, line, column);
    }

    /**
     * 创建大于等于操作符。
     */
    public static Operator gteq(int line, int column) {
        return new Operator(TokenType.GTEQ, line, column);
    }

    /**
     * 创建赋值操作符。
     */
    public static Operator assign(int line, int column) {
        return new Operator(TokenType.ASSIGN, line, column);
    }
}