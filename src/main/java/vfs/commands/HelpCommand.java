package vfs.commands;

import vfs.CommandContext;
import vfs.CommandRegistry;

import java.util.Map;

/**
 * Команда help — выводит список всех доступных команд с описанием.
 */
public class HelpCommand implements Command {
    private final CommandRegistry registry;

    /**
     * Создаёт команду, привязанную к реестру.
     * @param registry реестр команд
     */
    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean execute(CommandContext context, String[] args) {
        System.out.println("Available commands:");
        for (Map.Entry<String, String> entry : registry.getDescriptions().entrySet()) {
            System.out.printf("  %-10s %s%n", entry.getKey(), entry.getValue());
        }
        return true;
    }
}