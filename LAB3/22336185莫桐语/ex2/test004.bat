@echo off
@echo Running Testcase 004: IllegalOctalException
@echo ==============================================
cd bin

java -classpath .;..\lib\java-cup-11b-runtime.jar Main ..\testcases\main.004

cd ..
@echo ==============================================
pause
@echo on