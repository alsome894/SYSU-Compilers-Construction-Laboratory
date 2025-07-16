package parser.token;

/**
 * 布尔类型的Token。
 * 继承自Token类。
 */
public class MyBoolean extends Token {
    /**
     * Token的布尔值。
     */
    private boolean booleanValue;

    /**
     * 使用字符串值的构造函数。
     * @param _value 布尔值的字符串表示。
     */
    public MyBoolean(String _value) {
        priorityId = 0;
        type = new String("boolean");
        terminal = true;
        value = new String(_value);

        if (_value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * 使用布尔值和是否为终结状态的构造函数。
     * @param _value 布尔值。
     * @param _terminal 是否为终结状态。
     */
    public MyBoolean(boolean _value, boolean _terminal) {
        priorityId = 0;
        type = new String("boolean");
        terminal = _terminal;
        value = new String(_value ? "true" : "false");

        booleanValue = _value;
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的Token。
     */
    public MyBoolean(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * 拷贝构造函数，带是否为终结状态的参数。
     * @param _copy 被拷贝的Token。
     * @param _terminal 是否为终结状态。
     */
    public MyBoolean(Token _copy, boolean _terminal) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _terminal;
        value = new String(_copy.value);

        if (value.equals("true"))
            booleanValue = true;
        else
            booleanValue = false;
    }

    /**
     * 无用方法。
     * @return 返回0。
     */
    public double getDecimal() {
        return 0;
    }

    /**
     * 返回布尔值。
     * @return 布尔值。
     */
    public boolean getBoolean() {
        return booleanValue;
    }
}
