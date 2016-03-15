package com.spellchecker.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpellCheckerUtil {

    static Set<Character> vowels = new HashSet<Character>(Arrays.asList(new Character[] {'a', 'e', 'i', 'o', 'u'}));

    /**
     * remove repeated words at index i such as joob => job when index = 2
     */
    public static String removeRepeated(final String word, final int index) {
        if (index == 0) {
            return word;
        }
        if (word.charAt(index - 1) == word.charAt(index)) {
            StringBuffer sb = new StringBuffer(word);
            sb.deleteCharAt(index);
            return sb.toString();
        }
        return word;
    }

    /**
     * change case of a letter at give index Abc => abc at index = 0
     */
    public static String changeCase(final String word, final int index) {
        char wordArr[] = word.toCharArray();
        char currentChar = word.charAt(index);
        if (Character.isUpperCase(currentChar)) {
            wordArr[index] = Character.toLowerCase(currentChar);
        } else {
            wordArr[index] = Character.toUpperCase(currentChar);
        }
        return new String(wordArr);
    }


    /**
     * replace vowel at a given index. job => [jab, jeb, jib, jub] at index = 1
     */
    public static List<String> replaceVowel(final String word, final int index) {
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
