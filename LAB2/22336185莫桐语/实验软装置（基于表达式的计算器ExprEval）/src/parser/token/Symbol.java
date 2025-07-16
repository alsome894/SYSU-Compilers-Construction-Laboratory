package parser.token;

/**
 * 符号Token类。
 */
public class Symbol extends Token {

    /**
     * 使用字符串值和符号类型的构造函数。
     * @param _value 字符串值，例如','
     */
    public Symbol(String _value) {
        priorityId = Token.priorityIdMap.get(_value);
        switch (_value) {
            case "(":
                type = new String("parenthesis");
                break;
            case ")":
                type = new String("parenthesis");
                break;
            case "sin":
                type = new String("function");
                break;
            case "cos":
                type = new String("function");
                break;
            case "max":
                type = new String("function");
                break;
            case "min":
                type = new String("function");
                break;
            case "u-":
                type = new String("unary");
                break;
            case "^":
                type = new String("decimal_operator");
                break;
            case "*":
                type = new String("decimal_operator");
                break;
            case "/":
                type = new String("decimal_operator");
                break;
            case "+":
                type = new String("decimal_operator");
                break;
            case "-":
                type = new String("decimal_operator");
                break;
            case ">":
                type = new String("relation");
                break;
            case ">=":
                type = new String("relation");
                break;
            case "<":
                type = new String("relation");
                break;
            case "<=":
                type = new String("relation");
                break;
            case "<>":
                type = new String("relation");
                break;
            case "=":
                type = new String("relation");
                break;
            case "!":
                type = new String("unary");
                break;
            case "&":
                type = new String("boolean_operator");
                break;
            case "|":
                type = new String("boolean_operator");
                break;
            case "?":
                type = new String("trinary");
                break;
            case ":":
                type = new String("trinary");
                break;
            case ",":
                type = new String("comma");
                break;
            case "$":
                type = new String("dollar");
                break;
            default:
                type = new String("unknown");
        }
        terminal = true;
        value = new String(_value);
    }

    /**
     * 使用另一个Token的拷贝构造函数。
     * @param _copy Token的拷贝
     */
    public Symbol(Token _copy) {
        priorityId = _copy.priorityId;
        type = new String(_copy.type);
        terminal = _copy.terminal;
        value = _copy.value;
    }

    /**
     * 无用方法。
     * @return 总是返回0
     */
    public double getDecimal() {
        return 0;
    }

    /**
     * 无用方法。
     * @return 总是返回false
     */
    public boolean getBoolean() {
        return false;
    }
}
