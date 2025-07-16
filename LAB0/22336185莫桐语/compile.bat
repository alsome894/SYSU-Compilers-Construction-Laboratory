chcp 65001 >nul
@echo off
setlocal

REM 获取当前脚本所在目录，并切换到该目录
set "currentDir=%~dp0"
cd /d "%currentDir%src"

REM 编译Java
javac -encoding UTF-8 Main.java -d %currentDir%bin

REM 检查编译结果
if %errorlevel% equ 0 (
    echo 编译成功！.class文件已保存到bin。
) else (
    echo 编译失败，请检查错误！
)

pause
endlocal