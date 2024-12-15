package com.github.lama591divine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;

public class FileWriterManager implements AutoCloseable {
    private final Path integersPath;
    private final Path floatsPath;
    private final Path stringsPath;

    private final BufferedWriter integersWriter;
    private final BufferedWriter floatsWriter;
    private final BufferedWriter stringsWriter;

    private boolean hasIntegers = false;
    private boolean hasFloats = false;
    private boolean hasStrings = false;

    public FileWriterManager(Path outputPath, String prefix, boolean append) throws IOException {
        String integersFileName = prefix + "integers.txt";
        String floatsFileName = prefix + "floats.txt";
        String stringsFileName = prefix + "strings.txt";

        this.integersPath = outputPath.resolve(integersFileName);
        this.floatsPath = outputPath.resolve(floatsFileName);
        this.stringsPath = outputPath.resolve(stringsFileName);

        OpenOption[] options = append ?
                new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND} :
                new OpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};

        integersWriter = Files.newBufferedWriter(integersPath, options);
        floatsWriter = Files.newBufferedWriter(floatsPath, options);
        stringsWriter = Files.newBufferedWriter(stringsPath, options);
    }

    public void writeInteger(String value) throws IOException {
        integersWriter.write(value);
        integersWriter.newLine();
        hasIntegers = true;
    }

    public void writeFloat(String value) throws IOException {
        floatsWriter.write(value);
        floatsWriter.newLine();
        hasFloats = true;
    }

    public void writeString(String value) throws IOException {
        stringsWriter.write(value);
        stringsWriter.newLine();
        hasStrings = true;
    }

    @Override
    public void close() throws IOException {
        integersWriter.close();
        floatsWriter.close();
        stringsWriter.close();

        if (!hasIntegers) {
            Files.deleteIfExists(integersPath);
        }
        if (!hasFloats) {
            Files.deleteIfExists(floatsPath);
        }
        if (!hasStrings) {
            Files.deleteIfExists(stringsPath);
        }
    }

    public boolean hasIntegers() {
        return hasIntegers;
    }

    public boolean hasFloats() {
        return hasFloats;
    }

    public boolean hasStrings() {
        return hasStrings;
    }
}