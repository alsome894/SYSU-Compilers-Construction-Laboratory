package parser.token;

import java.util.HashMap;

/**
 * 抽象Token类。
 */
public abstract class Token {
    /** Token的优先级。 */
    protected int priorityId;

    /** Token的类型，例如decimal。 */
    protected String type;

    /** 是否为终结符。 */
    protected boolean terminal;

    /** Token本身的值，是一个字符串，例如'sin'。 */
    protected String value;

    /**
     * 优先级映射表，使用HashMap。
     * 优先级类别：
     * decimal, boolean, (), func, +-, /*, ^, 比较, !, and, |, ?, :, 逗号, $
     */
    protected static HashMap<String, Integer> priorityIdMap;

    /**
     * 返回Token的值。
     * @return 值，字符串形式
     */
    public String getValue() {
        return value;
    }

    /**
     * 返回Token的优先级。
     * @return 优先级，整数形式
     */
    public int getPriority() {
        return priorityId;
    }

    /**
     * 返回Token的类型。
     * @return 类型，字符串形式
     */
    public String getType() {
        return type;
    }

    /**
     * 返回是否为终结符。
     * @return true或false
     */
    public boolean isTerminal() {
        return terminal;
    }

    /**
     * 设置Token为非终结符。
     */
    public void setNonTerminal() {
        terminal = false;
    }

    /**
     * 如果是小数，返回小数值。
     * @return double值
     */
    public abstract double getDecimal();

    /**
     * 如果是布尔值，返回布尔值。
     * @return 布尔值
     */
    public abstract boolean getBoolean();


    /**
     * 构建算符优先表。
     */
    static {
        priorityIdMap = new HashMap <String, Integer>();

        //parentheses
        priorityIdMap.put("(", 2);
        priorityIdMap.put(")", 3);

        //function
        priorityIdMap.put("max", 4);
        priorityIdMap.put("min", 4);
        priorityIdMap.put("sin", 4);
        priorityIdMap.put("cos", 4);

        //unary-
        priorityIdMap.put("u-", 5);

        //decimal_operator
        priorityIdMap.put("^", 6);
        priorityIdMap.put("*", 7);
        priorityIdMap.put("/", 7);
        priorityIdMap.put("+", 8);
        priorityIdMap.put("-", 8);

        //relation
        priorityIdMap.put("<", 9);
        priorityIdMap.put("<=", 9);
        priorityIdMap.put(">",9);
        priorityIdMap.put(">=", 9);
        priorityIdMap.put("=", 9);
        priorityIdMap.put("<>", 9);

        //unary!
        priorityIdMap.put("!", 10);

        //boolean_operator
        priorityIdMap.put("&", 11);
        priorityIdMap.put("|", 12);

        //trinary
        priorityIdMap.put("?", 13);
        priorityIdMap.put(":", 14);

        //comma
        priorityIdMap.put(",", 15);

        //dollar
        priorityIdMap.put("$", 16);
    }
}
