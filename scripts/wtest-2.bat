@echo off
REM Тестирование параметров командной строки: особые случаи.

cd /d "%~dp0.."

echo === Edge 1: --vfs with non-existent path ===
echo exit | call gradlew.bat run --args="--vfs ./does-not-exist.json"

echo.
echo === Edge 2: --script without value ===
echo exit | call gradlew.bat run --args="--script"

echo.
echo === Edge 3: reversed order ===
call gradlew.bat run --args="--script ./scripts/startup.txt --vfs ./vfs-data.json"

echo.
echo === Edge 4: unknown option ===
echo exit | call gradlew.bat run --args="--unknown value"

echo.
echo All edge tests completed.