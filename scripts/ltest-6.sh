#!/bin/bash
# Тестирование обработки ошибок команд.

echo "=== Error 1: ls without VFS ==="
printf "ls\nexit\n" | ./gradlew run

echo
echo "=== Error 2: cd to unknown ==="
printf "cd nowhere\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Error 3: tail on directory ==="
printf "tail docs\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Error 4: tail with invalid number ==="
printf "tail -n abc readme.txt\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "All error tests completed."