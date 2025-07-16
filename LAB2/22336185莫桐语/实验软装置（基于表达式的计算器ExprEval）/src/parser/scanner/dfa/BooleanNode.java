package parser.scanner.dfa;

import java.util.*;

import parser.token.*;

/**
 * 布尔类型的DFA状态节点。
 */
public class BooleanNode extends DFANode {
    /**
     * 空构造函数。
     */
    BooleanNode() {
        finish = true;
        type = new String("boolean");
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的对象。
     */
    BooleanNode(DFANode _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * 返回一个布尔类型的Token。
     * @param _value Token的值。
     * @return 一个布尔类型的Token。
     */
    public MyBoolean getToken(String _value) {
        return new MyBoolean(_value);
    }
}
