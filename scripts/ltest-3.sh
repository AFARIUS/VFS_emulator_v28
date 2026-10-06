#!/bin/bash
# Тестирование загрузки различных вариантов VFS.

echo "=== Test 1: minimal VFS ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs/min-vfs.json"

echo
echo "=== Test 2: VFS with several files ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs/standart-vfs.json"

echo
echo "=== Test 3: deep VFS (at least 3 levels) ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs/deep-vfs.json"

echo
echo "All VFS loading tests completed."