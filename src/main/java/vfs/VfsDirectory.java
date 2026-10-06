package vfs;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Каталог виртуальной файловой системы. Содержит именованных потомков.
 */
public final class VfsDirectory implements VfsNode {
    private final String name;
    private final Map<String, VfsNode> children = new LinkedHashMap<>();

    /**
     * Создаёт каталог.
     * @param name имя каталога
     */
    public VfsDirectory(String name) {
        this.name = Objects.requireNonNull(name);
    }

    /**
     * Добавляет потомка в каталог.
     * @param child узел-потомок
     */
    public void addChild(VfsNode child) {
        children.put(child.getName(), child);
    }

    /**
     * Возвращает потомка по имени или null, если его нет.
     * @param childName имя потомка
     * @return узел-потомок или null
     */
    public VfsNode getChild(String childName) {
        return children.get(childName);
    }

    /**
     * Возвращает неизменяемое представление потомков.
     * @return карта потомков
     */
    public Map<String, VfsNode> getChildren() {
        return Collections.unmodifiableMap(children);
    }

    @Override
    public String getName() {
        return name;
    }
}