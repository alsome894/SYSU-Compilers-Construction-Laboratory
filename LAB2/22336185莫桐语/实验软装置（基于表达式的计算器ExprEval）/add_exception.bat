@echo off
cd src
javac -d ..\bin -encoding UTF-8 -classpath ..\bin exceptions\*.java
cd ..
pause
@echo on