package parser.scanner.dfa;

import java.util.*;

import parser.token.*;

/**
 * 十进制类型的DFA终结状态节点。
 */
public class DecimalNode extends DFANode {
    /**
     * 空构造函数。
     */
    DecimalNode() {
        finish = true;
        type = new String("decimal");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的对象。
     */
    DecimalNode(DFANode _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * 返回一个十进制类型的Token。
     * @param _value Token的值。
     * @return 一个十进制类型的Token。
     */
    public Decimal getToken(String _value) {
        return new Decimal(_value);
    }
}
