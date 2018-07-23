package com.aloievets.htmlanalyzer.analyzer;

import org.jsoup.nodes.Element;

public interface ElementComparator {

    /**
     * Compares attributes of html elements. Returns 0 if they are equal; > 0 means different elements.
     * The more difference is, the larger value is returned.
     *
     * @param first first element to compare
     * @param second second element to compare
     * @return 0 if element attributes are equal, > 0 - different
     */
    int compare(Element first, Element second);
}
