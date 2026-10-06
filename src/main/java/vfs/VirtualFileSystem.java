package vfs;

import java.util.Objects;

/**
 * Виртуальная файловая система, полностью размещённая в памяти.
 */
public final class VirtualFileSystem {
    private final String name;
    private final VfsDirectory root;

    /**
     * Создаёт виртуальную файловую систему.
     * @param name имя VFS
     * @param root корневой каталог
     */
    public VirtualFileSystem(String name, VfsDirectory root) {
        this.name = Objects.requireNonNull(name);
        this.root = Objects.requireNonNull(root);
    }

    public String getName() {
        return name;
    }

    public VfsDirectory getRoot() {
        return root;
    }

    /**
     * Подсчитывает общее число узлов в дереве, включая корень.
     * @return количество узлов
     */
    public int size() {
        return 1 + countChildren(root);
    }

    private int countChildren(VfsDirectory dir) {
        int total = 0;
        for (VfsNode child : dir.getChildren().values()) {
            total += 1;
            if (child instanceof VfsDirectory subdir) {
                total += countChildren(subdir);
            }
        }
        return total;
    }
}