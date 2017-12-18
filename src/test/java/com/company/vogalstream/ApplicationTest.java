package com.company.vogalstream;

import org.junit.Test;

import static com.company.vogalstream.Application.checkRule;
import static com.company.vogalstream.Application.firstChar;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void validRuleTest() {
        char[] lastTwo = new char[2];
        lastTwo[0] = 'a';
        lastTwo[1] = 'b';
        assertTrue(checkRule(lastTwo));
    }

    @Test
    public void checkRuleWithConsoantsOnly() {
        char[] lastTwo = new char[2];
        lastTwo[0] = 'b';
        lastTwo[1] = 'b';
        assertFalse(checkRule(lastTwo));
    }

    @Test
    public void checkRuleWithVowelOnly() {
        char[] lastTwo = new char[2];
        lastTwo[0] = 'u';
        lastTwo[1] = 'u';
        assertFalse(checkRule(lastTwo));
    }

    /**
     Found: aba
     Found: aca
     Found: afe
     Total: {aba=1, afe=1, aca=1} a=2 e=1
     */
    @Test
    public void threePatternsButJustOneValid() {
        String input = "aAbBABacafe";
        Stream stream = new StreamImpl(input);
        assertEquals('e', firstChar(stream));
    }

    /**
     Found: aba
     Found: aca
     Found: afe
     Found: ule
     Total: {aba=1, afe=1, aca=1, ule=1} a=2 e=2
     */
    @Test
    public void threePatternsButNoOneValid() {
        String input = "aAbBABacafeule";
        Stream stream = new StreamImpl(input);
        assertEquals(0, firstChar(stream));
    }

    @Test
    public void noPatternFoundVowel() {
        String input = "aaaaaaaaaaaaaaa";
        Stream stream = new StreamImpl(input);
        assertEquals(0, firstChar(stream));
    }

    @Test
    public void noPatternFoundConsoants() {
        String input = "bbbbbbb";
        Stream stream = new StreamImpl(input);
        assertEquals(0, firstChar(stream));
    }

    @Test
    public void shortInput() {
        String input = "aa";
        Stream stream = new StreamImpl(input);
        assertEquals(0, firstChar(stream));
    }

    @Test
    public void shortValidPattern() {
        String input = "aba";
        Stream stream = new StreamImpl(input);
        assertEquals('a', firstChar(stream));
    }

    /**
     Found: aba
     Found: ace
     Found: edi
     Found: ifo
     Found: opu
     Total: {aba=1, ace=1, ifo=1, opu=1, edi=1}
     */
    @Test
    public void multiplePatterns() {
        String input = "abacedifopu";
        Stream stream = new StreamImpl(input);
        assertEquals('a', firstChar(stream));
    }

    @Test
    public void multiplePatternsWithoutValid() {
        String input = "abacedifopcabacedifopcabacedifopcabacedifopucu";
        Stream stream = new StreamImpl(input);
        assertEquals(0, firstChar(stream));
    }

    @Test
    public void multiplePatternsWithOneValid() {
        String input = "abacedifopcabacedifopcabacedifopcabacedifopacu";
        Stream stream = new StreamImpl(input);
        assertEquals('u', firstChar(stream));
    }

}
