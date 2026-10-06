package vfs;

/**
 * Узел виртуальной файловой системы: файл или каталог.
 */
public sealed interface VfsNode permits VfsFile, VfsDirectory {
    /**
     * Возвращает имя узла.
     * @return имя узла
     */
    String getName();
}