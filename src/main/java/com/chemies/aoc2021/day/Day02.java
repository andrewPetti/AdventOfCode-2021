package com.chemies.aoc2021.day;

import com.chemies.aoc2021.util.FileHelper;
import com.chemies.aoc2021.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

public class Day02 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();
    private int _horizontal;
    private int _depth;
    private int _aim;

    @Override
    public void executePartA() {
        final int result = partA("day02Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partA(final String filename) {
        final ImmutableList<String> inputList = _fileHelper.fileToStringList(filename);

        _horizontal = 0;
        _depth = 0;
        inputList.forEach(this::processLinePartA);

        return _depth * _horizontal;
    }

    private void processLinePartA(final String line) {
        final String[] parts = line.split(" ");
        final String instruction = parts[0];
        final int value = Integer.parseInt(parts[1]);

        switch (instruction) {
            case "forward":
                _horizontal += value;
                break;
            case "down":
                _depth += value;
                break;
            case "up":
                _depth -= value;
        }
    }

    @Override
    public void executePartB() {
        final int result = partB("day02Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partB(final String filename) {
        final ImmutableList<String> inputList = _fileHelper.fileToStringList(filename);

        _horizontal = 0;
        _depth = 0;
        _aim = 0;
        inputList.forEach(this::processLinePartB);

        return _depth * _horizontal;
    }

    private void processLinePartB(final String line) {
        final String[] parts = line.split(" ");
        final String instruction = parts[0];
        final int value = Integer.parseInt(parts[1]);

        switch (instruction) {
            case "forward":
                _horizontal += value;
                _depth += _aim * value;
                break;
            case "down":
                _aim += value;
                break;
            case "up":
                _aim -= value;
        }
    }

    @Override
    public String getName() {
        return "Day 2";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
