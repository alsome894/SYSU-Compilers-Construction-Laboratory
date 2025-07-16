package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * 表达式是一个一元操作符，例如 - 或 !。
 */
public class UnaryExpr {
    /** 需要取负或取反的值 */
    private Token value;

    /**
     * 构造函数。
     * @param _value 需要进行一元操作的十进制数或布尔值
     * @throws TypeMismatchedException 如果值既不是十进制数也不是布尔值
     */
    public UnaryExpr(Token _value) throws TypeMismatchedException {
        if (_value.getType().equals("decimal"))
            value = new Decimal(-_value.getDecimal(), false);
        else if (_value.getType().equals("boolean"))
            value = new MyBoolean(!_value.getBoolean(), false);  
        else
            throw new TypeMismatchedException();
    }

    /**
     * 获取 -value 或 !boolean 的结果。
     * @return 十进制数 -> -十进制数；true -> false；false -> true
     */
    public Token expr() {
        return value;
    }
}
