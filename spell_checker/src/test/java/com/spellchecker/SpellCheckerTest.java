package com.spellchecker;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Optional;

import com.spellchecker.utils.SpellOpertations;

public class SpellCheckerTest {


    @Test
    public void testFindSuggestion() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("Inside", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "inside");
    }
    
    @Test
    public void testFindSuggestionCase() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("inSIDE", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "inside");
    }
    
    
    @Test
    public void testFindSuggestionRepeated() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("jjooobb", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "job");
    }
    
    
    @Test
    public void testFindSuggestionVowelChange() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("weke", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "wake");
    }
    
    @Test
    public void testFindSuggestionRepeatedVowelChange() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("CUNsperrICY", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "conspiracy");
    }
    
    @Test
    public void testFindSuggestionRepeatedVowelChange1() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("COt", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "cat");
        
    }
    
    @Test
    public void testFindSuggestionRepeatedVowelChange2() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("sheeeep", 0, SpellOpertations.NONE);
        Assert.assertTrue(optional.isPresent());
        Assert.assertEquals(optional.get(), "sheep");
    }
    
    @Test
    public void testFindSuggestionNoWordPresent() {
        SpellChecker spellChecker = new SpellChecker("/words.txt");
        Optional<String> optional = spellChecker.findSuggestion("quake", 0, SpellOpertations.NONE);
        Assert.assertFalse(optional.isPresent());
    }
}
