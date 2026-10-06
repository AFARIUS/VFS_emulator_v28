package vfs.commands;

import vfs.CommandContext;
import vfs.PathResolver;
import vfs.VfsDirectory;
import vfs.VfsNode;

/**
 * Команда rmdir — удаляет пустой каталог из VFS.
 * Изменения производятся в памяти.
 */
public class RmdirCommand implements Command {

    /**
     * Выполняет команду.
     * @param context контекст с VFS и текущим каталогом
     * @param args аргументы: ровно один путь к каталогу
     * @return true, если каталог удалён
     */
    @Override
    public boolean execute(CommandContext context, String[] args) {
        if (!context.hasVfs()) {
            System.out.println("rmdir: VFS is not loaded");
            return false;
        }
        if (args.length != 1) {
            System.out.println("rmdir: usage: rmdir <path>");
            return false;
        }
        VfsDirectory root = context.getRoot();
        VfsNode node = PathResolver.resolve(root, context.getCurrentDir(), args[0]);
        if (node == null) {
            System.out.println("rmdir: no such directory: " + args[0]);
            return false;
        }
        if (!(node instanceof VfsDirectory dir)) {
            System.out.println("rmdir: not a directory: " + args[0]);
            return false;
        }
        if (dir == root) {
            System.out.println("rmdir: cannot remove root directory");
            return false;
        }
        if (!dir.getChildren().isEmpty()) {
            System.out.println("rmdir: directory not empty: " + args[0]);
            return false;
        }
        VfsDirectory parent = findParent(root, dir);
        if (parent == null) {
            System.out.println("rmdir: cannot find parent of: " + args[0]);
            return false;
        }
        parent.removeChild(dir.getName());
        return true;
    }

    private VfsDirectory findParent(VfsDirectory current, VfsDirectory target) {
        for (VfsNode child : current.getChildren().values()) {
            if (child == target) {
                return current;
            }
            if (child instanceof VfsDirectory subdir) {
                VfsDirectory found = findParent(subdir, target);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
