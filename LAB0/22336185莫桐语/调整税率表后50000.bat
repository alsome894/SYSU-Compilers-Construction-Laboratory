@echo off
setlocal

echo expect '9050.00'

REM 获取当前脚本所在目录，并切换到该目录
set "currentDir=%~dp0"
cd /d "%currentDir%"

REM 运行程序并捕获输出
(
    echo 3
    echo 1
    echo 5000
    echo 5
    echo 0
    echo 1
    echo 50000
    echo 4
) | java -cp bin Main

pause
endlocal