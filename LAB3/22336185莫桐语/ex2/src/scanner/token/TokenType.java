package scanner.token;

/**
 * 表示 Oberon 语言的所有词法单元类型的枚举。
 */
public enum TokenType {
	/* 保留字/关键字 */
    TYPE_BOOL,      // BOOLEAN
    TYPE_INTEGER,   // INTEGER
    TYPE_RECORD,    // RECORD
    TYPE_ARRAY,     // ARRAY
    OF,             // OF

    DO,             // DO
    WHILE,          // WHILE
    IF,             // IF
    ELSE,           // ELSE
    ELIF,           // ELIF
    THEN,           // THEN

    CONST,          // CONST
	VAR,            // VAR
	TYPE,           // TYPE

    MODULE,         // MODULE
    PROCEDURE,      // PROCEDURE
    BEGIN,          // BEGIN
    END,            // END

    DIV,            // DIV
    MOD,            // MOD
    OR,             // OR

    READ,           // READ
    WRITE,          // WRITE
    WRITELN,        // WRITELN

	/* 其他符号 */
    LPAR,           // (
    RPAR,           // )
    LBRACK,         // [
    RBRACK,         // ]
    COLON,          // :
    SEMICOLON,      // ;
    COMMA,          // ,
    DOT,            // .

	/* 操作符 */
    ASSIGN,         // :=
    EQ,             // =
    GT,             // >
    LT,             // <
    LTEQ,           // <=
    GTEQ,           // >=
    NEQ,            // #
    AND,            // &
    NOT,            // ~
    PLUS,           // +
    MINUS,          // -
    MULT,           // *

	/* 字面量与标识符 */
    INTEGER,        // 整形字面量
    COMMENT,        // 注释
    ID,             // 标识符

    EOF             // 文件结束
}