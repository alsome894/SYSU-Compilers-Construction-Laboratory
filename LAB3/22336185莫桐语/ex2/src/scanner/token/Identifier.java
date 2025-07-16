package scanner.token;

/**
 * 表示标识符类型的词法单元，包含标识符字符串。
 */
public class Identifier extends Token {
    private String lexeme;

    /**
     * 构造函数。
     * @param line 行号
     * @param column 列号
     * @param lexeme 标识符字符串
     */
    public Identifier(int line, int column, String lexeme) {
        super(TokenType.ID, line, column);
        this.lexeme = lexeme;
    }

    /**
     * 获取标识符字符串。
     * @return 标识符
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * 返回标识符词法单元的字符串表示。
     * @return 字符串
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s", "[" + super.getLine() + "," + super.getColumn() + "]", super.getType().name(), "(" + lexeme + ")");
    }
}