package scanner.token;

/**
 * 表示关键字类型的词法单元。
 */
public class Keyword extends Token {
    /**
     * 构造函数，私有化，仅通过静态方法创建。
     */
    private Keyword(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 创建 TYPE_BOOL 关键字。
     */
    public static Keyword typeBool(int line, int column) {
        return new Keyword(TokenType.TYPE_BOOL, line, column);
    }

    /**
     * 创建 TYPE_INTEGER 关键字。
     */
    public static Keyword typeInt(int line, int column) {
        return new Keyword(TokenType.TYPE_INTEGER, line, column);
    }

    /**
     * 创建 TYPE_RECORD 关键字。
     */
    public static Keyword typeRecord(int line, int column) {
        return new Keyword(TokenType.TYPE_RECORD, line, column);
    }

    /**
     * 创建 TYPE_ARRAY 关键字。
     */
    public static Keyword typeArray(int line, int column) {
        return new Keyword(TokenType.TYPE_ARRAY, line, column);
    }

    /**
     * 创建 OF 关键字。
     */
    public static Keyword symOf(int line, int column) {
        return new Keyword(TokenType.OF, line, column);
    }

    /**
     * 创建 DO 关键字。
     */
    public static Keyword symDo(int line, int column) {
        return new Keyword(TokenType.DO, line, column);
    }

    /**
     * 创建 WHILE 关键字。
     */
    public static Keyword symWhile(int line, int column) {
        return new Keyword(TokenType.WHILE, line, column);
    }

    /**
     * 创建 IF 关键字。
     */
    public static Keyword symIf(int line, int column) {
        return new Keyword(TokenType.IF, line, column);
    }

    /**
     * 创建 ELSE 关键字。
     */
    public static Keyword symElse(int line, int column) {
        return new Keyword(TokenType.ELSE, line, column);
    }

    /**
     * 创建 ELIF 关键字。
     */
    public static Keyword symElif(int line, int column) {
        return new Keyword(TokenType.ELIF, line, column);
    }

    /**
     * 创建 THEN 关键字。
     */
    public static Keyword symThen(int line, int column) {
        return new Keyword(TokenType.THEN, line, column);
    }

    /**
     * 创建 CONST 关键字。
     */
    public static Keyword symConst(int line, int column) {
        return new Keyword(TokenType.CONST, line, column);
    }

    /**
     * 创建 VAR 关键字。
     */
    public static Keyword symVar(int line, int column) {
        return new Keyword(TokenType.VAR, line, column);
    }

    /**
     * 创建 TYPE 关键字。
     */
    public static Keyword symType(int line, int column) {
        return new Keyword(TokenType.TYPE, line, column);
    }

    /**
     * 创建 MODULE 关键字。
     */
    public static Keyword symModule(int line, int column) {
        return new Keyword(TokenType.MODULE, line, column);
    }

    /**
     * 创建 PROCEDURE 关键字。
     */
    public static Keyword symProcedure(int line, int column) {
        return new Keyword(TokenType.PROCEDURE, line, column);
    }

    /**
     * 创建 BEGIN 关键字。
     */
    public static Keyword symBegin(int line, int column) {
        return new Keyword(TokenType.BEGIN, line, column);
    }

    /**
     * 创建 END 关键字。
     */
    public static Keyword symEnd(int line, int column) {
        return new Keyword(TokenType.END, line, column);
    }

    /**
     * 创建 READ 关键字。
     */
    public static Keyword symRead(int line, int column) {
        return new Keyword(TokenType.READ, line, column);
    }

    /**
     * 创建 WRITE 关键字。
     */
    public static Keyword symWrite(int line, int column) {
        return new Keyword(TokenType.WRITE, line, column);
    }

    /**
     * 创建 WRITELN 关键字。
     */
    public static Keyword symWriteln(int line, int column) {
        return new Keyword(TokenType.WRITELN, line, column);
    }
}
