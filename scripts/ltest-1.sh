#!/bin/bash
# Тестирование параметров командной строки: базовые сценарии.

echo "=== Test 1: --vfs and --script together ==="
./gradlew run --args="--vfs ./vfs-data.json --script ./scripts/startup.txt"

echo
echo "=== Test 2: --vfs only ==="
echo "exit" | ./gradlew run --args="--vfs ./vfs-data.json"

echo
echo "=== Test 3: --script only ==="
./gradlew run --args="--script ./scripts/startup.txt"

echo
echo "=== Test 4: no parameters ==="
echo "exit" | ./gradlew run

echo
echo "All basic tests completed."