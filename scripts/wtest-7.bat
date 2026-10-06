@echo off
REM Тестирование команд rmdir и help.

cd /d "%~dp0.."

echo === Test 1: help ===
(echo help & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 2: rmdir removes empty directory ===
(echo ls & echo rmdir empty & echo ls & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 3: rmdir on non-empty directory fails ===
(echo rmdir docs & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 4: rmdir on file fails ===
(echo rmdir docs/guide.txt & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo All rmdir/help tests completed.