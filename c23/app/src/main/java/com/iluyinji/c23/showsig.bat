@echo off
chcp 65001

:: type %1  :: 类似与linux:cat
:: echo %1

::- %1 = .\Hello.java
set a=%1
set p1=%1
call :getname %a%
:: :getname
set fileName=%~nx1
::- fileName = Hello.java

set class=%p1:java=class%
echo %class%
::- class = .\Hello.class

:: java -> class
javac -encoding UTF-8  %1
:: javap <- Hello.class
javap -s -p %class%
:: del Hello.class
del %class%

