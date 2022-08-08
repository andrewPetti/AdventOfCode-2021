package com.chemies.aoc2021.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    private Day05 _subject;

    @BeforeEach
    void setup() {
        _subject = new Day05();
    }

    @Test
    void executePartA_expectCorrectResult() {
        final Integer expected = 5;
        assertEquals(expected, _subject.partA("day05Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final Integer expected = 12;
        assertEquals(expected, _subject.partB("day05Sample.txt"));
    }
}
