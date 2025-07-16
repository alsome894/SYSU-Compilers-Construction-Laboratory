package parser.type;

/**
 * 表示类型别名（typedef）的类。
 */
public class TypedefType extends Type {
    private Type type;

    /**
     * 构造一个类型别名，指定实际类型。
     */
    public TypedefType(Type type) {
        super("typedef");
        this.type = type;
    }

    /**
     * 获取实际类型。
     */
    public Type getType() {
        return type;
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则返回true。
     * 否则仅当参数也是TypedefType且实际类型相等时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof TypedefType)) {
            return false;
        }
        TypedefType other = (TypedefType) obj;
        return type.equals(other.type);
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
        return "typedef " + type;
    }
}
