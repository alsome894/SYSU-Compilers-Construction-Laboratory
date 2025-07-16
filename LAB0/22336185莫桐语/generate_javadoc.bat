@echo off
setlocal

rem 如果 javadoc 文件夹不存在，则创建之
if not exist javadoc (
    mkdir javadoc
)

rem 生成 javadoc 文档
rem -encoding 指定源文件编码
rem -docencoding 指定生成文档的编码
rem -charset 指定文档页面使用的字符集
javadoc -private -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d javadoc src\Main.java src\TaxBracket.java src\TaxCalculator.java

pause
