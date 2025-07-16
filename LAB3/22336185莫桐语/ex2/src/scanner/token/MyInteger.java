package scanner.token;

/**
 * 表示整数类型的词法单元，包含整数值。
 */
public class MyInteger extends Token {
    private int lex_val;

    /**
     * 构造函数。
     * @param line 行号
     * @param column 列号
     * @param value 整数值
     */
    public MyInteger(int line, int column, int value) {
        super(TokenType.INTEGER, line, column);
        this.lex_val = value;
    }

    /**
     * 获取整数值。
     * @return 整数值
     */
    public int getLex_val() {
        return lex_val;
    }

    /**
     * 返回整数词法单元的字符串表示。
     * @return 字符串
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s", "[" + super.getLine() + "," + super.getColumn() + "]", super.getType().name(), "(" + lex_val + ")");
    }
}
