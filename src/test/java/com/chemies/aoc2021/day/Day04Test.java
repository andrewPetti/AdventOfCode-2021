package com.chemies.aoc2021.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    private Day04 _subject;

    @BeforeEach
    void setup() {
        _subject = new Day04();
    }

    @Test
    void executePartA_expectCorrectResult() {
        final Integer expected = 4512;
        assertEquals(expected, _subject.partA("day04Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final Integer expected = 1924;
        assertEquals(expected, _subject.partB("day04Sample.txt"));
    }
}
