package scanner.token;

/**
 * 词法单元基类，包含类型、行号和列号等信息。
 */
public class Token extends java_cup.runtime.Symbol {
    private TokenType type; // Token类型
    private int line;       // Token所在行
    private int column;     // Token所在列

    /**
     * 构造函数。
     * @param type 词法单元类型
     * @param line 行号
     * @param column 列号
     */
    public Token(TokenType type, int line, int column) {
        super(type.ordinal(), type);
        this.type = type;
        this.line = line;
        this.column = column;
    }

    /**
     * 获取词法单元类型。
     * @return 类型
     */
    public TokenType getType() {
        return type;
    }

    /**
     * 获取行号。
     * @return 行号
     */
    public int getLine() {
        return line;
    }

    /**
     * 获取列号。
     * @return 列号
     */
    public int getColumn() {
        return column;
    }

    /**
     * 返回词法单元的字符串表示。
     * @return 字符串
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s", "[" + getLine() + "," + getColumn() + "]", getType().name());
    }
}