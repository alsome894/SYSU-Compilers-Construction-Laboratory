package parser.scanner.dfa;

import java.util.*;

import parser.token.*;

/**
 * 其他符号类型的DFA终结状态节点。
 */
public class SymbolNode extends DFANode {
    /**
     * 构造函数。
     * @param _type Token的类型。
     */
    SymbolNode(String _type) {
        finish = true;
        type = _type;
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的对象。
     */
    SymbolNode(DFANode _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * 返回一个Token。
     * @param _value Token的值。
     * @return 一个其他符号类型的Token。
     */
    public Symbol getToken(String _value) {
        return new Symbol(_value);
    }
}
