package vfs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Выполняет стартовый скрипт построчно.
 * Ошибочные строки пропускаются. Ввод и вывод отображаются в консоли,
 * имитируя интерактивный диалог.
 */
public class ScriptRunner {
    private final Shell shell;

    /**
     * Создаёт исполнитель, привязанный к указанной оболочке.
     * @param shell оболочка, используемая для выполнения команд
     */
    public ScriptRunner(Shell shell) {
        this.shell = shell;
    }

    /**
     * Читает и выполняет скрипт по указанному пути.
     * Пустые строки игнорируются. Ошибочные строки помечаются и пропускаются.
     *
     * @param scriptPath путь к файлу скрипта
     */
    public void run(String scriptPath) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(scriptPath));
        } catch (IOException e) {
            System.out.println("Cannot read script: " + e.getMessage());
            return;
        }
        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            System.out.println(shell.getPrompt() + trimmed);
            boolean ok = shell.executeLine(trimmed);
            if (!ok) {
                System.out.println("Skipping failed command: " + trimmed);
            }
            if (!shell.isRunning()) {
                break;
            }
        }
    }
}