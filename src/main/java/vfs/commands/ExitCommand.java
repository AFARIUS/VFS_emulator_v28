package vfs.commands;

import vfs.CommandContext;
import vfs.Shell;

/**
 * Команда exit — завершает работу эмулятора.
 */
public class ExitCommand implements Command {
    private final Shell shell;

    /**
     * Создаёт команду, привязанную к оболочке.
     * @param shell оболочка, которую нужно остановить
     */
    public ExitCommand(Shell shell) {
        this.shell = shell;
    }

    @Override
    public boolean execute(CommandContext context, String[] args) {
        shell.stop();
        System.out.println("Exit command executed successfully.");
        return true;
    }
}
