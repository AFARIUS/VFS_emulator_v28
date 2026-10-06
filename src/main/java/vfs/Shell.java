package vfs;

import vfs.commands.*;

import java.util.Scanner;

/** REPL-оболочка для эмулятора VFS. */
public class Shell {
    private final String vfsName;
    private final Scanner scanner;
    private final CommandContext context;
    private final CommandRegistry registry;
    private boolean running;

    /**
     * Создает оболочку с указанным именем VFS.
     * @param vfsName имя VFS, отображаемое в приглашении
     */
    public Shell(String vfsName) {
        this.vfsName = vfsName;
        this.scanner = new Scanner(System.in);
        this.context = new CommandContext();
        this.registry = new CommandRegistry();
        this.running = true;
        registerCommands();
    }

    private void registerCommands() {
        registry.register("ls", new LsCommand());
        registry.register("cd", new CdCommand());
        registry.register("history", new HistoryCommand());
        registry.register("uptime", new UptimeCommand());
        registry.register("tail", new TailCommand());
        registry.register("exit", new ExitCommand(this));
    }

    /**
     * Сохраняет ссылку на загруженную VFS.
     * @param vfs виртуальная файловая система или null
     */
    public void setVfs(VirtualFileSystem vfs) {
        context.setVfs(vfs);
    }

    /** Возвращает загруженную VFS или null. */
    public VirtualFileSystem getVfs() {
        return context.getVfs();
    }

    /** Возвращает строку приглашения. */
    public String getPrompt() {
        return vfsName + "> ";
    }

    /** Возвращает true, если оболочка ещё не остановлена командой exit. */
    public boolean isRunning() {
        return running;
    }

    /** Останавливает оболочку (вызывается командой exit). */
    public void stop() {
        this.running = false;
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
        context.addHistory(input);
        String[] tokens = Parser.parse(input);
        if (tokens.length == 0) {
            return false;
        }
        String commandName = tokens[0];
        Command command = registry.get(commandName);
        if (command == null) {
            System.out.println("Undefined command: " + commandName);
            return false;
        }
        String[] args = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, args, 0, args.length);
        return command.execute(context, args);
    }
}