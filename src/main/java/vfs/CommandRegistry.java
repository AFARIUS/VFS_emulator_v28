package vfs;

import vfs.commands.Command;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Реестр доступных команд. Хранит соответствие
 * «имя команды → реализация» и «имя команды → описание».
 */
public class CommandRegistry {
    private final Map<String, Command> commands = new LinkedHashMap<>();
    private final Map<String, String> descriptions = new LinkedHashMap<>();

    /**
     * Регистрирует команду.
     * @param name имя команды
     * @param description краткое описание
     * @param command реализация
     */
    public void register(String name, String description, Command command) {
        commands.put(name, command);
        descriptions.put(name, description);
    }

    /**
     * Возвращает команду по имени или null.
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

    /** Возвращает неизменяемую карту «имя → описание». */
    public Map<String, String> getDescriptions() {
        return Collections.unmodifiableMap(descriptions);
    }
}