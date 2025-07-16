package parser.env;

import java.util.*;
import scanner.token.*;
import parser.type.*;

/**
 * 表示一个声明，包括标识符、类型和扩展属性。
 */
public class Declaration {
    public static enum Modifiers {
        CONST,
        VAR,
    }

    private Identifier id;
    private String lexeme;
    private Integer line;
    private Integer column;
    private Type type;
    private Hashtable<String, Object> extAttrs;

    /**
     * 构造一个声明对象。
     * @param id 标识符
     * @param type 类型
     * @param extAttrs 扩展属性
     */
    public Declaration(Identifier id, Type type, Hashtable<String, Object> extAttrs) {
        this.id = id;
        this.lexeme = id.getLexeme();
        this.line = id.getLine();
        this.column = id.getColumn();
        this.type = type;
        this.extAttrs = extAttrs == null ? new Hashtable<String, Object>() : extAttrs;
    }

    /**
     * 获取标识符。
     * @return 标识符
     */
    public Identifier getId() {
        return id;
    }

    /**
     * 获取词素。
     * @return 词素
     */
    public String getLexeme() {
        return lexeme;
    }

    /**
     * 获取所在行号。
     * @return 行号
     */
    public Integer getLine() {
        return line;
    }

    /**
     * 获取所在列号。
     * @return 列号
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * 获取类型。
     * @return 类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 获取扩展属性。
     * @param key 属性名
     * @return 属性值
     */
    public Object getExtAttr(String key) {
        return extAttrs.get(key);
    }

    /**
     * 判断两个声明对象是否相等。
     * @param obj 比较对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Declaration)) {
            return false;
        }
        Declaration other = (Declaration) obj;
        return type.equals(other.type) && extAttrs.equals(other.extAttrs);
    }
    
}
