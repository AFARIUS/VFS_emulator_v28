package vfs;

import java.util.Scanner;

/** REPL-оболочка для эмулятора VFS. */
public class Shell {
    private final String vfsName;
    private final Scanner scanner;
    private boolean running;

    /**
     * Создает оболочку с указанным именем VFS.
     * @param vfsName имя VFS, отображаемое в приглашении
     */
    public Shell(String vfsName) {
        this.vfsName = vfsName;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    /** Возвращает строку приглашения. */
    public String getPrompt() {
        return vfsName + "> ";
    }

    /** Возвращает true, если оболочка ещё не остановлена командой exit. */
    public boolean isRunning() {
        return running;
    }

    /** Запускает цикл REPL. */
    public void run() {
        while (running) {
            System.out.print(getPrompt());
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            executeLine(input);
        }
    }

    /**
     * Выполняет одну строку ввода.
     * @param input строка команды
     * @return true, если команда распознана и выполнена, иначе false
     */
    public boolean executeLine(String input) {
        String[] tokens = Parser.parse(input);
        if (tokens.length == 0) {
            return false;
        }
        String command = tokens[0];
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);
        return execute(command, args);
    }

    private boolean execute(String command, String[] args) {
        switch (command) {
            case "exit":
                running = false;
                System.out.println("Exit command executed successfully.");
                return true;
            case "ls":
                printStub("ls", args);
                return true;
            case "cd":
                printStub("cd", args);
                return true;
            default:
                System.out.println("Undefined command: " + command);
                return false;
        }
    }

    private void printStub(String commandName, String[] args) {
        System.out.print(commandName + ": stub. Args: [");
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i < args.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}