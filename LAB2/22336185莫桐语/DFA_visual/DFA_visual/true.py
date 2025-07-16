# 导入所需的库和模块
from automata.fa.dfa import DFA
from visual_automata.fa.dfa import VisualDFA

# 定义 DFA 的五个组成部分
# 1. 状态集合（不包含死状态）
states = {'0', '1', '2', '3', '4'}
# 2. 输入符号（仅包含有效字符）
input_symbols = {'t', 'r', 'u', 'e'}
# 3. 转移函数（仅定义有效路径，其他输入自动拒绝）
transitions = {
    '0': {
        't': '1', 
        # 其他字符不定义，视为自动进入隐式死状态
    },
    '1': {
        'r': '2', 
        # 其他字符不定义
    },
    '2': {
        'u': '3', 
        # 其他字符不定义
    },
    '3': {
        'e': '4', 
        # 其他字符不定义
    },
    '4': {}  # 接受状态，无需转移
}
# 4. 初始状态
initial_state = '0'
# 5. 接受状态集合
final_states = {'4'}

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
    filename='true',
    horizontal=True,
    format_type="png",
)
# 测试
test_string = 'true'
print(f'字符串 "{test_string}" 是否被接受？ {dfa.accepts_input(test_string)}')  # 输出 True