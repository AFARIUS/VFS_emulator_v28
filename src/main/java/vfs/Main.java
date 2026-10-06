package vfs;

/** Точка входа в приложение. */
public class Main {
    /**
     * Запускает REPL-оболочку.
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Shell shell = new Shell("VFS28");
        shell.run();
    }
}