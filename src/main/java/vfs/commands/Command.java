package vfs.commands;

import vfs.CommandContext;

/**
 * Команда эмулятора. Каждая команда получает контекст и аргументы,
 * возвращает true, если выполнение успешно.
 */
public interface Command {
    /**
     * Выполняет команду.
     * @param context контекст выполнения (VFS, текущий каталог, история)
     * @param args    аргументы команды
     * @return true, если команда выполнена без ошибок
     */
    boolean execute(CommandContext context, String[] args);
}