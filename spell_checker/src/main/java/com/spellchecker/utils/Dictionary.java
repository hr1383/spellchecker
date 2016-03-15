package com.spellchecker.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

    private Set<String> words;

    public Dictionary() {
        this.words = new HashSet<String>();
    }

    public Set<String> getWords() {
        return words;
    }

    public void readWords(final String filePath) throws IOException {

        InputStream is = getInputStream(filePath);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                words.add(reader.readLine());
            }
        } finally {
            if (is != null)
                is.close();
        }

    }

    private InputStream getInputStream(final String filePath) throws IOException {
        InputStream is;
        try {
            is = new FileInputStream(filePath);
        } catch (IOException io) {
            is = Dictionary.class.getResourceAsStream(filePath);
        }
        if (is == null) {
            throw new IOException("Error while opening file");
        }
        return is;
    }

    public boolean contains(String word) {
            return (words.contains(word));
    }
}
