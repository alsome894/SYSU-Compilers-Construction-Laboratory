package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * 表达式是一个布尔类型操作符，例如 1 > 2。
 */
public class BooleanOperatorExpr {
    /** 操作符: &amp; | */
    private Token opertor;

    /** 表达式的左操作数 */
    private Token left;
    
    /** 表达式的右操作数 */
    private Token right;

    /**
     * 构造函数。
     * 条件：
     *   1. 左操作数和右操作数必须类型相同。
     *   2. 左操作数和右操作数必须是布尔值。
     * @param _operator 操作符: &amp; |
     * @param _left 表达式的左操作数
     * @param _right 表达式的右操作数
     * @throws TypeMismatchedException 如果左操作数和右操作数类型不匹配或不是布尔值
     */
    public BooleanOperatorExpr(Token _operator, Token _left, Token _right) throws TypeMismatchedException {
        if (_left.getType().equals("boolean") &&
            _right.getType().equals("boolean") &&
            _operator.getType().equals("boolean_operator")) {
            left = new MyBoolean(_left);
            right = new MyBoolean(_right);
        } else
            throw new TypeMismatchedException();

        opertor = new Symbol(_operator);
    }

    /**
     * 计算表达式的值。
     * 根据操作符选择 &amp; |。
     * @return 结果
     * @throws ExpressionException 如果发生错误
     */
    public Token expr() throws ExpressionException {
        boolean leftBoolean = left.getBoolean();
        boolean rightBoolean = right.getBoolean();
        switch (opertor.getValue()) {
            case "&":
                return new MyBoolean(leftBoolean && rightBoolean, false);
            case "|":
                return new MyBoolean(leftBoolean || rightBoolean, false);
        }
        throw new MissingOperatorException();
    }
}
