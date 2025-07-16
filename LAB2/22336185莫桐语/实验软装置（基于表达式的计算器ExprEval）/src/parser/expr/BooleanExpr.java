package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

/**
 * 表达式是一个常量：布尔值
 */
public class BooleanExpr {
    /** 常量值 */
    private Token myBoolean;

    /**
     * 构造一个常量表达式。
     * 
     * @param _value 布尔表达式
     * @throws MissingOperandException 如果不是布尔值，缺少操作数时抛出异常
     */
    public BooleanExpr(Token _value) throws MissingOperandException {
        if(_value.getType().equals("boolean"))
            myBoolean = new MyBoolean(_value, false);
        else
            throw new MissingOperandException();
    }

    /**
     * 计算表达式的值。
     * 
     * @return 返回布尔值的 Token，是一个非终结符
     */
    public Token expr() {
        return myBoolean;
    }
}
