package parser.type;

/**
 * 表示整数类型的类。
 */
public class IntegerType extends Type {
    /**
     * 构造一个整数类型。
     */
    public IntegerType() {
        super("integer");
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则总是返回true。
     * 否则仅当参数也是IntegerType时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof IntegerType;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
