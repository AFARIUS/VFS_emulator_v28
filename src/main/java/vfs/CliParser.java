package vfs;

/**
 * Разбирает аргументы командной строки в {@link Config}.
 * Поддерживаемые параметры:
 *   --vfs путь к физическому расположению VFS
 *   --script путь к стартовому скрипту
 */
public class CliParser {
    /**
     * Разбирает переданные аргументы.
     * @param args исходные аргументы командной строки
     * @return конфигурацию; незаданные значения равны null
     */
    public static Config parse(String[] args) {
        String vfsPath = null;
        String scriptPath = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--vfs":
                    vfsPath = nextValue(args, i);
                    i++;
                    break;
                case "--script":
                    scriptPath = nextValue(args, i);
                    i++;
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    break;
            }
        }
        return new Config(vfsPath, scriptPath);
    }

    private static String nextValue(String[] args, int index) {
        if (index + 1 >= args.length) {
            System.out.println("Missing value for " + args[index]);
            return null;
        }
        return args[index + 1];
    }
}