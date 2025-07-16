package scanner.token;

/**
 * Symbol 类用于表示各种符号类型的词法单元，如括号、分号、逗号等。
 */
public class Symbol extends Token {
    /**
     * 构造函数，指定类型、行号和列号
     */
    private Symbol(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 构造函数，仅指定类型
     */
    private Symbol(TokenType type) {
        super(type);
    }

    // 以下为各种符号的静态工厂方法

    /** 左括号 ( */
    public static Symbol lpar(int line, int column) {
        return new Symbol(TokenType.LPAR, line, column);
    }

    public static Symbol lpar() {
        return new Symbol(TokenType.LPAR);
    }

    /** 右括号 ) */
    public static Symbol rpar(int line, int column) {
        return new Symbol(TokenType.RPAR, line, column);
    }

    public static Symbol rpar() {
        return new Symbol(TokenType.RPAR);
    }

    /** 左中括号 [ */
    public static Symbol lbrack(int line, int column) {
        return new Symbol(TokenType.LBRACK, line, column);
    }

    public static Symbol lbrack() {
        return new Symbol(TokenType.LBRACK);
    }

    /** 右中括号 ] */
    public static Symbol rbrack(int line, int column) {
        return new Symbol(TokenType.RBRACK, line, column);
    }

    public static Symbol rbrack() {
        return new Symbol(TokenType.RBRACK);
    }

    /** 冒号 : */
    public static Symbol colon(int line, int column) {
        return new Symbol(TokenType.COLON, line, column);
    }

    public static Symbol colon() {
        return new Symbol(TokenType.COLON);
    }

    /** 分号 ; */
    public static Symbol semicol(int line, int column) {
        return new Symbol(TokenType.SEMICOLON, line, column);
    }

    public static Symbol semicol() {
        return new Symbol(TokenType.SEMICOLON);
    }

    /** 逗号 , */
    public static Symbol comma(int line, int column) {
        return new Symbol(TokenType.COMMA, line, column);
    }

    public static Symbol comma() {
        return new Symbol(TokenType.COMMA);
    }

    /** 点 . */
    public static Symbol dot(int line, int column) {
        return new Symbol(TokenType.DOT, line, column);
    }

    public static Symbol dot() {
        return new Symbol(TokenType.DOT);
    }

    /** 文件结束符 */
    public static Symbol eof(int line, int column) {
        return new Symbol(TokenType.EOF, line, column);
    }

    public static Symbol eof() {
        return new Symbol(TokenType.EOF);
    }
}