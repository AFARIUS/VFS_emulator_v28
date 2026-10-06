#!/bin/bash
# Тестирование параметров командной строки: особые случаи.

echo "=== Edge 1: --vfs with non-existent path ==="
echo "exit" | ./gradlew run --args="--vfs ./does-not-exist.json"

echo
echo "=== Edge 2: --script without value ==="
echo "exit" | ./gradlew run --args="--script"

echo
echo "=== Edge 3: reversed order ==="
./gradlew run --args="--script ./scripts/startup.txt --vfs ./vfs-data.json"

echo
echo "=== Edge 4: unknown option ==="
echo "exit" | ./gradlew run --args="--unknown value"

echo
echo "All edge tests completed."