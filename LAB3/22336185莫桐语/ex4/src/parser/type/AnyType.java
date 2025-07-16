package parser.type;

/**
 * 表示任意类型的类，等价于所有Type。
 */
public class AnyType extends Type {
    /**
     * 构造一个AnyType类型。
     */
    public AnyType() {
        super("any");
    }
    
    /**
     * 判断类型是否相等。
     * 只要参数是Type的实例，总是返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            return true;
        }
        return false;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
