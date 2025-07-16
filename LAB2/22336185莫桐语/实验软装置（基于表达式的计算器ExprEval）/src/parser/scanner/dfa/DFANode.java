package parser.scanner.dfa;

import java.util.*;

import parser.token.*;

/**
 * 扫描器DFA中的状态节点。
 */
public abstract class DFANode {
    /**
     * 是否为终结状态。
     */
    protected boolean finish;

    /**
     * 终结状态的类型，例如十进制。
     */
    protected String type;

    /**
     * 从当前状态出发的边。
     * 使用HashMap存储，键为可以转移的字符，值为目标状态。
     */
    protected HashMap <Character, Integer> edges;

    /**
     * 空构造函数。
     */
    DFANode() {
        finish = false;
        type = new String();
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 带类型的构造函数，表示这是一个终结状态。
     * @param _type 终结状态的Token类型。
     */
    DFANode(String _type) {
        finish = true;
        type = new String(_type);
        edges = new HashMap <Character, Integer> ();
    }

    /**
     * 拷贝构造函数。
     * @param _copy 被拷贝的对象。
     */
    DFANode(DFANode _copy) {
        finish = _copy.finish;
        type = new String(_copy.type);
        edges = new HashMap <Character, Integer>(_copy.edges);
    }

    /**
     * 返回终结状态点上的Token。
     * @param name Token的内容。
     * @return 一个Token。
     */
    public abstract Token getToken(String name);

    /**
     * 检查是否为终结状态。
     * @return 是否为终结状态。
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * 获取状态的类型，例如十进制。
     * @return 状态类型。
     */
    public String getType() {
        return type;
    }

    /**
     * 获取状态的出边。
     * @return 包含出边的HashMap。
     */
    public HashMap <Character, Integer> getEdges() {
        return edges;
    }

    /**
     * 添加一条出边，需要指定字符和目标状态。
     * @param ch 可以转移的字符。
     * @param to 转移的目标状态。
     */
    public void addEdge(char ch, int to) {
        edges.put(ch, to);
    }
}
