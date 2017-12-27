package ru.spbau.gbarto;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

public class UnzipperTest {
    /**
     * Checks the correctness of unpacking of three files.
     *
     * @throws IOException
     */
    @Test
    public void unzip() throws IOException {
        File direction;
        direction = Paths.get("src", "test", "resources", "test").toFile();
        direction.mkdir();

        Unzipper.unzip(Paths.get("src", "test", "resources").toString(),
                ".*\\.txt",
                direction.getPath());

        ArrayList<File> arr = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(Paths.get(direction.getPath()))) {
            for (Path file : stream.collect(Collectors.toList())) {
                if (!file.toFile().isDirectory()) {
                    arr.add(file.toFile());
                }
            }
        }

        assertEquals(3, arr.size());
        assertEquals("src\\test\\resources\\test\\zp\\file6.txt", arr.get(0).toString());
        assertEquals("src\\test\\resources\\test\\zp\\folder\\file4.txt", arr.get(1).toString());
        assertEquals("src\\test\\resources\\test\\zp\\folder\\file5.txt", arr.get(2).toString());

        File f = Paths.get("src", "test", "resources", "test").toFile();
        Files.walkFileTree(f.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException
            {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                    throws IOException
            {
                if (e == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    throw e;
                }
            }
        });
    }

}