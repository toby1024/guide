package com.example.guide.java8.lambda;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Jason
 */
@FunctionalInterface
public interface BufferedReadProcessor {
    /**
     * process file
     * @param reader
     * @return
     * @throws IOException
     */
    String process(BufferedReader reader) throws IOException;
}
