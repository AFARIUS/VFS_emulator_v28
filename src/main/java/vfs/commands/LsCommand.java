package vfs.commands;

import vfs.CommandContext;
import vfs.PathResolver;
import vfs.VfsDirectory;
import vfs.VfsNode;

/**
 * Команда ls — выводит содержимое текущего или указанного каталога.
 * Поддерживает один необязательный аргумент — путь к каталогу.
 */
public class LsCommand implements Command {

    /**
     * Выполняет команду.
     *
     * @param context контекст с VFS и текущим каталогом
     * @param args    аргументы команды (не более одного пути)
     * @return true, если команда выполнена
     */
    @Override
    public boolean execute(CommandContext context, String[] args) {
        if (!context.hasVfs()) {
            System.out.println("ls: VFS is not loaded");
            return false;
        }
        VfsDirectory target = resolveTarget(context, args);
        if (target == null) {
            return false;
        }
        printEntries(target);
        return true;
    }

    private VfsDirectory resolveTarget(CommandContext context, String[] args) {
        if (args.length == 0) {
            return context.getCurrentDir();
        }
        VfsNode node = PathResolver.resolve(context.getRoot(), context.getCurrentDir(), args[0]);
        if (node == null) {
            System.out.println("ls: no such directory: " + args[0]);
            return null;
        }
        if (!(node instanceof VfsDirectory dir)) {
            System.out.println("ls: not a directory: " + args[0]);
            return null;
        }
        return dir;
    }

    private void printEntries(VfsDirectory dir) {
        if (dir.getChildren().isEmpty()) {
            return;
        }
        for (VfsNode child : dir.getChildren().values()) {
            String suffix = child instanceof VfsDirectory ? "/" : "";
            System.out.println(child.getName() + suffix);
        }
    }
}
