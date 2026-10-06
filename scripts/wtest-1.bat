@echo off
REM Тестирование параметров командной строки: базовые сценарии.

cd /d "%~dp0.."

echo === Test 1: --vfs and --script together ===
call gradlew.bat run --args="--vfs ./vfs-data.json --script ./scripts/startup.txt"

echo.
echo === Test 2: --vfs only ===
echo exit | call gradlew.bat run --args="--vfs ./vfs-data.json"

echo.
echo === Test 3: --script only ===
call gradlew.bat run --args="--script ./scripts/startup.txt"

echo.
echo === Test 4: no parameters ===
echo exit | call gradlew.bat run

echo.
echo All basic tests completed.