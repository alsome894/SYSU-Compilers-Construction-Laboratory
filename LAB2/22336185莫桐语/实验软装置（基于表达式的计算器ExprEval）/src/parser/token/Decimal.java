package parser.token;

/**
 * 十进制数类型的Token。
 * 继承自Token类。
 */
public class Decimal extends Token {
    /** 小数的double值。 */
    private double decimalValue;

    /**
     * 使用字符串值的构造函数。
     * @param _value 字符串形式的小数值
     */
    public Decimal(String _value) {
        priorityId = 1;
        type = new String("decimal");
        terminal = true;
        value = new String(_value);

        decimalValue = Double.parseDouble(_value);
    }

    /**
     * 使用double值和是否为终结符的构造函数。
     * @param _value double值
     * @param _terminal 是否为终结符
     */
    public Decimal(double _value, boolean _terminal) {
        priorityId = 1;
        type = new String("decimal");
        terminal = _terminal;
        value = new String(String.valueOf(_value));

        decimalValue = Double.parseDouble(value);
    }

    /**
     * 使用另一个Token的拷贝构造函数。
     * @param _copy Token的拷贝
     */
    public Decimal(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = new String(_copy.value);

        decimalValue = Double.parseDouble(value);
    }

    /**
     * 使用Token拷贝和是否为终结符的拷贝构造函数。
     * @param _copy Token的拷贝
     * @param _terminal 是否为终结符
     */
    public Decimal(Token _copy, boolean _terminal) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _terminal;
        value = new String(_copy.value);

        decimalValue = Double.parseDouble(value);
    }

    /**
     * 获取小数的double值。
     * @return double值
     */
    public double getDecimal() {
        return decimalValue;
    }

    /**
     * 无用方法。
     * @return 总是返回false
     */
    public boolean getBoolean() {
        return false;
    }
}
