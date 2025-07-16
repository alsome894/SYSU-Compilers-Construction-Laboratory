@echo off
cd src
javac -d ..\bin -encoding UTF-8 -classpath ..\bin;..\lib\java-cup-11b-runtime.jar;..\lib\java-cup-11b.jar;..\lib\callgraph.jar;..\lib\flowchart.jar;..\lib\jgraph.jar *.java exceptions\*.java
cd ..
pause
@echo on
