package vfs;

/**
 * Конфигурация эмулятора, заполняемая из аргументов командной строки.
 * Поля могут быть null, если соответствующий параметр не был задан.
 */
public class Config {
    private final String vfsPath;
    private final String scriptPath;

    /**
     * Создаёт конфигурацию.
     * @param vfsPath путь к физическому расположению VFS, либо null
     * @param scriptPath путь к стартовому скрипту, либо null
     */
    public Config(String vfsPath, String scriptPath) {
        this.vfsPath = vfsPath;
        this.scriptPath = scriptPath;
    }

    public String getVfsPath() {
        return vfsPath;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public boolean hasVfsPath() {
        return vfsPath != null;
    }

    public boolean hasScriptPath() {
        return scriptPath != null;
    }
}