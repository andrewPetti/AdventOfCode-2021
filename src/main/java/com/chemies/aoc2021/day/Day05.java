package com.chemies.aoc2021.day;

import com.chemies.aoc2021.util.FileHelper;
import com.chemies.aoc2021.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class Day05 implements Day {
    private final TextFormatter _textFormatter = new TextFormatter();
    private final FileHelper _fileHelper = new FileHelper();

    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> _horizontalSegments = new ArrayList<>();
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> _verticalSegments = new ArrayList<>();
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> _diagonalSegments = new ArrayList<>();
    int maxHorizontal = 0;
    int maxVertical = 0;

    @Override
    public void executePartA() {
        final int result = partA("day05Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partA(final String filename) {
        processSegments(_fileHelper.fileToStringList(filename));
        final int[][] board = buildBoard();

        processHorizontal(board);
        processVertical(board);

        return (int) Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(x -> x > 1)
                .count();

    }

    private void processVertical(final int[][] board) {
        _verticalSegments.forEach(seg -> {
            final Integer x = seg.getValue0().getValue0();
            IntStream.rangeClosed(min(seg.getValue0().getValue1(), seg.getValue1().getValue1())
                            , max(seg.getValue0().getValue1(), seg.getValue1().getValue1()))
                    .forEach(y -> board[x][y]++);
        });
    }

    private void processHorizontal(final int[][] board) {
        _horizontalSegments.forEach(seg -> {
            final Integer y = seg.getValue0().getValue1();
            IntStream.rangeClosed(min(seg.getValue0().getValue0(), seg.getValue1().getValue0())
                            , max(seg.getValue0().getValue0(), seg.getValue1().getValue0()))
                    .forEach(x -> board[x][y]++);
        });
    }

    private int[][] buildBoard() {
        return new int[maxHorizontal + 1][maxVertical + 1];
    }

    private void processSegments(final ImmutableList<String> rawSegments) {
        rawSegments.forEach(line -> {
            final List<String> rawSegment = Arrays.asList(line.split("->"));

            final String[] pair1 = rawSegment.get(0).trim().split(",");
            final String[] pair2 = rawSegment.get(1).trim().split(",");

            final Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> segPairs = Pair.with(Pair.with(Integer.parseInt(pair1[0]), Integer.parseInt(pair1[1])),
                    Pair.with(Integer.parseInt(pair2[0]), Integer.parseInt(pair2[1])));

            groupSegment(segPairs);
            updateMaxValue(segPairs);
        });
    }

    private void updateMaxValue(final Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> segPairs) {
        if (segPairs.getValue0().getValue0() > maxHorizontal) {
            maxHorizontal = segPairs.getValue0().getValue0();
        }

        if (segPairs.getValue1().getValue0() > maxHorizontal) {
            maxHorizontal = segPairs.getValue1().getValue0();
        }

        if (segPairs.getValue0().getValue1() > maxVertical) {
            maxVertical = segPairs.getValue0().getValue1();
        }

        if (segPairs.getValue1().getValue1() > maxVertical) {
            maxVertical = segPairs.getValue1().getValue1();
        }
    }

    private void groupSegment(final Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> segPairs) {
        if (segPairs.getValue0().getValue0().equals(segPairs.getValue1().getValue0())) {
            _verticalSegments.add(segPairs);
        } else if (segPairs.getValue0().getValue1().equals(segPairs.getValue1().getValue1())) {
            _horizontalSegments.add(segPairs);
        } else {
            _diagonalSegments.add(segPairs);
        }


    }

    @Override
    public void executePartB() {
        final int result = partB("day05Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partB(final String filename) {
        processSegments(_fileHelper.fileToStringList(filename));
        final int[][] board = buildBoard();

        processHorizontal(board);
        processVertical(board);
        processDiagonal(board);

//        final int print = 1;
//        if (print == 1) {
//            for (int i = 0; i < 10; i++) { //this equals to the row in our matrix.
//                for (int j = 0; j < 10; j++) { //this equals to the column in each row.
//                    System.out.print(board[j][i] + " ");
//                }
//                System.out.print("\n");
//            }
//        }

        return (int) Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(x -> x > 1)
                .count();
    }


    private void processDiagonal(final int[][] board) {
        _diagonalSegments.forEach(seg -> {

            final Pair<Integer, Integer> ptA = seg.getValue0();
            final Pair<Integer, Integer> ptB = seg.getValue1();

            final int rise = ptB.getValue1() - ptA.getValue1();
            final int run = ptB.getValue0() - ptA.getValue0();
            int xa = ptA.getValue0();
            int ya = ptA.getValue1();

            if (run > 0 && rise < 0) {
                //quad 1
                for (int i = 0; i < run + 1; i++) {
                    board[xa][ya]++;
                    xa++;
                    ya--;
                }
            } else if (run > 0 && rise > 0) {
                for (int i = 0; i < run + 1; i++) {
                    board[xa][ya]++;
                    xa++;
                    ya++;
                }
            } else if (run < 0 && rise > 0) {
                //quad 3
                for (int i = 0; i < abs(run) + 1; i++) {
                    board[xa][ya]++;
                    xa--;
                    ya++;
                }
            } else {
                //quad 4
                for (int i = 0; i < abs(run) + 1; i++) {
                    board[xa][ya]++;
                    xa--;
                    ya--;
                }
            }
        });
    }

    @Override
    public String getName() {
        return "Day 5";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
