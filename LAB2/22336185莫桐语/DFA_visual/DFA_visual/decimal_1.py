#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
使用 automata-lib 定义并可视化识别十进制浮点数 DFA（含指数形式）
"""

from automata.fa.dfa import DFA

def main():
    dfa_decimal = DFA(
        states={'0','10','11','12','13','14','15'},
        input_symbols={'digit','.','E','e','+','-'},
        transitions={
            '0':  {'digit':'10'},
            '10': {'.':'11','E':'13','e':'13','digit':'10'},
            '11': {'digit':'12'},
            '12': {'E':'13','e':'13','digit':'12'},
            '13': {'+':'14','-':'14','digit':'15'},
            '14': {'digit':'15'},
            '15': {'digit':'15'}
        },
        initial_state='0',
        final_states={'10','12','15'},
        allow_partial=True
    )

    output_file = 'decimal_dfa.png'
    dfa_decimal.show_diagram(path=output_file)
    print(f"DFA 可视化图已保存至：{output_file}")

if __name__ == '__main__':
    main()
