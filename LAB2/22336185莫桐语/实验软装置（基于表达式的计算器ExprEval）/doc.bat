@echo off
cd src
javadoc -private -author -version -d ..\doc -encoding UTF-8 -classpath ..\bin parser\*.java parser\scanner\*.java parser\token\*.java parser\expr\*.java parser\scanner\dfa\*.java
cd ..
pause
@echo on
