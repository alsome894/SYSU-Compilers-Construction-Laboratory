@echo off
@echo Running Testcase 007: MissingRightParenthesisException
@echo ==============================================
cd bin

java -classpath .;..\lib\java-cup-11b-runtime.jar;..\lib\java-cup-11b.jar;..\lib\callgraph.jar;..\lib\flowchart.jar;..\lib\jgraph.jar Main ..\testcases\main.007

cd ..
@echo ==============================================
pause
@echo on