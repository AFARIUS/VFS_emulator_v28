package vfs;

/** Точка входа в приложение. */
public class Main {
    /**
     * Запускает эмулятор.
     * @param args аргументы командной строки: [--vfs path] [--script path]
     */
    public static void main(String[] args) {
        Config config = CliParser.parse(args);
        printConfig(config);

        Shell shell = new Shell("VFS28");

        if (config.hasScriptPath()) {
            ScriptRunner runner = new ScriptRunner(shell);
            runner.run(config.getScriptPath());
        }

        if (shell.isRunning()) {
            shell.run();
        }
    }

    private static void printConfig(Config config) {
        System.out.println("=== Configuration ===");
        System.out.println("VFS path:    " + (config.hasVfsPath() ? config.getVfsPath() : "<not set>"));
        System.out.println("Script path: " + (config.hasScriptPath() ? config.getScriptPath() : "<not set>"));
        System.out.println("=====================");
    }
}