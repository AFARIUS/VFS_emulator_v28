@echo off
REM Тестирование граничных случаев rmdir и help.

cd /d "%~dp0.."

echo === Edge 1: rmdir without arguments ===
(echo rmdir & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Edge 2: rmdir on missing path ===
(echo rmdir nowhere & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Edge 3: rmdir without VFS ===
(echo rmdir empty & echo exit) | call gradlew.bat run

echo.
echo === Edge 4: rmdir with extra arguments ===
(echo rmdir a b & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Edge 5: help with unexpected arguments ===
(echo help extra & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo All edge tests completed.