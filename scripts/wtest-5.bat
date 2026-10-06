@echo off
REM Тестирование команд ls, cd, history, uptime, tail.

cd /d "%~dp0.."

echo === Test 1: ls in root ===
(echo ls & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 2: cd + ls in subdirectory ===
(echo cd docs & echo ls & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 3: tail on file ===
(echo cd docs & echo tail -n 2 guide.txt & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 4: history and uptime ===
(echo ls & echo history & echo uptime & echo exit) | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo All command tests completed.