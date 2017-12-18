package com.company.vogalstream;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Application {

    public static final String[] VOWEL = new String[] { "a", "e" , "i", "o", "u" };
    public static final String[] CONSONANTS = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};

    public static final Set<String> VOWEL_SET = new HashSet<>(asList(VOWEL));
    public static final Set<String> CONSONANTS_SET = new HashSet<>(asList(CONSONANTS));

    public static void main(String[] args) {

        System.out.println("Enter the text:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        System.out.println("Input: : " + input);

        if (input.length() < 3) {
            System.out.println("Your input must be equals or greater than 3");
            System.exit(1);
        }

        Stream stream = new StreamImpl(input);

        char firstChar = firstChar(stream);

        if (firstChar!= 0) {
            System.out.println("First vowel is: " + firstChar);
        } else {
            System.out.println("Pattern not found");
        }


    }

    public static char firstChar(Stream stream) {

        char[] lastTwo = new char[2];

        Map<String, Integer> patternFound = new HashMap<>();

        for (int i = 0; stream.hasNext(); i++) {
            char currentChar = Character.toLowerCase(stream.getNext());

            // start from 3
            if (i < 2) {
                lastTwo[i] = currentChar;
                continue;
            }

            if (checkRule(lastTwo) && isVowel(currentChar)) {
                String pattern = String.valueOf(lastTwo) + currentChar;
                System.out.println("Found: " + pattern);
                patternFound.putIfAbsent(pattern, 0);
                patternFound.compute(pattern, (mapChar, val) -> val + 1);
            }

            move(lastTwo, currentChar);

        }

        System.out.println("Total: " + patternFound);

        Optional<Character> character = patternFound.entrySet().stream()
                .map(pattern -> new AbstractMap.SimpleEntry<>(pattern.getKey().charAt(2), pattern.getValue())) // transform from "pattern" to one "vowel"
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue))) // group by vowel and total found
                .entrySet().stream()
                .filter(vowel -> vowel.getValue() == 1) // This vowel can't repeat
                .map(Map.Entry::getKey) // Get the one!
                .findAny();

        return character.orElseGet(() -> (char)0);
    }

    public static void move(char[] lastTwo, char newValue) {
        lastTwo[0] = lastTwo[1];
        lastTwo[1] = newValue;
    }

    public static boolean checkRule(char[] lastTwo) {
        char char0 = lastTwo[0];
        char char1 = lastTwo[1];

        return isVowel(char0) && isConsoant(char1);
    }

    private static boolean isConsoant(char c) {
        return CONSONANTS_SET.contains(Character.toString(c));
    }

    private static boolean isVowel(char c) {
        return VOWEL_SET.contains(Character.toString(c));
    }

}
