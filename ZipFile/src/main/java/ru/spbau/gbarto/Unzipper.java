package ru.spbau.gbarto;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Unzipper class allows extract files from zips.
 */
public class Unzipper {
    /**
     * Finds all zip archives by path
     *
     * @param path path where to search for zip archives
     * @return
     * @throws IOException
     */
    public static ArrayList<File> findArchives(String path) throws IOException {
        ArrayList<File> result = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(path))) {
            for (Path file : stream.collect(Collectors.toList())) {
                if (file.getFileName().toString().endsWith(".zip")) {
                    result.add(file.toFile());
                }
            }
        }

        return result;
    }

    /**
     * Finds all zip entries files.
     *
     * @param zip a zip file to search
     * @param regex a regular expression to math
     * @return
     */
    public static ArrayList<ZipEntry> getZipEntriesByRegex(ZipFile zip, String regex) {
        ArrayList<ZipEntry> entrys = new ArrayList<>();

        for (Enumeration<? extends ZipEntry> enumeration = zip.entries(); enumeration.hasMoreElements(); ) {
            ZipEntry file = enumeration.nextElement();

            if (file.isDirectory()) continue;
            if (!file.getName().matches(regex)) continue;

            entrys.add(file);
        }

        return entrys;
    }

    /**
     * Extracts the files from zip archive
     *
     * @param entry a zip entry
     * @param file a zip file
     * @param destination a path
     * @throws IOException
     */
    public static void unzipEntry(ZipEntry entry, File file, String destination) throws IOException {
        File outputFile = Paths.get(destination,
                                    file.getName().substring(0, file.getName().length() - 4),
                                    entry.getName()).toFile();

        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        FileSystem fileSystem = FileSystems.newFileSystem(file.toPath(), null);
        Files.copy(fileSystem.getPath(entry.getName()), outputFile.toPath());
        fileSystem.close();
    }

    /**
     * Finds and extracts all files matching regular expression from given directory
     *
     * @param source path where search
     * @param regex regular expression
     * @param destination path where extract
     * @throws IOException
     */
    public static void unzip(@NotNull String source, @NotNull  String regex, @NotNull String destination) throws IOException {
        ArrayList<File> files = findArchives(source);

        for (File file : files) {
            ArrayList<ZipEntry> entries = getZipEntriesByRegex(new ZipFile(file), regex);

            for (ZipEntry entry : entries) {
                unzipEntry(entry, file, destination);
            }
        }
    }
}