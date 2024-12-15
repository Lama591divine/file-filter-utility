package com.github.lama591divine;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Help {

    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();

        String header = "Utility for filtering data from input files by type.\n\n";
        String footer = "Example usage:\n" +
                "java -jar data-filter-utility.jar -s -a -p sample- in1.txt in2.txt";

        formatter.printHelp("java -jar data-filter-utility.jar [options] <input_files>", header, options, footer, true);
    }

}