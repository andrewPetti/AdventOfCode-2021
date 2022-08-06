package com.chemies.aoc2021.day;

import com.chemies.aoc2021.util.FileHelper;
import com.chemies.aoc2021.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day03 implements Day {
    private final TextFormatter _textFormatter = new TextFormatter();
    private final FileHelper _fileHelper = new FileHelper();
    private int _lengthOfBits;

    @Override
    public void executePartA() {
        final int result = partA("day03Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partA(final String filename) {
        final ImmutableList<String> lines = _fileHelper.fileToStringList(filename);

        _lengthOfBits = lines.get(0).length();
        final ArrayList<Integer> bitCounts = new ArrayList<Integer>(Collections.nCopies(_lengthOfBits, 0));

        //lines.forEach(this::determineBitsCountWithBitOne);
        final BitCounter bitCounter = new BitCounter(bitCounts);
        lines.forEach(bitCounter::determineBitsCountWithBitOne);

        final String gamma = buildGamma(bitCounts, lines.size());
        final String epsilon = buildEpsilon(bitCounts, lines.size());

        final int gammaDecimal = Integer.parseInt(gamma, 2);
        final int epsilonDecimal = Integer.parseInt(epsilon, 2);

        return gammaDecimal * epsilonDecimal;

    }

    private String buildEpsilon(final ArrayList<Integer> bitCounts, final int size) {
        final StringBuilder builder = new StringBuilder();
        bitCounts.forEach(x -> {
            if (x < size / 2) {
                builder.append("1");
            } else {
                builder.append("0");
            }
        });

        return builder.toString();
    }

    private String buildGamma(final List<Integer> bitCounts, final int size) {
        final StringBuilder builder = new StringBuilder();
        bitCounts.forEach(x -> {
            if (x > size / 2) {
                builder.append("1");
            } else {
                builder.append("0");
            }
        });

        return builder.toString();
    }


    @Override
    public void executePartB() {
        final int result = partB("day03Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partB(final String filename) {
        final ImmutableList<String> lines = _fileHelper.fileToStringList(filename);

        _lengthOfBits = lines.get(0).length();

        final List<String> oxygenGeneratorRating = findOxygenGeneratorRating(lines, _lengthOfBits, 0);
        final List<String> co2ScubberRating = findCO2ScrubberRating(lines, 0);
//        final ArrayList<Integer> bitCounts = new ArrayList<Integer>(Collections.nCopies(_size, 0));
//        final BitCounter bitCounter = new BitCounter(bitCounts);
//        lines.forEach(bitCounter::determineBitsCountWithBitOne);

        //lines.stream().filter(x -> x.charAt(0) == getMaxCount(_bitCounts.get(0)));
        //  _bitCounts.stream().filter()
        return Integer.parseInt(oxygenGeneratorRating.get(0), 2) * Integer.parseInt(co2ScubberRating.get(0), 2);
    }

    private List<String> findCO2ScrubberRating(final ImmutableList<String> lines, final int pos) {
        final ArrayList<Integer> bitCounts = new ArrayList<>(Collections.nCopies(_lengthOfBits, 0));
        final BitCounter bitCounter = new BitCounter(bitCounts);
        lines.forEach(bitCounter::determineBitsCountWithBitOne);

        final char leastCommonBit = getLeastCommonBit(bitCounts, lines.size(), pos);
        final ImmutableList<String> filteredLines = lines.stream().filter(x -> x.charAt(pos) == leastCommonBit).collect(ImmutableList.toImmutableList());

        if (filteredLines.size() == 1) {
            return filteredLines;
        }
        return findCO2ScrubberRating(filteredLines, pos + 1);
    }

    private char getLeastCommonBit(final ArrayList<Integer> bitCounts, final int size, final int pos) {
        if (bitCounts.get(pos) >= size / 2.0) {
            //|| bitCounts.get(pos) == size / 2.0) {
            return '0';
        } else return '1';
    }

    private List<String> findOxygenGeneratorRating(final ImmutableList<String> lines, final int lenghtOfBits, final int pos) {
        final ArrayList<Integer> bitCounts = new ArrayList<>(Collections.nCopies(lenghtOfBits, 0));
        final BitCounter bitCounter = new BitCounter(bitCounts);
        lines.forEach(bitCounter::determineBitsCountWithBitOne);

        final char mostCommonBit = getMostCommonBit(bitCounts, lines.size(), pos);
        final ImmutableList<String> filteredLines = lines.stream().filter(x -> x.charAt(pos) == mostCommonBit).collect(ImmutableList.toImmutableList());

        if (filteredLines.size() == 1) {
            return filteredLines;
        }
        return findOxygenGeneratorRating(filteredLines, lenghtOfBits, pos + 1);

    }

    private char getMostCommonBit(final List<Integer> bitCounts, final int size, final int pos) {
        if (bitCounts.get(pos) >= size / 2.0
                || bitCounts.get(pos) == size / 2.0) {
            return '1';
        } else return '0';
    }

    @Override
    public String getName() {
        return "Day 3";
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    class BitCounter {
        List<Integer> _bitCounts;

        public BitCounter(final List<Integer> bitCounts) {
            _bitCounts = bitCounts;
        }

        private void determineBitsCountWithBitOne(final String line) {
            int pos = 0;
            for (final char x : line.toCharArray()) {
                if (x == '1') {
                    _bitCounts.set(pos, _bitCounts.get(pos) + 1);
                }
                pos++;
            }
        }
    }
}
