package scanner.token;

/**
 * Operator 类用于表示各种操作符类型的词法单元，如加减乘除、关系运算等。
 */
public class Operator extends Token {
    /**
     * 构造函数，指定类型、行号和列号
     */
    private Operator(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 构造函数，仅指定类型
     */
    private Operator(TokenType type) {
        super(type);
    }

    // 以下为各种操作符的静态工厂方法

    /** 加号 + */
    public static Operator plus(int line, int column) {
        return new Operator(TokenType.PLUS, line, column);
    }

    public static Operator plus() {
        return new Operator(TokenType.PLUS);
    }

    /** 减号 - */
    public static Operator minus(int line, int column) {
        return new Operator(TokenType.MINUS, line, column);
    }

    public static Operator minus() {
        return new Operator(TokenType.MINUS);
    }

    /** 乘号 * */
    public static Operator mult(int line, int column) {
        return new Operator(TokenType.MULT, line, column);
    }

    public static Operator mult() {
        return new Operator(TokenType.MULT);
    }

    /** 除法 div */
    public static Operator div(int line, int column) {
        return new Operator(TokenType.DIV, line, column);
    }

    public static Operator div() {
        return new Operator(TokenType.DIV);
    }

    /** 取模 mod */
    public static Operator mod(int line, int column) {
        return new Operator(TokenType.MOD, line, column);
    }

    public static Operator mod() {
        return new Operator(TokenType.MOD);
    }

    /** 逻辑与 and */
    public static Operator and(int line, int column) {
        return new Operator(TokenType.AND, line, column);
    }

    public static Operator and() {
        return new Operator(TokenType.AND);
    }

    /** 逻辑或 or */
    public static Operator or(int line, int column) {
        return new Operator(TokenType.OR, line, column);
    }

    public static Operator or() {
        return new Operator(TokenType.OR);
    }

    /** 逻辑非 not */
    public static Operator not(int line, int column) {
        return new Operator(TokenType.NOT, line, column);
    }

    public static Operator not() {
        return new Operator(TokenType.NOT);
    }

    /** 等于 = */
    public static Operator eq(int line, int column) {
        return new Operator(TokenType.EQ, line, column);
    }

    public static Operator eq() {
        return new Operator(TokenType.EQ);
    }

    /** 不等于 <> */
    public static Operator neq(int line, int column) {
        return new Operator(TokenType.NEQ, line, column);
    }

    public static Operator neq() {
        return new Operator(TokenType.NEQ);
    }

    /** 小于 < */
    public static Operator lt(int line, int column) {
        return new Operator(TokenType.LT, line, column);
    }

    public static Operator lt() {
        return new Operator(TokenType.LT);
    }

    /** 小于等于 <= */
    public static Operator lteq(int line, int column) {
        return new Operator(TokenType.LTEQ, line, column);
    }

    public static Operator lteq() {
        return new Operator(TokenType.LTEQ);
    }

    /** 大于 > */
    public static Operator gt(int line, int column) {
        return new Operator(TokenType.GT, line, column);
    }

    public static Operator gt() {
        return new Operator(TokenType.GT);
    }

    /** 大于等于 >= */
    public static Operator gteq(int line, int column) {
        return new Operator(TokenType.GTEQ, line, column);
    }

    public static Operator gteq() {
        return new Operator(TokenType.GTEQ);
    }

    /** 赋值符号 := */
    public static Operator assign(int line, int column) {
        return new Operator(TokenType.ASSIGN, line, column);
    }

    public static Operator assign() {
        return new Operator(TokenType.ASSIGN);
    }
}