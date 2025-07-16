package scanner.token;

/**
 * Keyword 类用于表示各种关键字类型的词法单元。
 */
public class Keyword extends Token {
    /**
     * 构造函数，指定类型、行号和列号
     */
    private Keyword(TokenType type, int line, int column) {
        super(type, line, column);
    }

    /**
     * 构造函数，仅指定类型
     */
    private Keyword(TokenType type) {
        super(type);
    }

    // 以下为各种关键字的静态工厂方法

    /** bool 类型关键字 */
    public static Keyword typeBool(int line, int column) {
        return new Keyword(TokenType.TYPE_BOOL, line, column);
    }

    public static Keyword typeBool() {
        return new Keyword(TokenType.TYPE_BOOL);
    }

    /** integer 类型关键字 */
    public static Keyword typeInt(int line, int column) {
        return new Keyword(TokenType.TYPE_INTEGER, line, column);
    }

    public static Keyword typeInt() {
        return new Keyword(TokenType.TYPE_INTEGER);
    }

    /** record 类型关键字 */
    public static Keyword typeRecord(int line, int column) {
        return new Keyword(TokenType.TYPE_RECORD, line, column);
    }

    public static Keyword typeRecord() {
        return new Keyword(TokenType.TYPE_RECORD);
    }

    /** array 类型关键字 */
    public static Keyword typeArray(int line, int column) {
        return new Keyword(TokenType.TYPE_ARRAY, line, column);
    }

    public static Keyword typeArray() {
        return new Keyword(TokenType.TYPE_ARRAY);
    }

    /** of 关键字 */
    public static Keyword symOf(int line, int column) {
        return new Keyword(TokenType.OF, line, column);
    }

    public static Keyword symOf() {
        return new Keyword(TokenType.OF);
    }

    /** do 关键字 */
    public static Keyword symDo(int line, int column) {
        return new Keyword(TokenType.DO, line, column);
    }

    public static Keyword symDo() {
        return new Keyword(TokenType.DO);
    }

    /** while 关键字 */
    public static Keyword symWhile(int line, int column) {
        return new Keyword(TokenType.WHILE, line, column);
    }

    public static Keyword symWhile() {
        return new Keyword(TokenType.WHILE);
    }

    /** if 关键字 */
    public static Keyword symIf(int line, int column) {
        return new Keyword(TokenType.IF, line, column);
    }

    public static Keyword symIf() {
        return new Keyword(TokenType.IF);
    }

    /** else 关键字 */
    public static Keyword symElse(int line, int column) {
        return new Keyword(TokenType.ELSE, line, column);
    }

    public static Keyword symElse() {
        return new Keyword(TokenType.ELSE);
    }

    /** elif 关键字 */
    public static Keyword symElif(int line, int column) {
        return new Keyword(TokenType.ELIF, line, column);
    }

    public static Keyword symElif() {
        return new Keyword(TokenType.ELIF);
    }

    /** then 关键字 */
    public static Keyword symThen(int line, int column) {
        return new Keyword(TokenType.THEN, line, column);
    }

    public static Keyword symThen() {
        return new Keyword(TokenType.THEN);
    }

    /** const 关键字 */
    public static Keyword symConst(int line, int column) {
        return new Keyword(TokenType.CONST, line, column);
    }

    public static Keyword symConst() {
        return new Keyword(TokenType.CONST);
    }

    /** var 关键字 */
    public static Keyword symVar(int line, int column) {
        return new Keyword(TokenType.VAR, line, column);
    }

    public static Keyword symVar() {
        return new Keyword(TokenType.VAR);
    }

    /** type 关键字 */
    public static Keyword symType(int line, int column) {
        return new Keyword(TokenType.TYPE, line, column);
    }

    public static Keyword symType() {
        return new Keyword(TokenType.TYPE);
    }

    /** module 关键字 */
    public static Keyword symModule(int line, int column) {
        return new Keyword(TokenType.MODULE, line, column);
    }

    public static Keyword symModule() {
        return new Keyword(TokenType.MODULE);
    }

    /** procedure 关键字 */
    public static Keyword symProcedure(int line, int column) {
        return new Keyword(TokenType.PROCEDURE, line, column);
    }

    public static Keyword symProcedure() {
        return new Keyword(TokenType.PROCEDURE);
    }

    /** begin 关键字 */
    public static Keyword symBegin(int line, int column) {
        return new Keyword(TokenType.BEGIN, line, column);
    }

    public static Keyword symBegin() {
        return new Keyword(TokenType.BEGIN);
    }

    /** end 关键字 */
    public static Keyword symEnd(int line, int column) {
        return new Keyword(TokenType.END, line, column);
    }

    public static Keyword symEnd() {
        return new Keyword(TokenType.END);
    }

    /** read 关键字 */
    public static Keyword symRead(int line, int column) {
        return new Keyword(TokenType.READ, line, column);
    }

    public static Keyword symRead() {
        return new Keyword(TokenType.READ);
    }

    /** write 关键字 */
    public static Keyword symWrite(int line, int column) {
        return new Keyword(TokenType.WRITE, line, column);
    }

    public static Keyword symWrite() {
        return new Keyword(TokenType.WRITE);
    }

    /** writeln 关键字 */
    public static Keyword symWriteln(int line, int column) {
        return new Keyword(TokenType.WRITELN, line, column);
    }

    public static Keyword symWriteln() {
        return new Keyword(TokenType.WRITELN);
    }
}
