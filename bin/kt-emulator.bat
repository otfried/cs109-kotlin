@echo off

setlocal
setlocal enabledelayedexpansion
call :set_home
call :set_cp

java -ea %_CP% org.otfried.cs109emulator.EmulatorKt %*
goto end

:set_home
  set _BIN_DIR=
  for %%i in (%~sf0) do set _BIN_DIR=%_BIN_DIR%%%~dpsi
  set _KOTLIN_HOME=%_BIN_DIR%..
goto :eof

:set_cp
  set _CP=-cp %_KOTLIN_HOME%\lib\kotlin-runtime.jar

  if exist classes set _CP=!_CP!;classes

  for %%f in ( %_KOTLIN_HOME%\ext\*.jar ) do set _CP=!_CP!;%%f
goto :eof

:end
