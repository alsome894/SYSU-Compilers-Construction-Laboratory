package parser.scanner;

import exceptions.*;
import parser.scanner.dfa.*;
import parser.token.*;

import java.beans.Expression;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

/**
 * 扫描器。
 */
public class MyScanner {
    /**
     * 需要扫描的表达式。
     */
    private String expression;

    /**
     * 扫描结果，token列表。
     */
    private ArrayList <Token> tokens;
    
    /**
     * 扫描器的DFA。
     * 它是静态的，因为只有一个DFA实例。
     */
    private static DFA dfa = new DFA();

    /**
     * 空构造函数。
     */
    public MyScanner() {
        expression = new String("");
        tokens = new ArrayList<Token>();
    }

    /**
     * 带表达式的构造函数。
     * @param _expression 需要扫描的表达式。
     */
    public MyScanner(String _expression) {
        expression = new String(_expression.toLowerCase());
        tokens = new ArrayList<Token>();
    }

    /**
     * 检查是否为一元负号。
     * @param cur 当前token字符串。
     * @return 是否为一元负号。
     */
    private boolean isUnary(String cur) {
        if (cur.equals("-")) {
            int tokenCount = tokens.size();
            if (tokenCount > 0) {
                Token last = tokens.get(tokenCount - 1);
                if (last.getType().equals("decimal") 
                    || last.getValue().equals(")")
                    || last.getType().equals("boolean"))
                    return false;
                else
                    return true;
            } else
                return true;
        }
        return false;
    }

    /**
     * 扫描表达式。
     * 让表达式通过DFA。
     * @return token列表。
     * @throws ExpressionException 如果表达式非法或为空。
     */
    public ArrayList <Token> scan() throws ExpressionException {
        int expressionLength = expression.length();
        int index = 0;

        String curToken = new String();
        dfa.reset();
        boolean startWithLetter = false;
        boolean startWithDigit = false;
        while (index < expressionLength) {
            char cur = expression.charAt(index);
            char lookahead = (index + 1 < expressionLength) ? expression.charAt(index + 1) : '$';
            if (cur == ' ') {
                index++;
                continue;
            }

            if (dfa.isStart()) {
                if (Character.isLetter(cur))
                    startWithLetter = true;
                else if (Character.isDigit(cur) || cur == '.')
                    startWithDigit = true;
            }
            
            curToken += cur;
            String tokenType = dfa.nextState(cur, lookahead);
            if (tokenType.equals("scanning")) {
                index++;
                continue;

            } else if (tokenType.equals("error")) {

                if (startWithLetter)
                    throw new IllegalIdentifierException();
                else if (startWithDigit)
                    throw new IllegalDecimalException();
                else
                    throw new IllegalSymbolException();

            } else {

                tokens.add(dfa.getToken(curToken, isUnary(curToken)));
                dfa.reset();
                curToken = "";
                startWithLetter = false;
                startWithDigit = false;

            }
            index++;
        }

        if (tokens.isEmpty())
            throw new EmptyExpressionException();
        return tokens;
    }
}
