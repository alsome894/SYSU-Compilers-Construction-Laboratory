package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

/**
 * 表达式是一个常量：十进制
 */
public class DecimalExpr {
    /** 常量值 */
    private Token decimal;

    /**
     * 构造一个常量表达式。
     * 
     * @param _value 十进制常量表达式
     * @throws MissingOperandException 如果不是十进制，缺少操作数时抛出异常
     */
    public DecimalExpr(Token _value) throws MissingOperandException {
        if(_value.getType().equals("decimal"))
            decimal = new Decimal(_value, false);
        else
            throw new MissingOperandException();
    }

    /**
     * 计算表达式的值。
     * 
     * @return 返回十进制的 Token，是一个非终结符
     */
    public Token expr() {
        return decimal;
    }
}