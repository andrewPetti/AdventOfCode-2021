package com.chemies.aoc2021.day;

import com.chemies.aoc2021.util.FileHelper;
import com.chemies.aoc2021.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;
import org.javatuples.Triplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day04 implements Day {
    private final TextFormatter _textFormatter = new TextFormatter();
    private final FileHelper _fileHelper = new FileHelper();

    @Override
    public void executePartA() {
        final int result = partA("day04Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partA(final String filename) {
        final ImmutableList<ImmutableList<String>> lineGroups = _fileHelper.fileToGroupedList(filename);

        final ImmutableList<String> callingNumbers = getCallingNumbers(lineGroups);
        final Map<Triplet<Integer, Integer, Integer>, String> bingoCards = getBingoCards(lineGroups);
        final Map<Triplet<Integer, Integer, Integer>, Integer> foundValues = new HashMap<>();
        bingoCards.keySet().forEach(x -> foundValues.put(x, 0));

        final int numberOfBingoCards = lineGroups.size() - 1;
        String lastCalledNumber;
        for (final String calledNumber : callingNumbers) {
            lastCalledNumber = calledNumber;
            final ImmutableList<Triplet<Integer, Integer, Integer>> foundKeys = bingoCards.entrySet()
                    .stream()
                    .filter(entry -> calledNumber.equals(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(ImmutableList.toImmutableList());

            foundKeys.forEach(x -> foundValues.put(x, 1));

            final int winningCard = checkCards(foundValues, numberOfBingoCards);
            if (winningCard > 0) {
                final ImmutableList<Triplet<Integer, Integer, Integer>> winningCardKeys = foundValues.entrySet()
                        .stream()
                        .filter(entry -> winningCard == entry.getKey().getValue0())
                        .filter(entry -> entry.getValue() == 0)
                        .map(Map.Entry::getKey)
                        .collect(ImmutableList.toImmutableList());

                final int sumUnCalled = bingoCards.entrySet()
                        .stream()
                        .filter(entry -> winningCardKeys.contains(entry.getKey()))
                        .map(Map.Entry::getValue)
                        .mapToInt(Integer::parseInt)
                        .sum();

                return sumUnCalled * Integer.parseInt(lastCalledNumber);
            }
        }
        return 0;
    }

    private int checkCards(final Map<Triplet<Integer, Integer, Integer>, Integer> foundValues, final int numberOfCards) {

        for (int cardNumber = 1; cardNumber <= numberOfCards; cardNumber++) {
            final int finalCardNumber = cardNumber;
            final Map<Triplet<Integer, Integer, Integer>, Integer> card = foundValues.entrySet()
                    .stream()
                    .filter(entry -> Integer.parseInt(String.valueOf(entry.getKey().getValue0())) == finalCardNumber)
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
            for (int row = 0; row < 5; row++) {
                final int finalRow = row;
                final int sum = card.entrySet()
                        .stream()
                        .filter(entry -> Integer.parseInt(String.valueOf(entry.getKey().getValue1())) == finalRow)
                        .map(Map.Entry::getValue)
                        .mapToInt(Integer::intValue)
                        .sum();
                if (sum == 5) {
                    return cardNumber;
                }
            }

            for (int col = 0; col < 5; col++) {
                final int finalCol = col;
                final int sum = card.entrySet()
                        .stream()
                        .filter(entry -> Integer.parseInt(String.valueOf(entry.getKey().getValue1())) == finalCol)
                        .map(Map.Entry::getValue)
                        .mapToInt(Integer::intValue)
                        .sum();
                if (sum == 5) {
                    return cardNumber;
                }
            }
        }

        return 0;
    }

    private Map<Triplet<Integer, Integer, Integer>, String> getBingoCards(final ImmutableList<ImmutableList<String>> lineGroups) {
        final Map<Triplet<Integer, Integer, Integer>, String> cardMap = new HashMap<>();
        for (int card = 1; card < lineGroups.size(); card++) {
            final ImmutableList<String> rows = lineGroups.get(card);
            for (int row = 0; row < rows.size(); row++) {
                final String cleanedRow = rows.get(row).replaceAll("  ", " ").trim();
                final ImmutableList<String> rowValues = ImmutableList.copyOf(cleanedRow.split(" "));
                for (int col = 0; col < rowValues.size(); col++) {
                    cardMap.put(Triplet.with(card, row, col), rowValues.get(col));
                }
            }
        }

        return cardMap;
    }

    private ImmutableList<String> getCallingNumbers(final List<ImmutableList<String>> lineGroups) {
        return ImmutableList.copyOf(lineGroups.get(0).get(0).split(","));
    }

    @Override
    public void executePartB() {
        final int result = partB("day04Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    private int partB(final String filename) {
        return 0;
    }

    @Override
    public String getName() {
        return "Day 4";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
