package parser.type;

/**
 * 表示模块类型的类。
 */
public class ModuleType extends Type {
    /**
     * 构造一个模块类型。
     */
    public ModuleType() {
        super("module");
    }

    /**
     * 判断类型是否相等。
     * 如果参数是AnyType，则返回true。
     * 否则仅当参数也是ModuleType时返回true。
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof ModuleType;
    }

    /**
     * 返回哈希码。
     */
    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
