@echo off
cd bin
java -classpath .;..\lib\java-cup-11b-runtime.jar Main ..\testcases\main.obr
cd ..
pause
@echo on
