package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

/**
 * 表达式是一个十进制数操作符，例如 1 + 2。
 */
public class DecimalOperatorExpr {
    /** 操作符: + - * / 和 exp */
    private Token opertor;

    /** 表达式的左操作数（十进制数） */
    private Decimal left;
    
    /** 表达式的右操作数（十进制数） */
    private Decimal right;

    /**
     * 构造函数。
     * 左操作数和右操作数必须是十进制数。
     * @param _opertor 操作符: + - * / 和 exp
     * @param _left 表达式的左操作数
     * @param _right 表达式的右操作数
     * @throws TypeMismatchedException 如果左操作数或右操作数不是十进制数
     */
    public DecimalOperatorExpr(Token _opertor, Token _left, Token _right) throws TypeMismatchedException {
        if (!_left.getType().equals("decimal"))
            throw new TypeMismatchedException();
        if (!_right.getType().equals("decimal"))
            throw new TypeMismatchedException();
        opertor = new Symbol(_opertor);
        left = new Decimal(_left);
        right = new Decimal(_right);
    }

    /**
     * 计算表达式的值。
     * @return a+b, a-b, a*b, a/b 或 a exp b；返回一个非终结符
     * @throws ExpressionException 如果操作符不是 + - * / exp
     */
    public Token expr() throws ExpressionException {
        double leftDecimal = left.getDecimal();
        double rightDecimal = right.getDecimal();
        switch (opertor.getValue()) {
            case "+":
                return new Decimal(leftDecimal + rightDecimal, false);
            case "-":
                return new Decimal(leftDecimal - rightDecimal, false);
            case "*":
                return new Decimal(leftDecimal * rightDecimal, false);
            case "/":
                if (rightDecimal == 0)
                    throw new DividedByZeroException();
                return new Decimal(leftDecimal / rightDecimal, false);
            case "^":
                return new Decimal(Math.pow(leftDecimal, rightDecimal), false);
        }
        throw new MissingOperatorException();
    }
}
