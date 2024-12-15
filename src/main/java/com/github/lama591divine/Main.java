package com.github.lama591divine;

import org.apache.commons.cli.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        try {
            Configuration config = new Configuration(args);
            Path outputPath = Paths.get(config.getOutputDir());

            DataClassifier classifier = new DataClassifier();
            Statistics stats = new Statistics();

            try (FileWriterManager writerManager = new FileWriterManager(outputPath, config.getPrefix(), config.isAppend())) {
                processInputFiles(config, classifier, writerManager, stats);
                stats.printStatistics(config.isShortStats(), config.isFullStats(),
                        writerManager.hasIntegers(), writerManager.hasFloats(), writerManager.hasStrings());
            } catch (IOException e) {
                System.err.println("Error writing output files: " + e.getMessage());
            }

        } catch (ParseException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void processInputFiles(Configuration config, DataClassifier classifier, FileWriterManager writerManager, Statistics stats) {
        for (String fileName : config.getInputFiles()) {
            Path filePath = Paths.get(fileName);

            if (!Files.exists(filePath)) {
                System.err.println("Input file does not exist: " + fileName);
                continue;
            }
            if (!Files.isRegularFile(filePath)) {
                System.err.println("The input path is not a file: " + fileName);
                continue;
            }

            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue;
                    }

                    switch (classifier.classify(line)) {
                        case INTEGER:
                            stats.addInteger(classifier.parseInteger(line));
                            writerManager.writeInteger(line);
                            break;
                        case FLOAT:
                            double value = classifier.parseFloat(line);
                            stats.addFloat(value);
                            writerManager.writeFloat(Double.toString(value));
                            break;
                        case STRING:
                            stats.addString(line);
                            writerManager.writeString(line);
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file " + fileName + ": " + e.getMessage());
            }
        }
    }
}