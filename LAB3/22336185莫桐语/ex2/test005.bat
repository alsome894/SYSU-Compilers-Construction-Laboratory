@echo off
@echo Running Testcase 005: IllegalIdentifierLengthException
@echo ==============================================
cd bin

java -classpath .;..\lib\java-cup-11b-runtime.jar Main ..\testcases\main.005

cd ..
@echo ==============================================
pause
@echo on