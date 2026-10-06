package vfs;

import java.io.IOException;
import java.nio.file.Path;

/** Точка входа в приложение. */
public class Main {
    /**
     * Запускает эмулятор.
     * @param args аргументы командной строки: [--vfs path] [--script path]
     */
    public static void main(String[] args) {
        Config config = CliParser.parse(args);
        printConfig(config);

        VirtualFileSystem vfs = tryLoadVfs(config);
        String shellName = vfs != null ? vfs.getName() : "VFS28";
        Shell shell = new Shell(shellName);
        shell.setVfs(vfs);

        if (config.hasScriptPath()) {
            ScriptRunner runner = new ScriptRunner(shell);
            runner.run(config.getScriptPath());
        }

        if (shell.isRunning()) {
            shell.run();
        }
    }

    private static VirtualFileSystem tryLoadVfs(Config config) {
        if (!config.hasVfsPath()) return null;

        try {
            VirtualFileSystem vfs = VfsLoader.load(Path.of(config.getVfsPath()));
            System.out.println("VFS loaded: " + vfs.getName() + " (" + vfs.size() + " nodes)");
            return vfs;
        } catch (IOException e) {
            System.out.println("Failed to load VFS: " + e.getMessage());
            return null;
        }
    }

    private static void printConfig(Config config) {
        System.out.println("=== Configuration ===");
        System.out.println("VFS path:    " + (config.hasVfsPath() ? config.getVfsPath() : "<not set>"));
        System.out.println("Script path: " + (config.hasScriptPath() ? config.getScriptPath() : "<not set>"));
        System.out.println("=====================");
    }
}