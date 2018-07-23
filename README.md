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