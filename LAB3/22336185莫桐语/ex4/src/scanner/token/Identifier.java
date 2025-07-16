package scanner.token;

/**
 * Identifier 类用于表示标识符类型的词法单元。
 */
public class Identifier extends Token {
    private String lexeme;

    /**
     * 构造函数，指定行号、列号和标识符字符串
     */
    public Identifier(int line, int column, String lexeme) {
        super(TokenType.ID, line, column);
        this.lexeme = lexeme;
    }

    /**
     * 构造函数，仅指定标识符字符串
     */
    public Identifier(String lexeme) {
        super(TokenType.ID);
        this.lexeme = lexeme;
    }

    /**
     * 默认构造函数
     */
    public Identifier() {
        super(TokenType.ID);
        this.lexeme = null;
    }

    /**
     * 获取标识符字符串
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * 转为字符串表示
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s", "[" + super.getLine() + "," + super.getColumn() + "]", super.getType().name(), "(" + lexeme + ")");
    }
}