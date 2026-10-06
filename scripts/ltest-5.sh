#!/bin/bash
# Тестирование команд ls, cd, history, uptime, tail.

echo "=== Test 1: ls in root ==="
printf "ls\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 2: cd + ls in subdirectory ==="
printf "cd docs\nls\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 3: tail on file ==="
printf "cd docs\ntail -n 2 guide.txt\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 4: history and uptime ==="
printf "ls\nhistory\nuptime\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "All command tests completed."