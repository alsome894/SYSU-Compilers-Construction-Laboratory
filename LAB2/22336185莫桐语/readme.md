# 22336185 莫桐语 · 编译原理第2次实验

| 实验  | **基于表达式的计算器** | 电话     | 18977987225 |
| ----- | ---------------------- | -------- | ----------- |
| 学号  | 22336185               | 姓名     | 莫桐语      |
| Email | 653572250@qq.com       | 完成日期 | 2025/4/25   |

## 项目结构说明

### ▮ 编译运行脚本

`build.bat`  
└─ 编译.java文件 → 生成`bin\src`目录下的.class可执行文件

`run.bat`  
└─ 运行可执行文件，启动终端，提示输入表达式

`doc.bat`  
└─ 生成javadoc文件 → 生成`doc\`目录下的.html注释文件

`add_exception.bat`
└─ 编译新增的异常类型 → 生成`bin\exceptions`目录下的.class可执行文件

### ▮ 功能测试脚本

`test_simple.bat`  
└─ 调用`testcases\simple.xml`，进行`simple`级别测试

`test_standard.bat`  
└─ 调用`testcases\standard.xml`，进行`standard`级别测试

`test_correct.bat`  
└─ 调用`testcases\correct_cases.xml`，进行正确案例测试

`test_exception.bat`  
└─ 调用`testcases\exception_cases.xml`，进行异常案例测试

### ▮ 其他文件夹

`src\`	# 程序源代码.java
├─ `excpetions\`	# 各种异常源代码.java
└─ `parser\`	# 算符优先分析器
	├─ `Caculator.java`	# 计算器程序入口
	├─ `Parser.java`	# 算符优先分析器模块源代码.java
	├─ `Reducer.java`	# 归约器模块源代码.java
	├─ `token\`	# 各种单词源代码.java
	└─ `scanner\`	# 词法分析器模块
		├─ `Myscanner.java`	# 词法分析器模块源代码.java
		└─ `dfa\`	# 各种DFA源代码.java



`bin\`
└─ 编译生成的可执行文件.class

`doc\`
└─ 使用javadoc生成的相关文件.html

`testcases\`
└─ 测试脚本源代码.xml

`DFA_visual\`
└─ 生成可视化DFA源代码.py

### ▮ 运行配置

- 控制台支持UTF-8编码
- jdk1.8.0_201