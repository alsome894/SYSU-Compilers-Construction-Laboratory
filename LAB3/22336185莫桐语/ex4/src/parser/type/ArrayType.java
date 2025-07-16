package parser.type;

/**
 * 表示数组类型的类。
 */
public class ArrayType extends Type {
    private Type elementType;

    /**
     * 构造一个数组类型，指定元素类型。
     */
    public ArrayType(Type elementType) {
        super("array");
        this.elementType = elementType;
    }

    /**
     * 获取数组元素类型。
     */
    public Type getElementType() {
        return elementType;
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则返回true。
     * 否则仅当参数也是ArrayType且元素类型相等时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof ArrayType)) {
            return false;
        }
        ArrayType other = (ArrayType) obj;
        return elementType.equals(other.elementType);
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
        return elementType + "[]";
    }
}
