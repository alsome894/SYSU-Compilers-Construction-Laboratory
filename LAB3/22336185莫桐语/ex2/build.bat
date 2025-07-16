@echo off
cd src
javac -d ..\bin -encoding UTF-8 -classpath ..\bin;..\lib\java-cup-11b-runtime.jar *.java scanner\*.java exceptions\*.java
cd ..
pause
@echo on
