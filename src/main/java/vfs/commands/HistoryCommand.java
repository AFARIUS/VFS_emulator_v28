package vfs.commands;

import vfs.CommandContext;

import java.util.List;

/**
 * Команда history — выводит историю введённых команд с номерами.
 */
public class HistoryCommand implements Command {

    @Override
    public boolean execute(CommandContext context, String[] args) {
        List<String> history = context.getHistory();
        for (int i = 0; i < history.size(); i++) {
            System.out.printf("%4d  %s%n", i + 1, history.get(i));
        }
        return true;
    }
}
