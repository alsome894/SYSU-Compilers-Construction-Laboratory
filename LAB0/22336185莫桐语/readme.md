# 22336185 莫桐语 · 编译原理第0次实验

## 项目结构说明

### ▮ 核心程序
`Main.java`  
└─ 基于命令行界面的计算个人所得税（工资、薪金所得）应用程序的源代码

`TaxBracket.java`  
└─ TaxBracket 类用于表示单个税率级别

`TaxCalculator.java`  
└─ TaxCalculator 类用于计算个人所得税

### ▮ 编译运行脚本
`compile.bat`  
└─ 编译.java文件 → 生成`bin\`目录下的.class可执行文件

`run.bat`  
└─ 启动交互式终端，支持输入对应数字选择功能：  
　　├─ 1. 个税计算  
　　├─ 2. 起征点动态调整  
　　├─ 3. 税率表修改  
　　└─ 4. 退出系统

`generate_javadoc.bat`  
└─ 生成javadoc文件 → 生成`javadoc\`目录下的.html注释文件

### ▮ 功能测试脚本
`regression_test.bat`  
└─ 自动化回归测试框架  
　　├─ 标准测试（输入50,000）  
　　└─ 边界测试（输入5,000）  
　　 成功时输出：  
　　　　`标准回归测试通过`  
　　　　`边缘回归测试通过`
　　 失败时输出：  
　　　　`标准回归测试失败`  
　　　　`边缘回归测试失败`

`test.bat`  
└─ 使用junit测试框架  
　　├─ 编译并运行`TaxBracketTest.java`  
　　└─ 编译并运行`TaxCalculatorTest.java`  
　　 成功时输出：  
　　　　`OK (2 tests)`
　　 失败时输出错误信息

| 执行脚本| 测试场景                       |
| -------------- | ----------------------- |
| `标准测试5000.bat`| 标准边界测试         |
| `标准测试50000.bat` | 标准测试           |
| `调整起征点后50000.bat`| 起征点调高测试  |
| `调整税率表后50000.bat`| 税率表修改测试  |

### ▮ 配置调整测试
`调整起征点后50000.bat`  
└─ 验证起征点参数更新后的计算逻辑

`调整税率表后50000.bat`  
└─ 测试场景：  
　　├─ 级数1上限调整  
　　└─ 税率数值修改后的计算验证

### ▮ 其他文件夹
`src\`
└─ 程序源代码.java
　　├─ Main.java  
　　├─ TaxBracket.java  
　　├─ TaxCalculator.java
　　├─ TaxBracketTest.java
　　└─ TaxCalculatorTest.java

`bin\`
└─ 编译生成的可执行文件.class
　　├─ Main.class  
　　├─ TaxBracket.class  
　　└─ TaxCalculator.class

`lib\`
└─ 依赖库(主要为junit的依赖项)
　　├─ hamcrest-core-1.3.jar    
　　└─ junit-4.13.2.jar

`doc\`
└─ 设计思路兼实验报告
　　├─ design.md    
　　└─ design.pdf
　　
`javadoc\`
└─ 使用javadoc生成的相关.html文件

### ▮ 运行配置

- 控制台支持UTF-8编码
- jdk1.8.0_201