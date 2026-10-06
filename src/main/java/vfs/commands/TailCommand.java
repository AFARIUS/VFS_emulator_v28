package vfs.commands;

import vfs.CommandContext;
import vfs.PathResolver;
import vfs.VfsFile;
import vfs.VfsNode;

import java.nio.charset.StandardCharsets;

/**
 * Команда tail — выводит последние N строк файла VFS.
 * Синтаксис: tail [-n N] <file>
 */
public class TailCommand implements Command {
    private static final int DEFAULT_LINES = 10;

    @Override
    public boolean execute(CommandContext context, String[] args) {
        if (!context.hasVfs()) {
            System.out.println("tail: VFS is not loaded");
            return false;
        }
        TailArgs parsed = parseArgs(args);
        if (parsed == null) {
            return false;
        }
        VfsFile file = resolveFile(context, parsed.filePath);
        if (file == null) {
            return false;
        }
        printTail(file, parsed.lines);
        return true;
    }

    private TailArgs parseArgs(String[] args) {
        if (args.length == 1) {
            return new TailArgs(DEFAULT_LINES, args[0]);
        }
        if (args.length == 3 && "-n".equals(args[0])) {
            try {
                int n = Integer.parseInt(args[1]);
                if (n <= 0) {
                    System.out.println("tail: number of lines must be positive");
                    return null;
                }
                return new TailArgs(n, args[2]);
            } catch (NumberFormatException e) {
                System.out.println("tail: invalid number: " + args[1]);
                return null;
            }
        }
        System.out.println("tail: usage: tail [-n N] <file>");
        return null;
    }

    private VfsFile resolveFile(CommandContext context, String path) {
        VfsNode node = PathResolver.resolve(context.getRoot(), context.getCurrentDir(), path);
        if (node == null) {
            System.out.println("tail: no such file: " + path);
            return null;
        }
        if (!(node instanceof VfsFile file)) {
            System.out.println("tail: not a file: " + path);
            return null;
        }
        return file;
    }

    private void printTail(VfsFile file, int lines) {
        String text = new String(file.getContent(), StandardCharsets.UTF_8);
        String[] all = text.split("\n", -1);
        int from = Math.max(0, all.length - lines);
        for (int i = from; i < all.length; i++) {
            System.out.println(all[i]);
        }
    }

    private record TailArgs(int lines, String filePath) {
    }
}
