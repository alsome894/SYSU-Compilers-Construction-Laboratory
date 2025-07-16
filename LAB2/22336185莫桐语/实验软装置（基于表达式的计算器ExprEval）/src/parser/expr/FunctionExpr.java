package parser.expr;

import exceptions.*;
import parser.token.*;

import java.util.*;

/**
 * 表达式是一个函数。
 */
public class FunctionExpr {
    /** 函数名称，例如：sin, cos, min, max */
    private Token func;

    /** 函数的参数列表。 */
    private ArrayList<Token> args;
    
    /** 参数的数量（包括逗号）。 */
    private int length;

    /**
     * 空构造函数。
     */
    public FunctionExpr() {
        args = new ArrayList<Token>();
        length = 0;
    }

    /**
     * 构造函数。
     * @param _func 函数名称，例如：sin, cos, min, max
     * @param _args 函数的参数列表（包括逗号）。
     */
    public FunctionExpr(Token _func, ArrayList<Token> _args) {
        func = new Symbol(_func);
        args = new ArrayList<Token>(_args);
        length = args.size();
    }

    /**
     * 检查参数列表是否合法。
     * @throws ExpressionException 根据 switch case 的情况抛出异常
     */
    private void checkArgs() throws ExpressionException {
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);
                
                switch (iValue.getType()) {
                    case "boolean":
                        throw new TypeMismatchedException();
                    case "comma":
                        throw new MissingOperandException();
                    case "decimal":
                        break;
                    default:
                        throw new FunctionCallException();
                }

                double nowValue = iValue.getDecimal();
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }
        if (length % 2 == 0)
            throw new MissingOperandException();
    }

    /**
     * 计算 sin 或 cos 函数的值。
     * 条件：
     *   1. 参数必须只有一个，否则抛出 FunctionCallException。
     *   2. 参数类型必须是 decimal，否则抛出 TypeMismatchedException。
     *   3. 函数名称必须是 sin 或 cos，否则抛出 FunctionCallException。
     * @return 计算结果
     * @throws ExpressionException 包括 FunctionCallException 和 TypeMismatchedException
     */
    private Token exprSinCos() throws ExpressionException {
        if (length == 0)
            throw new MissingOperandException();
        if (length != 1)
            throw new FunctionCallException();
        
        Token value = args.get(0);

        switch (func.getValue()) {
            case "sin":
                return new Decimal(Math.sin(value.getDecimal()), false);
            case "cos":
                return new Decimal(Math.cos(value.getDecimal()), false);
        }
        throw new FunctionCallException();
    }

    /**
     * 计算 max 或 min 函数的值。
     * 条件：
     *   1. 参数数量必须是奇数，因为 (逗号 = decimal - 1)，
     *      所以总数为 2 * decimal - 1，是一个奇数。
     *      否则抛出 FunctionCallException。
     *   2. decimal 的数量必须大于 1，否则抛出 MissingOperandException。
     *   3. 参数排列必须是 "decimal, comma, decimal, comma, ... decimal"。
     *      否则：
     *        - 如果不是 decimal 而是 boolean，抛出 TypeMismatchedException。
     *        - 如果缺少逗号，抛出 FunctionCallException。
     * @return 参数的最大值或最小值，非终结符
     * @throws ExpressionException 如果违反上述条件
     */
    private Token exprMaxMin() throws ExpressionException {
        
        if (length == 0)
            throw new MissingOperandException();

        Token firstValue = args.get(0);
        double maxValue = firstValue.getDecimal();
        double minValue = maxValue;
        for (int i = 1; i < length; i++) {
            if (i % 2 == 0) {
                Token iValue = args.get(i);

                double nowValue = iValue.getDecimal();
                maxValue = Math.max(nowValue, maxValue);
                minValue = Math.min(nowValue, minValue);
            } else {
                if (!args.get(i).getType().equals("comma"))
                    throw new FunctionCallException();
            }
        }

        if ((length + 1) / 2 <= 1)
            throw new MissingOperandException();

        switch (func.getValue()) {
            case "max":
                return new Decimal(maxValue, false);
            case "min":
                return new Decimal(minValue, false);
        }
        throw new FunctionCallException();
    }

    /**
     * 计算表达式的值。
     * 根据函数类型选择是单参数函数还是多参数函数。
     * @return 函数的计算结果
     * @throws ExpressionException 如果违反上述条件
     */
    public Token expr() throws ExpressionException {

        checkArgs();

        if (func.getValue().equals("sin") || func.getValue().equals("cos"))
            return exprSinCos();
        else
            return exprMaxMin();
    }
}
