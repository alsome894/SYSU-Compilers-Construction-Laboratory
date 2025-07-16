package parser.type;

/**
 * 表示布尔类型的类。
 */
public class BooleanType extends Type {
    /**
     * 构造一个布尔类型。
     */
    public BooleanType() {
        super("boolean");
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则返回true。
     * 否则仅当参数也是BooleanType时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof BooleanType;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
