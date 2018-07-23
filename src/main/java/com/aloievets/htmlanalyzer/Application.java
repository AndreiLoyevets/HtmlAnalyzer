package com.aloievets.htmlanalyzer;

import com.aloievets.htmlanalyzer.analyzer.ElementComparator;
import com.aloievets.htmlanalyzer.analyzer.ElementData;
import com.aloievets.htmlanalyzer.analyzer.HtmlAnalyzer;
import com.aloievets.htmlanalyzer.analyzer.SimpleElementComparator;
import com.aloievets.htmlanalyzer.reader.HtmlReader;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Application {

    public static final String DEFAULT_TARGET_ID = "make-everything-ok-button";

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Expected arguments: <input_origin_file_path> <input_other_sample_file_path> <target element id (optional)>");
            return;
        }

        File originalFile = new File(args[0]);
        File diffFile = new File(args[1]);
        String targetElementId = args.length > 2 ? args[2] : DEFAULT_TARGET_ID;

        Optional<Element> targetElement = HtmlReader.findElementById(originalFile, targetElementId);

        if (!targetElement.isPresent()) {
            System.out.println("Target element id " + targetElementId + " is not present in the original file");
            return;
        }

        List<String> path = HtmlAnalyzer.getRoughElementPath(targetElement.get());
        ElementData targetElementData = new ElementData(targetElement.get(), path);

        List<ElementData> elementsToCheck = HtmlAnalyzer.findElementsForPath(HtmlReader.parseHtml(diffFile),
                targetElementData.getPath());

        ElementComparator elementComparator = new SimpleElementComparator();

        Optional<ElementData> searchedElement = elementsToCheck.stream()
                .min(Comparator.comparingInt(elementData -> elementComparator.compare(targetElement.get(), elementData.getElement())));

        if (searchedElement.isPresent()) {
            System.out.println(searchedElement.get().getPath());
            System.out.println(searchedElement.get().getElement());
        } else {
            System.out.println("No elements found in the second file matching criteria");
        }
    }
}
