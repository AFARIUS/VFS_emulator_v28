package vfs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class VfsLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void loadsMinimalVfs() throws IOException {
        Path file = writeJson("minimal.json",
                "{\"name\":\"M\",\"root\":{\"type\":\"dir\",\"children\":{}}}");
        VirtualFileSystem vfs = VfsLoader.load(file);
        assertEquals("M", vfs.getName());
        assertEquals(1, vfs.size());
    }

    @Test
    void loadsNestedVfs() throws IOException {
        String json = "{\"name\":\"N\",\"root\":{\"type\":\"dir\",\"children\":{"
                + "\"a\":{\"type\":\"dir\",\"children\":{"
                + "\"b\":{\"type\":\"file\",\"content\":\"YQ==\"}}}}}}";
        Path file = writeJson("nested.json", json);
        VirtualFileSystem vfs = VfsLoader.load(file);
        assertEquals(3, vfs.size());
        VfsNode a = vfs.getRoot().getChild("a");
        assertInstanceOf(VfsDirectory.class, a);
        VfsNode b = ((VfsDirectory) a).getChild("b");
        assertInstanceOf(VfsFile.class, b);
        assertEquals(1, ((VfsFile) b).size());
    }

    @Test
    void rejectsMissingFile() {
        Path missing = tempDir.resolve("does-not-exist.json");
        assertThrows(IOException.class, () -> VfsLoader.load(missing));
    }

    @Test
    void rejectsInvalidJson() throws IOException {
        Path file = writeJson("bad.json", "{ this is not json");
        assertThrows(IOException.class, () -> VfsLoader.load(file));
    }

    private Path writeJson(String name, String content) throws IOException {
        Path file = tempDir.resolve(name);
        Files.writeString(file, content);
        return file;
    }
}