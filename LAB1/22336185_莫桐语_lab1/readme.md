# 22336185 莫桐语 · 编译原理第1次实验

## 项目结构说明

**（实验0中我误以为实验报告只需要作为设计文档以供参考即可、所以实验报告没有放在实验0提交的压缩包文件夹根目录，而是放在了doc\design.pdf。如果助教学长学姐批改时看漏的话麻烦复查一下，感谢！！！）**

### ▮ 核心程序

`Postfix.java`  
└─ 中缀表达式转换命令行工具，是程序整体的最外层封装

`Parser.java`  
└─ 中缀表达式到后缀表达式的转换器，支持加强的错误恢复机制

`ParserError.java`  
└─ 表示解析过程中出现的语法错误，记录错误位置、描述和类型信息

### ▮ 可选内容完成情况

`compare\`
└─ 完成了消除尾递归的初步性能比较

`Parser.java`
└─ 完成了错误分类、错误定位、出错恢复等要求

`test\`
└─ 完成了简单的对实验项目的单元测试

### ▮ 编译运行脚本

`build.bat`  
└─ 编译.java文件 → 生成`bin\`目录下的.class可执行文件

`run.bat`  
└─ 运行可执行文件，启动终端，提示输入表达式

`doc.bat`  
└─ 生成javadoc文件 → 生成`doc\`目录下的.html注释文件

`compare\Data_Generate.bat`
└─ 生成比较实验中需要的随机表达式

`compare\run_compare.bat`
└─ 编译并运行比较测试脚本

### ▮ 功能测试脚本
`test.bat`  
└─ 编译并运行JUnit测试类 → 生成`test-bin\`目录下的.class可执行文件

`testcase-00i.bat`  
└─ 运行对应测试脚本

`testcase.bat`  
└─ 运行上述所有测试脚本

### ▮ 其他文件夹
`src\`
└─ 程序源代码.java
　　├─ Postfix.java  
　　├─ Parser.java  
　　└─ ParserError.java

`bin\`
└─ 编译生成的可执行文件.class
　　├─ Postfix.class  
　　├─ Parser.class  
　　└─ ParserError.class

`lib\`
└─ 依赖库(主要为junit的依赖项)
　　├─ hamcrest-core-1.3.jar    
　　└─ junit-4.13.2.jar

`doc\`
└─ 使用javadoc生成的相关.html文件

`compare\`
└─ 消除尾递归前后程序的性能比较实验
　　├─ src\  
　　├─ bin\  
　　├─ Data_Generate.bat
　　├─ run_compare.bat
　　└─ test_.txt

`test\`
└─ 单元测试程序源代码.java
　　└─ ParserTest.java

`test-bin\`
└─ 单元测试程序可执行文件.class
　　└─ ParserTest.class

`testcases\`
└─ 测试用例源代码

### ▮ 运行配置

- 控制台支持UTF-8编码
- jdk1.8.0_201