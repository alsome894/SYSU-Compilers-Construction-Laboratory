@echo off
setlocal

REM 获取当前脚本所在目录，并切换到该目录
set "currentDir=%~dp0"
cd /d "%currentDir%"

REM 运行程序
java -cp bin Main

pause
endlocal