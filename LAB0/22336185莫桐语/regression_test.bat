@echo off
setlocal

REM 获取当前脚本所在目录，并切换到该目录
set "currentDir=%~dp0"
cd /d "%currentDir%"

REM 运行程序并捕获输出
(
    echo 1
    echo 50000
    echo 4
) | java -cp bin Main > output1.txt

(
    echo 1
    echo 5000
    echo 4
) | java -cp bin Main > output2.txt

chcp 65001 >nul
REM 检查输出是否包含正确的税额
findstr /C:"9090.00" output1.txt >nul
if %errorlevel% == 0 (
    echo 标准回归测试通过
) else (
    echo 标准回归测试失败
)

findstr /C:"0.00" output2.txt >nul
if %errorlevel% == 0 (
    echo 边缘回归测试通过
) else (
    echo 边缘回归测试失败
)

REM 清理临时文件
del output1.txt
del output2.txt

pause
endlocal

