package com.spellchecker.utils;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class SpellCheckerUtilTest {

    
    @Test
    public void testRemovedRepeated() {
        String str = "jjooob";
        String newStr = SpellCheckerUtil.removeRepeated(str, 0);
        assertEquals(newStr, str);
        newStr = SpellCheckerUtil.removeRepeated(str, 1);
        assertEquals(newStr, "jooob");
        newStr = SpellCheckerUtil.removeRepeated(newStr, 1);
        assertEquals(newStr, "jooob");
        newStr = SpellCheckerUtil.removeRepeated(newStr, 2);
        assertEquals(newStr, "joob");
        newStr = SpellCheckerUtil.removeRepeated(newStr, 2);
        assertEquals(newStr, "job");
    }
    
    @Test
    public void testReplaceVowel() {
        String str = "Sunday";
        List<String> newList = SpellCheckerUtil.replaceVowel(str, 0);
        assertEquals(newList.size(), 0);
        newList = SpellCheckerUtil.replaceVowel(str, 1);
        assertEquals(newList.size(), 4);
    }
    
    @Test 
    public void testChangeCase() {
        String str = "Sunday";
        String newStr = SpellCheckerUtil.changeCase(str, 0);
        assertEquals(newStr, "sunday");
        newStr = SpellCheckerUtil.changeCase(newStr, 0);
        assertEquals(newStr, str);
    }
}
