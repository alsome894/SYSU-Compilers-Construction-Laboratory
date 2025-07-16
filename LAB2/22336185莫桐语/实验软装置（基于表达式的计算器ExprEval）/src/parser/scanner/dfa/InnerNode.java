package parser.scanner.dfa;

import java.util.*;

import parser.token.*;

/**
 * DFA中的内部状态节点。
 */
public class InnerNode extends DFANode {
    /**
     * 空构造函数。
     */
    InnerNode() {
        finish = false;
        type = new String("inner");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的对象。
     */
    InnerNode(DFANode _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * 实际上不会发生。
     * @param _value 无意义的参数。
     * @return 无意义的返回值。
     */
    public Token getToken(String _value) {
        return new Decimal(_value);
    }
}
