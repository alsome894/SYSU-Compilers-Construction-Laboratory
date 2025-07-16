package scanner.token;

/**
 * MyInteger 类用于表示整数字面量类型的词法单元。
 */
public class MyInteger extends Token {
    private Integer lex_val;

    /**
     * 构造函数，指定行号、列号和整数值
     */
    public MyInteger(int line, int column, int value) {
        super(TokenType.INTEGER, line, column);
        this.lex_val = value;
    }

    /**
     * 默认构造函数
     */
    public MyInteger() {
        super(TokenType.INTEGER);
        this.lex_val = null;
    }

    /**
     * 获取整数值
     */
    public int getLex_val() {
        return lex_val;
    }

    /**
     * 转为字符串表示
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s", "[" + super.getLine() + "," + super.getColumn() + "]", super.getType().name(), "(" + lex_val + ")");
    }
}
