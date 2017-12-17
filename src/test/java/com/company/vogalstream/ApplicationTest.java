package com.company.vogalstream;

import org.junit.Test;

import static com.company.vogalstream.Application.checkRule;
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

}