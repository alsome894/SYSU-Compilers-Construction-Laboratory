@echo off
cd src
javadoc -private -author -version -d ..\doc -encoding UTF-8 -classpath "..\bin;..\lib\java-cup-11b.jar" *.java scanner\*.java scanner\token\*.java
cd ..
pause
@echo on
