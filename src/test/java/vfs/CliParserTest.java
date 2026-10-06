package vfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CliParserTest {

    @Test
    void parsesBothParameters() {
        Config c = CliParser.parse(new String[]{"--vfs", "/vfs", "--script", "/s.txt"});
        assertEquals("/vfs", c.getVfsPath());
        assertEquals("/s.txt", c.getScriptPath());
        assertTrue(c.hasVfsPath());
        assertTrue(c.hasScriptPath());
    }

    @Test
    void parsesEmptyArguments() {
        Config c = CliParser.parse(new String[]{});
        assertNull(c.getVfsPath());
        assertNull(c.getScriptPath());
        assertFalse(c.hasVfsPath());
        assertFalse(c.hasScriptPath());
    }

    @Test
    void parsesOnlyVfs() {
        Config c = CliParser.parse(new String[]{"--vfs", "/only-vfs"});
        assertEquals("/only-vfs", c.getVfsPath());
        assertNull(c.getScriptPath());
    }
}