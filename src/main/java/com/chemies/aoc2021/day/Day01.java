package com.chemies.aoc2021.day;

import com.chemies.aoc2021.util.FileHelper;
import com.chemies.aoc2021.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

public class Day01 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final int result = partA("day01Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    @Override
    public void executePartB() {
        final int result = partB("day01Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partB(final String filename) {

        final ImmutableList<Integer> integers = _fileHelper.fileToIntegerList(filename);

        int count = 0;
        int previousWindow = integers.get(0) + integers.get(1) + integers.get(2);
        for (int i = 1; i < integers.size() - 2; i++) {
            final int currentWindow = integers.get(i) + integers.get(i + 1) + integers.get(i + 2);
            if (previousWindow < currentWindow) {
                count++;
            }
            previousWindow = currentWindow;
        }
        return count;
    }


    @Override
    public String getName() {
        return "Day 1";
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    public int partA(final String filename) {
        final ImmutableList<Integer> integers = _fileHelper.fileToIntegerList(filename);

        int count = 0;
        for (int i = 1; i < integers.size(); i++) {
            if (integers.get(i - 1) < integers.get(i)) {
                count++;
            }

        }
        return count;
    }
}
