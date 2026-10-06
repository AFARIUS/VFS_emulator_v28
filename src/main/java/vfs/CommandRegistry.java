package vfs;

import vfs.commands.Command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Реестр доступных команд. Хранит соответствие «имя команды → реализация».
 */
public class CommandRegistry {
    private final Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Регистрирует команду под указанным именем.
     *
     * @param name    имя команды
     * @param command реализация
     */
    public void register(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Возвращает команду по имени или null.
     *
     * @param name имя команды
     * @return реализация команды или null
     */
    public Command get(String name) {
        return commands.get(name);
    }

    /** Возвращает true, если команда с таким именем зарегистрирована. */
    public boolean contains(String name) {
        return commands.containsKey(name);
    }
}