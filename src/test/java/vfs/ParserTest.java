package vfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParserTest {
    @Test
    void testParseSimple() {
        String[] result = Parser.parse("ls -l /home");
        assertArrayEquals(new String[]{"ls", "-l", "/home"}, result);
    }

    @Test
    void testParseWithMultipleSpaces() {
        String[] result = Parser.parse("cd   folder");
        assertArrayEquals(new String[]{"cd", "folder"}, result);
    }
}