@echo off
cd src
java -jar ..\lib\jflex-full-1.9.1.jar --outdir scanner oberon.flex
cd ..
pause