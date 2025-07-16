package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * 三元表达式。
 * A ? B : C
 */
public class TrinaryExpr {
    /** 条件：A ? B : C 中的 A */
    private MyBoolean condition;

    /** 选择项 1：A ? B : C 中的 B */
    private Token left;
    
    /** 选择项 2：A ? B : C 中的 C */
    private Token right;

    /**
     * 构造函数。
     * 条件：
     *   1. 条件必须是布尔值。
     *   2. 选择项必须是布尔值或十进制数。
     * @param _condition 条件：A ? B : C 中的 A
     * @param _left 选择项 1：A ? B : C 中的 B
     * @param _right 选择项 2：A ? B : C 中的 C
     * @throws ExpressionException 如果违反条件 1 或 2
     */
    public TrinaryExpr(Token _condition, Token _left, Token _right) throws ExpressionException {
        if (!_condition.getType().equals("boolean"))
            throw new TypeMismatchedException();
        condition = new MyBoolean(_condition);
        
        if (_left.getType().equals("decimal"))
            left = new Decimal(_left);
        else if (_left.getType().equals("boolean"))
            left = new MyBoolean(_left);
        else
            throw new MissingOperandException();
        
        if (_right.getType().equals("decimal"))
            right = new Decimal(_right);
        else if (_right.getType().equals("boolean"))
            right = new MyBoolean(_right);
        else
            throw new MissingOperandException();
    }

    /**
     * 计算表达式的值。
     * 如果条件为真，则返回选择项 1，否则返回选择项 2。
     * @return 结果：选择项 1 或选择项 2
     * @throws ExpressionException 如果发生错误
     */
    public Token expr() throws ExpressionException {
        boolean choose = condition.getBoolean();

        if (choose) {
            if (left.getType().equals("decimal"))
                return new Decimal(left, false);
            else
                return new MyBoolean(left, false);
        }

        if (right.getType().equals("decimal"))
            return new Decimal(right, false);
        else
            return new MyBoolean(right, false);
    }
}
