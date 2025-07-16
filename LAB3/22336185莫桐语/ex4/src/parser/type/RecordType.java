package parser.type;

import java.util.*;
import parser.env.*;

/**
 * 表示记录类型（结构体）的类。
 */
public class RecordType extends Type {
    private Hashtable<String, Declaration> fields;

    /**
     * 构造一个记录类型，指定字段表。
     */
    public RecordType(Hashtable<String, Declaration> fields) {
        super("record");
        this.fields = new Hashtable<String, Declaration>();
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            this.fields.put(key.toLowerCase(), fields.get(key));
        }
    }

    /**
     * 获取字段表。
     */
    public Hashtable<String, Declaration> getFields() {
        return fields;
    }

    /**
     * 判断类型是否相等。
     * 仅当参数也是RecordType且字段名和类型完全一致时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordType)) {
            return false;
        }
        RecordType other = (RecordType) obj;
        if (fields.size() != other.fields.size()) {
            return false;
        }
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (!other.fields.containsKey(key)) {
                return false;
            }
            if (!fields.get(key).getType().equals(other.fields.get(key).getType())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * 返回类型的字符串表示。
     */
    @Override
    public String toString() {
        String result = "{ ";
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            result += key + ": " + fields.get(key).getType();
            if (keys.hasMoreElements()) {
                result += ", ";
            }
        }
        return result + " }";
    }
}
