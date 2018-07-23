package com.aloievets.htmlanalyzer.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class HtmlReader {

    public static final String ENCODING = "UTF-8";

    public static Optional<Element> findElementById(File htmlFile, String targetId) throws IOException {
        Document document = parseHtml(htmlFile);

        return Optional.ofNullable(document.getElementById(targetId));
    }

    public static Document parseHtml(File htmlFile) throws IOException {
        return Jsoup.parse(htmlFile, ENCODING);
    }
}
