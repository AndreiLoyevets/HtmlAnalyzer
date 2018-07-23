package com.aloievets.htmlanalyzer.analyzer;

import org.jsoup.nodes.Element;

public class SimpleElementComparator implements ElementComparator {

    // in order of importance
    public static final String CLASS_ATTR = "class";
    public static final String ID_ATTR = "id";
    public static final String TITLE_ATTR = "title";

    @Override
    public int compare(Element first, Element second) {
        int score = 0;

        if (first.hasAttr(TITLE_ATTR) && second.hasAttr(TITLE_ATTR)) {
            if (!first.attr(TITLE_ATTR).equals(second.attr(TITLE_ATTR))) {
                score += 1;
            }
        }

        if (first.hasAttr(ID_ATTR) && second.hasAttr(ID_ATTR)) {
            if (!first.attr(ID_ATTR).equals(second.attr(ID_ATTR))) {
                score += 2;
            }
        }

        if (!first.html().equals(second.html())) {
            score += 4;
        }

        if (first.hasAttr(CLASS_ATTR) && second.hasAttr(CLASS_ATTR)) {
            if (!first.attr(CLASS_ATTR).equals(second.attr(CLASS_ATTR))) {
                score += 8;
            }
        }

        return score;
    }
}
