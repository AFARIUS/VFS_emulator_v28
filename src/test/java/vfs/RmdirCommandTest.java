package vfs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RmdirCommandTest {

    private Shell newShell() {
        VfsDirectory root = new VfsDirectory("/");
        VfsDirectory empty = new VfsDirectory("empty");
        VfsDirectory docs = new VfsDirectory("docs");
        docs.addChild(new VfsFile("guide.txt", new byte[]{1, 2, 3}));
        root.addChild(empty);
        root.addChild(docs);
        VirtualFileSystem vfs = new VirtualFileSystem("TestVFS", root);
        Shell shell = new Shell("TestVFS");
        shell.setVfs(vfs);
        return shell;
    }

    @Test
    void removesEmptyDirectory() {
        Shell shell = newShell();
        assertTrue(shell.executeLine("rmdir empty"));
        assertNull(shell.getVfs().getRoot().getChild("empty"));
    }

    @Test
    void failsOnNonEmptyDirectory() {
        Shell shell = newShell();
        assertFalse(shell.executeLine("rmdir docs"));
        assertNotNull(shell.getVfs().getRoot().getChild("docs"));
    }

    @Test
    void failsOnMissingDirectory() {
        Shell shell = newShell();
        assertFalse(shell.executeLine("rmdir nowhere"));
    }

    @Test
    void failsOnFile() {
        Shell shell = newShell();
        assertFalse(shell.executeLine("rmdir docs/guide.txt"));
    }

    @Test
    void failsWithoutArguments() {
        Shell shell = newShell();
        assertFalse(shell.executeLine("rmdir"));
    }

    @Test
    void failsWithoutVfs() {
        Shell shell = new Shell("NoVfs");
        assertFalse(shell.executeLine("rmdir empty"));
    }
}