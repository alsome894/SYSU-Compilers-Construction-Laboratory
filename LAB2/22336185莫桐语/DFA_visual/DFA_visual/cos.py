# 导入所需的库和模块
from automata.fa.dfa import DFA
from visual_automata.fa.dfa import VisualDFA

# 定义 DFA 的五个组成部分
# 1. 状态集合（不包含死状态）
states = {'0', '21', '22', '23'}
# 2. 输入符号（仅包含有效字符）
input_symbols = {'c', 'o', 's'}
# 3. 转移函数（仅定义有效路径，其他输入自动拒绝）
transitions = {
    '0': {
        'c': '21', 
    },
    '21': {
        'o': '22', 
    },
    '22': {
        's': '23', 
    },
    '23': {
    },
}
# 4. 初始状态
initial_state = '0'
# 21. 接受状态集合
final_states = {'23'}

# 创建 DFA 对象（允许部分转移）
dfa = DFA(
    states=states,
    input_symbols=input_symbols,
    transitions=transitions,
    initial_state=initial_state,
    final_states=final_states,
    allow_partial=True  # 关键！允许不完整转移函数
)

# 可视化 DFA
visual_dfa = VisualDFA(dfa)

# 绘制图形
visual_dfa.show_diagram(
    view=True,
    filename='cos',
    horizontal=True,
    format_type="png",
)
