@echo off
cd src
javac -d ..\bin -encoding UTF-8 -classpath ..\lib\java-cup-11b-runtime.jar;..\lib\java-cup-11b.jar;..\lib\callgraph.jar;..\lib\flowchart.jar;..\lib\jgraph.jar *.java scanner\token\*.java scanner\*.java exceptions\*.java parser\*.java parser\env\*.java parser\type\*.java
cd ..
pause
@echo on
