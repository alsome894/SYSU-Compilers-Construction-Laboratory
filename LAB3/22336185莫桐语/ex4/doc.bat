@echo off
cd src
javadoc -private -author -version -d ..\doc -encoding UTF-8 -classpath "..\bin;..\lib\java-cup-11b-runtime.jar;..\lib\jgraph.jar;..\lib\flowchart.jar" *.java scanner\*.java scanner\token\*.java parser\*.java parser\env\*.java parser\type\*.java
cd ..
pause
@echo on
