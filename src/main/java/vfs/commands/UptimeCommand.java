package vfs.commands;

import vfs.CommandContext;

/**
 * Команда uptime — выводит время работы эмулятора с момента запуска.
 */
public class UptimeCommand implements Command {

    @Override
    public boolean execute(CommandContext context, String[] args) {
        long totalSeconds = context.getUptimeMillis() / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        System.out.printf("Uptime: %d h %d min %d sec%n", hours, minutes, seconds);
        return true;
    }
}
