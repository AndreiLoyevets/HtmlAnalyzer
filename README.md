# HTML Difference Analyzer

Java 8 and (optional) Maven should be installed.

## How to Run

Go to `/executable` folder. There is executable jar with dependencies. Run:
`java -jar html-analyzer.jar <original file path> <another file path> <original element id to search (optional)>`

## How to Rebuild
From root project directory run:
mvn package

Executable jar `html-analyzer.jar` will be under `/target` directory.

## Samples
Sample files are located in `/data` folder. Sample file outputs are in `/output` folder.

## Main idea

1) Search for target element by id in the original file.

2) Get path to target element in the original file.

3) Search for elements in the second file matching the target path.

4) Compare matching elements with the original element and find the most suitable (see `com.aloievets.htmlanalyzer.analyzer.SimpleElementComparator`).

5) Output path to corresponding element in the second file and the element itself.