@echo off
cd src
if exist OberonScanner.java (
    del OberonScanner.java
)
if exist Parser.java (
    del Parser.java
)
if exist Symbol.java (
    del Symbol.java
)
java -jar ..\lib\jflex-full-1.9.1.jar oberon.flex
java -jar ..\lib\java-cup-11b.jar -interface -parser Parser -symbols Symbol -nonterms oberon.cup
cd ..
pause