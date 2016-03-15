package com.spellchecker;

import com.spellchecker.utils.Dictionary;
import com.spellchecker.utils.SpellCheckerUtil;
import com.spellchecker.utils.SpellOpertations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class SpellChecker {

    private Dictionary dictionary;

    public static void main(String[] args) {
        new SpellChecker().suggestWords();

    }

    public SpellChecker() {
        this.dictionary = new Dictionary();
        init("/usr/share/dict/words");
    }

    public SpellChecker(final String filePath) {
        this.dictionary = new Dictionary();
        init(filePath);
    }

    private void init(final String filePath) {
        try {
            dictionary.readWords(filePath);
        } catch (IOException e) {
            System.err.println("Error while reading the file");
        }
    }

    private void suggestWords() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print(">");
            String word = "";
            while ((word = reader.readLine()) != null) {
                if (dictionary.contains(word)) {
                    System.out.println("Correct Word");
                } else {
                    Optional<String> suggestedWord = findSuggestion(word, 0, SpellOpertations.NONE);
                    if (suggestedWord.isPresent()) {
                        System.out.println(suggestedWord.get());
                    } else {
                        System.out.println("NO SUGGESTION");
                    }
                }
                System.out.print(">");
            }
        } catch (IOException e) {
            System.err.println("Error while reading from console");
        }
    }

    /**
     * solving the problem with backtracking concept, where each word is changed as per rules and moved forward in the
     * index.
     */
    public Optional<String> findSuggestion(final String word, int currentIndex, SpellOpertations prevOperation) {

        if (currentIndex > word.length()) {
            return Optional.empty();
        }

        if (dictionary.contains(word)) {
            return Optional.of(word);
        }

        for (int index = currentIndex; index < word.length(); index++) {

            // change case
            if (prevOperation != SpellOpertations.CHANGE_CASE) {
                String newWord = SpellCheckerUtil.changeCase(word, index);
                Optional<String> optional = findSuggestion(newWord, index, SpellOpertations.CHANGE_CASE);
                if (optional.isPresent()) {
                    return optional;
                }
            }

            // remove repeated
            String newWord = SpellCheckerUtil.removeRepeated(word, index);
            if (newWord.length() != word.length()) {
                Optional<String> optional = findSuggestion(newWord, index, SpellOpertations.REPEATED_LETTERS);
                if (optional.isPresent()) {
                    return optional;
                }
            }

            // replace vowels
            if (prevOperation != SpellOpertations.CHANGE_VOWELS) {
                List<String> newWords = SpellCheckerUtil.replaceVowel(word, index);
                for (String newReplacedWord : newWords) {
                    Optional<String> optional =
                        findSuggestion(newReplacedWord, index, SpellOpertations.CHANGE_VOWELS);
                    if (optional.isPresent()) {
                        return optional;
                    }
                }
            }
            // no-op
            if (prevOperation != SpellOpertations.NONE) {
                Optional<String> optional = findSuggestion(word, index + 1, SpellOpertations.NONE);
                if (optional.isPresent()) {
                    return optional;
                }
            }
        }
        return Optional.empty();
    }


}
