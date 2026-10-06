package vfs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;

/**
 * Загружает виртуальную файловую систему из JSON-файла.
 * Двоичные данные в JSON представлены строками в base64.
 * Содержимое файла читается в память, исходный файл не модифицируется.
 */
public final class VfsLoader {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private VfsLoader() {}

    /**
     * Загружает VFS из указанного файла.
     * @param path путь к JSON-файлу
     * @return загруженная виртуальная файловая система
     * @throws IOException если файл не найден, недоступен или неверного формата
     */
    public static VirtualFileSystem load(Path path) throws IOException {
        byte[] raw = Files.readAllBytes(path);
        JsonNode root = MAPPER.readTree(raw);
        String name = textField(root, "name", "VFS");
        JsonNode rootNode = root.get("root");
        if (rootNode == null) {
            throw new IOException("Missing 'root' node");
        }
        VfsDirectory rootDir = parseDirectory(rootNode, "/");
        return new VirtualFileSystem(name, rootDir);
    }

    private static VfsDirectory parseDirectory(JsonNode node, String defaultName) throws IOException {
        String type = textField(node, "type", "dir");
        if (!"dir".equals(type)) {
            throw new IOException("Expected node of type 'dir', got '" + type + "'");
        }

        VfsDirectory dir = new VfsDirectory(defaultName);
        JsonNode children = node.get("children");

        if (children == null || !children.isObject()) {
            return dir;
        }

        Iterator<Map.Entry<String, JsonNode>> it = children.fields();

        while (it.hasNext()) {
            Map.Entry<String, JsonNode> entry = it.next();
            dir.addChild(parseNode(entry.getKey(), entry.getValue()));
        }

        return dir;
    }

    private static VfsNode parseNode(String name, JsonNode node) throws IOException {
        String type = textField(node, "type", "");
        return switch (type) {
            case "dir" -> parseDirectory(node, name);
            case "file" -> parseFile(name, node);
            default -> throw new IOException("Unknown node type: '" + type + "'");
        };
    }

    private static VfsFile parseFile(String name, JsonNode node) throws IOException {
        String content = textField(node, "content", "");
        byte[] bytes;
        try {
            bytes = Base64.getDecoder().decode(content);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid base64 content in file '" + name + "'");
        }
        return new VfsFile(name, bytes);
    }

    private static String textField(JsonNode node, String field, String fallback) {
        JsonNode value = node.get(field);
        if (value == null || !value.isTextual()) {
            return fallback;
        }
        return value.asText();
    }
}