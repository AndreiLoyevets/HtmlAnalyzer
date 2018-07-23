package com.aloievets.htmlanalyzer.analyzer;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.jsoup.nodes.Element;

import java.util.List;

@Value
@AllArgsConstructor
public class ElementData {

    private final Element element;
    private final List<String> path;
}
