@echo off
setlocal

echo expect '7590.00'

REM 获取当前脚本所在目录，并切换到该目录
set "currentDir=%~dp0"
cd /d "%currentDir%"

REM 运行程序并捕获输出
(
    echo 2
    echo 10000
    echo 1
    echo 50000
    echo 4
) | java -cp bin Main

pause
endlocal