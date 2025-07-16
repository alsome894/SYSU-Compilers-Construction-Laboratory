@echo off
setlocal

javac -encoding UTF-8 src\RecursiveParser.java src\LoopParser.java src\Benchmark.java -d bin
java -cp bin -Xss16m Benchmark

pause
endlocal