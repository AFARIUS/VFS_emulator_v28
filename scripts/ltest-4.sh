#!/bin/bash
# Тестирование ошибок загрузки VFS и совместной работы с стартовым скриптом.

echo "=== Error 1: non-existent VFS file ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs/missing.json"

echo
echo "=== Error 2: invalid JSON ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs/invalid.json"

echo
echo "=== Combined: minimal VFS + startup script ==="
./gradlew run --args="--vfs ./vfs/minimal.json --script ./scripts/startup.txt"

echo
echo "=== Combined: several VFS + startup script ==="
./gradlew run --args="--vfs ./vfs/several.json --script ./scripts/startup.txt"

echo
echo "=== Combined: deep VFS + startup script ==="
./gradlew run --args="--vfs ./vfs/deep.json --script ./scripts/startup.txt"

echo
echo "All error tests completed."