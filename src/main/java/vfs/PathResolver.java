package vfs;

/**
 * Вспомогательный класс для определения путей в дереве VFS.
 * Поддерживает абсолютные пути (начинающиеся с "/"),
 * относительные пути, "." и "..".
 */
public final class PathResolver {

    private PathResolver() {
    }

    /**
     * Определяет путь относительно текущего каталога.
     * @param root корень VFS
     * @param current текущий каталог
     * @param path путь (абсолютный или относительный)
     * @return найденный узел (файл или каталог) или null, если путь не найден
     */
    public static VfsNode resolve(VfsDirectory root, VfsDirectory current, String path) {
        if (path == null || path.isEmpty() || ".".equals(path)) {
            return current;
        }
        VfsDirectory cursor = path.startsWith("/") ? root : current;
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                cursor = parentOf(root, cursor);
                continue;
            }
            VfsNode child = cursor.getChild(part);
            if (child == null) {
                return null;
            }
            if (!(child instanceof VfsDirectory dir)) {
                return child;
            }
            cursor = dir;
        }
        return cursor;
    }

    private static VfsDirectory parentOf(VfsDirectory root, VfsDirectory dir) {
        if (dir == root) {
            return root;
        }
        for (VfsNode child : root.getChildren().values()) {
            if (child == dir) {
                return root;
            }
            if (child instanceof VfsDirectory subdir) {
                VfsDirectory found = findParentOf(subdir, dir, root);
                if (found != null) {
                    return found;
                }
            }
        }
        return root;
    }

    private static VfsDirectory findParentOf(VfsDirectory root, VfsDirectory target, VfsDirectory parent) {
        for (VfsNode child : root.getChildren().values()) {
            if (child == target) {
                return root;
            }
            if (child instanceof VfsDirectory subdir) {
                VfsDirectory found = findParentOf(subdir, target, root);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}