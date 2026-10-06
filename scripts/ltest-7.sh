#!/bin/bash
# Тестирование команд rmdir и help.

echo "=== Test 1: help ==="
printf "help\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 2: rmdir removes empty directory ==="
printf "ls\nrmdir empty\nls\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 3: rmdir on non-empty directory fails ==="
printf "rmdir docs\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 4: rmdir on file fails ==="
printf "rmdir docs/guide.txt\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "All rmdir/help tests completed."