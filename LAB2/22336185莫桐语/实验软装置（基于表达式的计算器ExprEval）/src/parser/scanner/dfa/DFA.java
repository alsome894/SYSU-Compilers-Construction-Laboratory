package parser.scanner.dfa;

import parser.token.*;

/**
 * 扫描器的DFA。
 */
public class DFA {
    /** 当前DFA的状态。 */
    private int state;

    /** DFA的状态点。 */
    private static DFANode []nodes;

    /**
     * 构造函数，初始状态为0。
     */
    public DFA() {
        state = 0;
    }

    /**
     * 扫描一个token后，返回初始状态。
     */
    public void reset() {
        state = 0;
    }

    /**
     * 当前状态，并尝试通过一个边。
     * 如果没有对应的边，返回错误。
     * 如果lookahead不存在或为$，接受此token并返回token类型。
     * 否则返回scanning。
     * @param cur 当前边对应的字符
     * @param lookahead 下一个字符
     * @return 处理结果："error"、token类型或"scanning"
     */
    public String nextState(char cur, char lookahead) {
        if (!nodes[state].getEdges().containsKey(cur)) {
            return "error";
        }
        state = nodes[state].getEdges().get(cur);
        DFANode tempState = nodes[state];
        if (!tempState.getEdges().containsKey(lookahead) && tempState.isFinish() || lookahead == '$') {
            if (!tempState.isFinish())
                return "error";
            return nodes[state].getType();
        }
        return "scanning";
    }

    /**
     * 返回当前状态的一个新token。
     * @param _value token的值，例如'sin'
     * @param isUnaryFlag 是否为一元操作符
     * @return 一个新的Token
     */
    public Token getToken(String _value, boolean isUnaryFlag) {
        if (isUnaryFlag)
            return new Symbol("u-");
        return nodes[state].getToken(_value);
    }

    /**
     * 检查当前状态是否为初始状态。
     * @return 布尔值，是否为初始状态
     */
    public boolean isStart() {
        return (state == 0);
    }

    /**
     * 构建DFA。
     * 构建内容与DFA状态图一致。
     */
    static {
        nodes = new DFANode[46];

        // 终结状态节点
        nodes[4] = new BooleanNode();
        nodes[9] = new BooleanNode();

        nodes[10] = new DecimalNode();
        nodes[12] = new DecimalNode();
        nodes[15] = new DecimalNode();

        nodes[16] = new SymbolNode("parenthesis");
        nodes[17] = new SymbolNode("parenthesis");

        nodes[20] = new SymbolNode("function");
        nodes[23] = new SymbolNode("function");
        nodes[26] = new SymbolNode("function");
        nodes[28] = new SymbolNode("function");

        nodes[29] = new SymbolNode("decimal_operator");
        nodes[30] = new SymbolNode("decimal_operator");
        nodes[31] = new SymbolNode("decimal_operator");
        nodes[32] = new SymbolNode("decimal_operator");
        nodes[33] = new SymbolNode("decimal_operator");

        nodes[34] = new SymbolNode("relation");
        nodes[35] = new SymbolNode("relation");
        nodes[36] = new SymbolNode("relation");
        nodes[37] = new SymbolNode("relation");
        nodes[38] = new SymbolNode("relation");
        nodes[39] = new SymbolNode("relation");

        nodes[40] = new SymbolNode("unary");

        nodes[41] = new SymbolNode("boolean_operator");
        nodes[42] = new SymbolNode("boolean_operator");

        nodes[43] = new SymbolNode("trinary");
        nodes[44] = new SymbolNode("trinary");

        nodes[45] = new SymbolNode("comma");


        // 中间状态节点
        for (int i = 0; i < 46; i++)
            if (nodes[i] == null)
                nodes[i] = new InnerNode();

        // boolean
        nodes[0].addEdge('t', 1);
        nodes[1].addEdge('r', 2);
        nodes[2].addEdge('u', 3);
        nodes[3].addEdge('e', 4);
        nodes[0].addEdge('f', 5);
        nodes[5].addEdge('a', 6);
        nodes[6].addEdge('l', 7);
        nodes[7].addEdge('s', 8);
        nodes[8].addEdge('e', 9);

        // digit=[0-9]
        for (int i = 0; i <= 9; i++) {
            char c = (char)(i + 48);
            nodes[0].addEdge(c, 10);    // 0 → 10
            nodes[10].addEdge(c, 10);   // 10 → 10
            nodes[11].addEdge(c, 12);   // 11 → 12
            nodes[12].addEdge(c, 12);   // 12 → 12
            nodes[13].addEdge(c, 15);   // 13 → 15
            nodes[14].addEdge(c, 15);   // 14 → 15
            nodes[15].addEdge(c, 15);   // 15 → 15
        }

        // decimal
        nodes[10].addEdge('.', 11);     // 10 → 11
        nodes[10].addEdge('e', 13);     // 10 → 13
        nodes[12].addEdge('e', 13);     // 12 → 13
        nodes[13].addEdge('+', 14);     // 13 → 14
        nodes[13].addEdge('-', 14);     // 13 → 14

        // decimal_operator
        nodes[0].addEdge('+', 32);      // +
        nodes[0].addEdge('-', 33);      // -
        nodes[0].addEdge('*', 30);      // *
        nodes[0].addEdge('/', 31);      // /
        nodes[0].addEdge('^', 29);      // ^

        // parenthesis
        nodes[0].addEdge('(', 16);      // (
        nodes[0].addEdge(')', 17);      // )


        // sincos
        nodes[0].addEdge('s', 18);      // s → 18
        nodes[18].addEdge('i', 19);     // s → i → 19
        nodes[19].addEdge('n', 20);     // i → n → 20
        nodes[0].addEdge('c', 21);      // c → 21
        nodes[21].addEdge('o', 22);     // o → 22
        nodes[22].addEdge('s', 23);     // s → 23

        // maxmin
        nodes[0].addEdge('m', 24);      // m → 24
        nodes[24].addEdge('a', 25);     // a → 25
        nodes[25].addEdge('x', 26);     // x → 26
        nodes[24].addEdge('i', 27);     // i → 27
        nodes[27].addEdge('n', 28);     // n → 28

        // relation
        nodes[0].addEdge('>', 34);      // > → 34
        nodes[34].addEdge('=', 35);     // >= → 35
        nodes[0].addEdge('<', 36);      // < → 36
        nodes[36].addEdge('=', 37);     // <= → 37
        nodes[36].addEdge('>', 38);     // <> → 38
        nodes[0].addEdge('=', 39);      // =

        // unary
        nodes[0].addEdge('!', 40);      // !

        // boolean_operator
        nodes[0].addEdge('&', 41);      // &
        nodes[0].addEdge('|', 42);      // |

        // trinary
        nodes[0].addEdge('?', 43);      // ?
        nodes[0].addEdge(':', 44);      // :

        // comma
        nodes[0].addEdge(',', 45);      // ,
    }
}
