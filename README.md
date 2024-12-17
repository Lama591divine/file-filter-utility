# File Filter Utility

## Overview

**File Filter Utility** is a Java-based command-line tool designed to filter and categorize data from input files into separate output files based on data types. It supports integers, floating-point numbers, and strings, providing both short and detailed statistics about the processed data.

## Features

- **Data Classification:** Automatically classifies each line of input as an integer, floating-point number, or string.
- **Customizable Output:**
    - Specify output directory and filename prefixes.
    - Option to append to existing output files.
- **Statistics Generation:**
    - **Short Statistics:** Displays count of each data type.
    - **Full Statistics:** Provides detailed metrics such as minimum, maximum, sum, and average for integers and floats, and length metrics for strings.
- **Flexible Input Handling:** Accepts multiple input files for processing.
- **Help Support:** Provides usage instructions through a help option.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Options](#options)
- [Examples](#examples)
- [Dependencies](#dependencies)

## Prerequisites

- **Java:** JDK 23
- **Maven:** 3.9.9 or later

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Lama591divine/file-filter-utility.git

2. **Build the Project Using Maven:**
- Ensure you have Maven installed. To build the project and generate a jar with dependencies:

    ```bash
   mvn clean package

- The executable jar file data-filter-utility-1.0.0-jar-with-dependencies.jar will be located in the target directory.

## Usage

- Run the utility using the java -jar command followed by the desired options and input files.

    ```bash
    java -jar target/data-filter-utility-1.0.0-jar-with-dependencies.jar [options] <input_files>
  
## Options

- **-o, --output <directory>**
  > Description: Specify the output directory path.
  > Default: Current directory (.)

- **-p, --prefix <prefix>**
  > Description: Prefix for output filenames.  
  > Default: No prefix

- **-a, --append**
  > Description: Append to existing output files instead of overwriting.  

- **-s, --short**
  > Description: Display short statistics.  

- **-f, --full**
  > Description: Display full statistics.

- **-h, --help**
  > Description: Show help message.
    *Note: Options -s (short statistics) and -f (full statistics) cannot be used simultaneously.*


## Examples

1. **Basic Usage**

   Process input1.txt and input2.txt, outputting results to the current directory.
    ```bash
   java -jar target/data-filter-utility-1.0.0-jar-with-dependencies.jar input1 input2

2. **Specify Output Directory and Prefix:**

   This will create files like sample-integers.txt, sample-floats.txt, and sample-strings.txt in the specified output directory.
    ```bash
   java -jar target/data-filter-utility-1.0.0-jar-with-dependencies.jar -o target -p sample- input1 input2

3. **Append to Existing Files and Show Short Statistics:**

    ```bash
   java -jar target/data-filter-utility-1.0.0-jar-with-dependencies.jar -a -s input1

4. **Show Full Statistics:**

    ```bash
   java -jar target/data-filter-utility-1.0.0-jar-with-dependencies.jar -f input1

## Dependencies
The project utilizes the following external libraries:

- **Apache Commons CLI**  
  Version: 1.5.0   
  Description: Provides a simple API for parsing command-line options.  

  ```xml
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-cli</artifactId>
        <version>1.5.0</version>
    </dependency>