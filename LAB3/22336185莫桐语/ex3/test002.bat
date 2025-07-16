@echo off
@echo Running Testcase 002: IllegalIntegerException
@echo ==============================================
cd bin

java -classpath .;..\lib\java-cup-11b-runtime.jar;..\lib\java-cup-11b.jar;..\lib\callgraph.jar;..\lib\flowchart.jar;..\lib\jgraph.jar Main ..\testcases\main.002

cd ..
@echo ==============================================
pause
@echo on