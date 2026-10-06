@echo off
REM Тестирование обработки ошибок команд.

cd /d "%~dp0.."

echo === Error 1: ls without VFS ===
(echo ls & echo exit) | call gradlew.bat run

echo.
echo === Error 2: cd to unknown ===
(echo cd nowhere & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Error 3: tail on directory ===
(echo tail docs & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Error 4: tail with invalid number ===
(echo tail -n abc readme.txt & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo All error tests completed.