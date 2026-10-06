package vfs;

import java.util.Objects;

/**
 * Файл виртуальной файловой системы. Содержимое хранится в памяти
 * в виде массива байт; в JSON представляется строкой в base64.
 */
public final class VfsFile implements VfsNode {
    private final String name;
    private final byte[] content;

    /**
     * Создаёт файл.
     * @param name    имя файла
     * @param content содержимое файла
     */
    public VfsFile(String name, byte[] content) {
        this.name = Objects.requireNonNull(name);
        this.content = Objects.requireNonNull(content).clone();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает копию содержимого файла.
     * @return массив байт с содержимым
     */
    public byte[] getContent() {
        return content.clone();
    }

    /**
     * Возвращает размер файла в байтах.
     * @return размер файла
     */
    public int size() {
        return content.length;
    }
}