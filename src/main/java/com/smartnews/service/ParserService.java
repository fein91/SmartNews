package com.smartnews.service;

import java.io.IOException;

public interface ParserService {
    String parseTitle(String url) throws IOException;
}
