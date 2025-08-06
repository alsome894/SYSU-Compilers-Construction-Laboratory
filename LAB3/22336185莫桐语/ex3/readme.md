# 22336185 莫桐语 · 编译原理第3次实验.ex3

## 项目结构说明

### ▮ 编译运行脚本

```
build.bat	# 编译脚本
doc.bat		# 生成javadoc
gen.bat		# 生成代码脚本
run.bat		# 运行正确版本脚本
```



### ▮ 功能测试脚本

```
test.bat	# 运行所有变异版本脚本
test*.bat	# 运行变异版本脚本
```



### ▮ 其他文件夹

```
src/			
├── Main.java		
├── exceptions/
├── Node.java
├── Parser.java
├── Symbol.java
└── OberonScanner.java

testcases/			# 测试用源代码代码
├── main.obr		# 正确版本源代码
└── main.* 			# 变异版本源代码
```



### ▮ 运行配置

- 控制台支持UTF-8编码
- jdk1.8.0_201
