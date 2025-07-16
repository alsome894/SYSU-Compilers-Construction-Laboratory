@echo off
setlocal

javac -encoding UTF-8 src\DataGenerator.java -d bin
java -cp bin DataGenerator

pause
endlocal