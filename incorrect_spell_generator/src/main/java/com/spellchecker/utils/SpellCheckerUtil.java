package com.spellchecker.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpellCheckerUtil {

    static Set<Character> vowels = new HashSet<Character>(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u'}));

    public static String repeatLetter(String word, int index) {
        char c = word.charAt(index);
        StringBuffer buffer = new StringBuffer(word);
        buffer.insert(index, c);
        return buffer.toString();
    }

    public static String changeCase(String word, int index) {
        char wordArr[] = word.toCharArray();
        char currentChar = word.charAt(index);
        if (Character.isUpperCase(currentChar)) {
            wordArr[index] = Character.toLowerCase(currentChar);
        } else {
            wordArr[index] = Character.toUpperCase(currentChar);
        }
        return new String(wordArr);
    }


    public static List<String> replaceVowel(String word, int index) {
        char wordArr[] = word.toCharArray();
        if (!vowels.contains(wordArr[index])) {
            return Collections.EMPTY_LIST;
        }
        return vowels.stream().filter(vowel -> wordArr[index] != vowel).map(vowel -> {
            char newWordArr[] = new char[wordArr.length];
            System.arraycopy(wordArr, 0, newWordArr, 0, wordArr.length);
            newWordArr[index] = vowel;
            return new String(newWordArr);
        }).collect(Collectors.toList());
    }
}
