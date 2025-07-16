package scanner.token;

/**
 * 词法分析器中的Token对象，包含类型、行号、列号等信息。
 */
public class Token extends java_cup.runtime.Symbol {
    private TokenType type;       // Token类型
    private Integer line;         // Token所在行
    private Integer column;       // Token所在列

    /**
     * 构造一个带有行号和列号的Token对象。
     * @param type Token类型
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
     * 构造一个不带位置信息的Token对象。
     * @param type Token类型
     */
    public Token(TokenType type) {
        super(type.ordinal(), type);
        this.type = type;
        this.line = null;
        this.column = null;
    }

    /**
     * 获取Token类型。
     * @return Token类型
     */
    public TokenType getType() {
        return type;
    }

    /**
     * 获取Token所在行号。
     * @return 行号
     */
    public Integer getLine() {
        return line;
    }

    /**
     * 获取Token所在列号。
     * @return 列号
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * 返回Token的字符串表示。
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s", "[" + getLine() + "," + getColumn() + "]", getType().name());
    }

    /**
     * 判断两个Token是否类型相同。
     * @param obj 另一个对象
     * @return 类型相同返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Token)) {
            return false;
        }
        Token other = (Token) obj;
        return type == other.type;
    }
}