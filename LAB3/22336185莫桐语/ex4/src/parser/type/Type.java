package parser.type;

/**
 * 抽象类型基类，所有类型都应继承自该类。
 */
public abstract class Type {
    private String name;

    /**
     * 构造一个类型对象。
     * @param name 类型名称
     */
    public Type(String name) {
        this.name = name;
    }

    /**
     * 判断该类型是否等于另一个类型。
     * 
     * @param obj 另一个类型对象
     * @return 如果类型相等返回 true，否则返回 false
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * 获取该类型的哈希值。
     * 
     * @return 该类型的哈希码
     */
    @Override
    public abstract int hashCode();

    /**
     * 返回类型的名称字符串。
     * @return 类型名称
     */
    @Override
    public String toString() {
        return name;
    }
}
