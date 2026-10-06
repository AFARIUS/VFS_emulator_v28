package vfs;

/**
 * Парсер командной строки. Разделяет ввод по пробелам.
 */
public class Parser {
    /**
     * Разбирает строку на токены по пробелам.
     *
     * @param input входная строка
     * @return массив токенов
     */
    public static String[] parse(String input) {
        return input.split("\\s+");
    }
}