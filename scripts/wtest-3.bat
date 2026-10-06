@echo off
REM Тестирование загрузки различных вариантов VFS.

cd /d "%~dp0.."

echo === Test 1: minimal VFS ===
echo exit | call gradlew.bat run --args="--vfs ./vfs/min-vfs.json"

echo.
echo === Test 2: VFS with several files ===
echo exit | call gradlew.bat run --args="--vfs ./vfs/standart-vfs.json"

echo.
echo === Test 3: deep VFS (at least 3 levels) ===
echo exit | call gradlew.bat run --args="--vfs ./vfs/deep-vfs.json"

echo.
echo All VFS loading tests completed.