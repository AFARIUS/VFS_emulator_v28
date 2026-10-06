package vfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Контекст выполнения команд: виртуальная файловая система,
 * текущий каталог и история введённых команд.
 */
public class CommandContext {
    private VirtualFileSystem vfs;
    private VfsDirectory root;
    private VfsDirectory currentDir;
    private final List<String> history = new ArrayList<>();
    private final long startTime = System.currentTimeMillis();

    /**
     * Устанавливает загруженную VFS и сбрасывает текущий каталог в корень.
     * @param vfs виртуальная файловая система или null
     */
    public void setVfs(VirtualFileSystem vfs) {
        this.vfs = vfs;
        this.root = vfs != null ? vfs.getRoot() : null;
        this.currentDir = root;
    }

    /** Возвращает загруженную VFS или null. */
    public VirtualFileSystem getVfs() {
        return vfs;
    }

    /** Возвращает корень VFS или null. */
    public VfsDirectory getRoot() {
        return root;
    }

    /** Возвращает текущий каталог или null, если VFS не загружена. */
    public VfsDirectory getCurrentDir() {
        return currentDir;
    }

    /**
     * Устанавливает текущий каталог.
     * @param dir новый текущий каталог
     */
    public void setCurrentDir(VfsDirectory dir) {
        this.currentDir = dir;
    }

    /** Возвращает true, если VFS загружена. */
    public boolean hasVfs() {
        return vfs != null;
    }

    /**
     * Добавляет строку в историю команд.
     * @param line введённая строка
     */
    public void addHistory(String line) {
        history.add(line);
    }

    /** Возвращает неизменяемый список истории. */
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    /** Возвращает время работы эмулятора в миллисекундах. */
    public long getUptimeMillis() {
        return System.currentTimeMillis() - startTime;
    }
}