package scanner.token;

/**
 * 表示符号类型的词法单元。
 */
public class Symbol extends Token {
    /**
     * 构造函数，私有化，仅通过静态方法创建。
     */
    private Symbol(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 创建左括号符号。
     */
    public static Symbol lpar(int line, int column) {
        return new Symbol(TokenType.LPAR, line, column);
    }

    /**
     * 创建右括号符号。
     */
    public static Symbol rpar(int line, int column) {
        return new Symbol(TokenType.RPAR, line, column);
    }

    /**
     * 创建左中括号符号。
     */
    public static Symbol lbrack(int line, int column) {
        return new Symbol(TokenType.LBRACK, line, column);
    }

    /**
     * 创建右中括号符号。
     */
    public static Symbol rbrack(int line, int column) {
        return new Symbol(TokenType.RBRACK, line, column);
    }

    /**
     * 创建冒号符号。
     */
    public static Symbol colon(int line, int column) {
        return new Symbol(TokenType.COLON, line, column);
    }

    /**
     * 创建分号符号。
     */
    public static Symbol semicol(int line, int column) {
        return new Symbol(TokenType.SEMICOLON, line, column);
    }

    /**
     * 创建逗号符号。
     */
    public static Symbol comma(int line, int column) {
        return new Symbol(TokenType.COMMA, line, column);
    }

    /**
     * 创建点号符号。
     */
    public static Symbol dot(int line, int column) {
        return new Symbol(TokenType.DOT, line, column);
    }

    /**
     * 创建文件结束符号。
     */
    public static Symbol eof(int line, int column) {
        return new Symbol(TokenType.EOF, line, column);
    }
}