@echo off
setlocal enabledelayedexpansion

REM 清理旧的编译文件
rmdir /s /q bin 2>nul
mkdir bin
rmdir /s /q test-bin 2>nul
mkdir test-bin

REM 编译主代码
javac -encoding UTF-8 -d bin src\*.java
if %errorlevel% neq 0 (
    echo 主代码编译失败
    exit /b %errorlevel%
)

REM 编译测试代码
javac -encoding UTF-8 -d test-bin -cp "bin;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" test\ParserTest.java
if %errorlevel% neq 0 (
    echo 测试代码编译失败
    exit /b %errorlevel%
)

REM 运行单元测试
java -cp "test-bin;bin;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ParserTest

pause
endlocal