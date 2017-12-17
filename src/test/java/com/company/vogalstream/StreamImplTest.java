package com.company.vogalstream;

import org.junit.Test;

import static org.junit.Assert.*;

public class StreamImplTest {

    @Test
    public void shouldGetNextIfHasNext() {
        StreamImpl stream = new StreamImpl("123");
        assertTrue(stream.hasNext());

        assertEquals((char)'1', stream.getNext());
        assertTrue(stream.hasNext());

        assertEquals((char)'2', stream.getNext());
        assertTrue(stream.hasNext());

        assertEquals((char)'3', stream.getNext());
        assertFalse(stream.hasNext());

    }
}