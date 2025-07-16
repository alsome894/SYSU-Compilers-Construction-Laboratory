package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * 表达式是一个关系运算，例如 1 &gt; 2。
 */
public class RelationExpr {
    /** 操作符: &gt; &lt; = &lt;= &gt;= &lt;&gt; */
    private Token opertor;

    /** 表达式的左操作数 */
    private Token left;
    
    /** 表达式的右操作数 */
    private Token right;

    /**
     * 构造函数。
     * 条件：
     *   1. 左操作数和右操作数必须类型相同。
     *   2. 左操作数和右操作数必须是十进制数。
     * @param _operator 操作符: &gt; &lt; = &lt;= &gt;= &lt;&gt;
     * @param _left 表达式的左操作数
     * @param _right 表达式的右操作数
     * @throws TypeMismatchedException 如果左操作数和右操作数类型不匹配或不是十进制数
     */
    public RelationExpr(Token _operator, Token _left, Token _right) throws TypeMismatchedException {
        if (_left.getType().equals("decimal") &&
            _right.getType().equals("decimal") &&
            _operator.getType().equals("relation")) {
            left = new Decimal(_left);
            right = new Decimal(_right);
        } else
            throw new TypeMismatchedException();

        opertor = new Symbol(_operator);
    }

    /**
     * 十进制表达式。
     * 操作符是 &gt; &lt; = &lt;= &gt;= &lt;&gt;。
     * 条件：
     *   1. 左操作数和右操作数必须是十进制数。
     * @return 结果
     * @throws ExpressionException 如果违反条件 1
     */
    private Token exprDecimal() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "<":
                return new MyBoolean(leftDecimal < rightDecimal, false);
            case "<=":
                return new MyBoolean(leftDecimal <= rightDecimal, false);
            case ">":
                return new MyBoolean(leftDecimal > rightDecimal, false);
            case ">=":
                return new MyBoolean(leftDecimal >= rightDecimal, false);
            case "=":
                return new MyBoolean(leftDecimal == rightDecimal, false);
            case "<>":
                return new MyBoolean(leftDecimal != rightDecimal, false);
        }
        throw new MissingOperatorException();
    }


    /**
     * 计算表达式的值。
     * 根据操作符选择 &gt; &lt; = &lt;= &gt;= &lt;&gt;。
     * @return 结果
     * @throws ExpressionException 如果发生错误
     */
    public Token expr() throws ExpressionException {
        return exprDecimal();
    }
}
