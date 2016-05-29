@echo off

setlocal
setlocal enabledelayedexpansion
call :set_home

call %~dps0kotlinc-js.bat -library-files %_KOTLIN_HOME%\ext\cs109-jslib.jar %*
goto end

:set_home
  set _BIN_DIR=
  for %%i in (%~sf0) do set _BIN_DIR=%_BIN_DIR%%%~dpsi
  set _KOTLIN_HOME=%_BIN_DIR%..
goto :eof

:end
