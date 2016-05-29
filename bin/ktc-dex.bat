@echo off

setlocal
setlocal enabledelayedexpansion
call :set_home
call :set_cp

set _DEX=%~n1.dex
set _JAR=%~n1.jar

call %~dps0kotlinc.bat %_CP% -d %_JAR% %* && java -jar %_KOTLIN_HOME%\dx\dx.jar --dex --output=%_DEX% %_JAR%

goto end

:set_home
  set _BIN_DIR=
  for %%i in (%~sf0) do set _BIN_DIR=%_BIN_DIR%%%~dpsi
  set _KOTLIN_HOME=%_BIN_DIR%..
goto :eof

:set_cp
  set _CP=
  set "_SEP=-cp^ "

  if exist classes (
    set _CP=-cp classes
    set _SEP=;
  )

  for %%f in ( %_KOTLIN_HOME%\ext\*.jar ) do (
    set _CP=!_CP!!_SEP!%%f
    set _SEP=;
  )
goto :eof

:end
