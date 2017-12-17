package com.company.vogalstream;

public class StreamImpl implements Stream {

    private final String input;
    private int currentPosition = 0;

    public StreamImpl(String input) {
        this.input = input;
    }

    public char getNext() {
        char c = input.charAt(currentPosition);
        currentPosition++;
        return c;
    }

    public boolean hasNext() {
        return currentPosition < input.length();
    }
}
