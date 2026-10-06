#!/bin/bash
# Тестирование граничных случаев rmdir и help.

echo "=== Edge 1: rmdir without arguments ==="
printf "rmdir\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Edge 2: rmdir on missing path ==="
printf "rmdir nowhere\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Edge 3: rmdir without VFS ==="
printf "rmdir empty\nexit\n" | ./gradlew run

echo
echo "=== Edge 4: rmdir with extra arguments ==="
printf "rmdir a b\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Edge 5: help with unexpected arguments ==="
printf "help extra\nexit\n" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "All edge tests completed."