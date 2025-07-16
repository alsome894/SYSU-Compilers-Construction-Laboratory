@echo off
setlocal

rem 如果 bin 文件夹不存在，则创建之
if not exist bin (
    mkdir bin
)

rem 设置 CLASSPATH：当前目录、bin 文件夹以及 lib 下的 JUnit 和 Hamcrest jar 文件
set CLASSPATH=.;bin;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar

rem 编译 src 目录下所有 Java 源文件，将 .class 文件输出到 bin 目录
javac -encoding UTF-8 -d bin -cp %CLASSPATH% src\*.java

rem 使用 JUnitCore 运行测试（同时运行 TaxBracketTest 和 TaxCalculatorTest）
java -cp %CLASSPATH% org.junit.runner.JUnitCore TaxBracketTest TaxCalculatorTest

pause
