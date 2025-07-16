@echo off
@echo Running Testcase 001: IllegalSymbolException
@echo ==============================================
cd bin

java -classpath .;..\lib\java-cup-11b-runtime.jar Main ..\testcases\main.001

cd ..
@echo ==============================================
pause
@echo on