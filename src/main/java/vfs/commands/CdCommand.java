package vfs.commands;

import vfs.CommandContext;
import vfs.PathResolver;
import vfs.VfsDirectory;
import vfs.VfsNode;

/**
 * Команда cd — меняет текущий каталог.
 * Поддерживает "..", ".", "/" и относительные пути.
 */
public class CdCommand implements Command {

    @Override
    public boolean execute(CommandContext context, String[] args) {
        if (!context.hasVfs()) {
            System.out.println("cd: VFS is not loaded");
            return false;
        }
        if (args.length != 1) {
            System.out.println("cd: usage: cd <path>");
            return false;
        }
        VfsNode node = PathResolver.resolve(context.getRoot(), context.getCurrentDir(), args[0]);
        if (node == null) {
            System.out.println("cd: no such directory: " + args[0]);
            return false;
        }
        if (!(node instanceof VfsDirectory dir)) {
            System.out.println("cd: not a directory: " + args[0]);
            return false;
        }
        context.setCurrentDir(dir);
        return true;
    }
}
