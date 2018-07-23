package com.aloievets.htmlanalyzer.analyzer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

public class HtmlAnalyzer {

    /**
     * Extracts the path to element starting from the root of the document.
     *
     * @param element element to analyze
     * @return "rough" path of the element in the document; "rough" because if the document has a couple of duplicate
     * tags on the same level, the resulting path will not show that (e.g., returns div instead of div[0] or div[1])
     */
    public static List<String> getRoughElementPath(Element element) {
        notNull(element);

        LinkedList<String> path = new LinkedList<>();

        Element currentElement = element;

        do {
            path.addFirst(currentElement.tagName());
            currentElement = currentElement.parent();
        }
        while (currentElement.parent() != null);

        return path;
    }

    /**
     * For the given rough path, method searches through the document and finds all elements with their strict paths
     * that correspond to target rough path.
     *
     * @param document document to process
     * @param targetRoughPath "rough" path of the element to search, see {@link HtmlAnalyzer#getRoughElementPath(Element)}
     * @return list of elements and their strict paths which correspond to the target rough path. For example,
     * [html, body, div[1], div[0], a[0]] can be one of strict paths that correspond to [html, body, div, div, a] rough path.
     */
    public static List<ElementData> findElementsForPath(Document document, List<String> targetRoughPath) {
        Element rootElement = document.getElementsByTag(targetRoughPath.get(0)).first();

        if (rootElement == null) {
            return Collections.emptyList();
        }

        List<String> currentPath = new LinkedList<>();
        currentPath.add(rootElement.tagName());

        return findChildElementsForSubPath(rootElement, currentPath, 0, targetRoughPath);
    }

    /**
     * One step of recursion. Checks child elements that satisfy rough path and merges results.
     *
     * @param currentElement current element for which child nodes are analyzed; current element already satisfies the path
     * @param currentPath strict path that is already made to current element
     * @param currentPathIndex index in target rough path; all elements with indices 0..current path index already satisfy search
     * @param targetRoughPath a complete original rough path to search
     * @return list of all elements satisfying rough path
     */
    private static List<ElementData> findChildElementsForSubPath(Element currentElement, List<String> currentPath,
                                                                 int currentPathIndex, List<String> targetRoughPath) {

        if (currentPathIndex == targetRoughPath.size() - 1) {
            return Collections.singletonList(new ElementData(currentElement, currentPath));
        }

        List<ElementData> matchingElements = new LinkedList<>();

        String lastTagName = "";
        int duplicateTagIndex = 0;

        for (Element childElement : currentElement.children()) {
            if (lastTagName.equals(childElement.tagName())) {
                duplicateTagIndex++;
            } else {
                duplicateTagIndex = 0;
            }
            lastTagName = childElement.tagName();

            if (childElement.tagName().equals(targetRoughPath.get(currentPathIndex + 1))) {
                List<String> childPath = new LinkedList<>(currentPath);
                childPath.add(childElement.tagName() + "[" + duplicateTagIndex + "]");
                matchingElements.addAll(findChildElementsForSubPath(childElement, childPath,
                        currentPathIndex + 1, targetRoughPath));
            }
        }

        return matchingElements;
    }
}
