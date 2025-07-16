@echo off
cd src
javac -d ..\bin -encoding UTF-8 -classpath ..\bin parser\*.java parser\scanner\*.java parser\token\*.java parser\expr\*.java parser\scanner\dfa\*.java
cd ..
pause
@echo on
