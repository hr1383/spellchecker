package com.spellchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.spellchecker.utils.SpellCheckerUtil;

public class WrongSpellingGenerator {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line = input.readLine()) != null) {
                generateWords(line, 0, 3);
            }
        } catch (IOException e) {
            System.err.println("Error while reading from Input Stream!");
        }
    }

    /**
     * generate wrong words using rules: changeCase, repeatLetter, replaceVowel
     *  
     */
    public static void generateWords(final String word, int index, int depth) {
        if (depth <= 0 || index >= word.length()) {
            return;
        }
        System.out.println(word);
        String changeCaseword = SpellCheckerUtil.changeCase(word, index);
        String removeRepeatedWord = SpellCheckerUtil.repeatLetter(word, index);
        List<String> replaceVowelWord = SpellCheckerUtil.replaceVowel(word, index);
        generateWords(changeCaseword, index + 1, depth - 1);
        if (!removeRepeatedWord.equals(word)) {
            generateWords(removeRepeatedWord, index + 1, depth - 1);
        }
        replaceVowelWord.forEach(newWord -> generateWords(newWord, index + 1, depth - 1));

    }

}
