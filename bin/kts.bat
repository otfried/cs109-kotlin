@echo off

setlocal
setlocal enabledelayedexpansion
call :set_home
call :set_cp

call %~dps0kotlinc.bat %_CP% -script %*

goto :fin

:set_home
  set _BIN_DIR=
  for %%i in (%~sf0) do set _BIN_DIR=%_BIN_DIR%%%~dpsi
  set _KOTLIN_HOME=%_BIN_DIR%..
goto :eof

:set_cp
  set _CP=
  rem next line must end with a space! 
  set _SEP=-cp^ 

  if exist classes (
    set _CP=-cp classes
    set _SEP=;
  )

  for %%f in ( %_KOTLIN_HOME%\ext\*.jar ) do (
    set _CP=!_CP!!_SEP!%%f
    set _SEP=;
  )
goto :eof

:fin
