package vfs;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShellCommandsTest {

    private Shell newShellWithVfs() {
        VfsDirectory root = new VfsDirectory("/");
        VfsDirectory docs = new VfsDirectory("docs");
        VfsFile file = new VfsFile("readme.txt",
                "line1\nline2\nline3\nline4\n".getBytes(StandardCharsets.UTF_8));
        docs.addChild(file);
        root.addChild(docs);
        VirtualFileSystem vfs = new VirtualFileSystem("TestVFS", root);
        Shell shell = new Shell("TestVFS");
        shell.setVfs(vfs);
        return shell;
    }

    @Test
    void lsAtRootListsDocs() {
        Shell shell = newShellWithVfs();
        assertTrue(shell.executeLine("ls"));
    }

    @Test
    void cdIntoDocsWorks() {
        Shell shell = newShellWithVfs();
        assertTrue(shell.executeLine("cd docs"));
        assertTrue(shell.executeLine("ls"));
    }

    @Test
    void cdBackToParentWorks() {
        Shell shell = newShellWithVfs();
        shell.executeLine("cd docs");
        assertTrue(shell.executeLine("cd .."));
    }

    @Test
    void cdToUnknownFails() {
        Shell shell = newShellWithVfs();
        assertFalse(shell.executeLine("cd nowhere"));
    }

    @Test
    void uptimeWorks() {
        Shell shell = newShellWithVfs();
        assertTrue(shell.executeLine("uptime"));
    }

    @Test
    void tailPrintsLastLines() {
        Shell shell = newShellWithVfs();
        shell.executeLine("cd docs");
        assertTrue(shell.executeLine("tail -n 2 readme.txt"));
    }

    @Test
    void tailOnDirectoryFails() {
        Shell shell = newShellWithVfs();
        assertFalse(shell.executeLine("tail docs"));
    }
}