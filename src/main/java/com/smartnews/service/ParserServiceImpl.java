package com.smartnews.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ParserServiceImpl implements ParserService {
    private static final String DEFAULT_USER_AGENT = "Mozilla";

    @Override
    public String parseTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(DEFAULT_USER_AGENT)
                .get();
        return doc.title();
    }
}
