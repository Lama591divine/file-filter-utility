package com.github.lama591divine;

import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {

    private final String outputDir;
    private final String prefix;
    private final boolean append;
    private final boolean shortStats;
    private final boolean fullStats;
    private final java.util.List<String> inputFiles;

    public Configuration(String[] args) throws ParseException, IllegalArgumentException {
        Options options = buildOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("h")) {
            new Help().printHelp(options);
            System.exit(0);
        }

        this.outputDir = cmd.getOptionValue("o", ".");
        this.prefix = cmd.getOptionValue("p", "");
        this.append = cmd.hasOption("a");
        this.shortStats = cmd.hasOption("s");
        this.fullStats = cmd.hasOption("f");

        if (shortStats && fullStats) {
            throw new IllegalArgumentException("Options -s and -f cannot be used simultaneously.");
        }

        this.inputFiles = cmd.getArgList();
        if (inputFiles.isEmpty()) {
            System.err.println("No input files specified.");
            new Help().printHelp(options);
            System.exit(1);
        }

        Path outputPath = Paths.get(outputDir);
        if (!Files.exists(outputPath)) {
            try {
                Files.createDirectories(outputPath);
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to create the output directory: " + outputDir);
            }
        } else if (!Files.isDirectory(outputPath)) {
            throw new IllegalArgumentException("The specified output path is not a directory: " + outputDir);
        }
    }

    private Options buildOptions() {
        Options options = new Options();
        options.addOption("o", "output", true, "Output directory path");
        options.addOption("p", "prefix", true, "Prefix for output filenames");
        options.addOption("a", "append", false, "Append to existing output files");
        options.addOption("s", "short", false, "Short statistics");
        options.addOption("f", "full", false, "Full statistics");
        options.addOption("h", "help", false, "Show help");
        return options;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppend() {
        return append;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public java.util.List<String> getInputFiles() {
        return inputFiles;
    }
}