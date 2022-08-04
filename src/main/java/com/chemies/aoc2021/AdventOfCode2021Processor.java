package com.chemies.aoc2021;

import com.chemies.aoc2021.day.Day;
import com.chemies.aoc2021.day.DayFactory;
import com.chemies.aoc2021.day.DayProcessor;

import java.util.Scanner;

public class AdventOfCode2021Processor {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final DayProcessor _dayProcessor = new DayProcessor();
    private static final DayFactory _dayFactory = new DayFactory();

    public static void main(final String[] args) {
        System.out.println("Welcome to Andrew's Advent of Code 2021 Solutions\n");

        executeAdventOfCode();

    }

    private static void executeAdventOfCode() {
        boolean stop = false;
        Day day = null;
        int choice;
        while (!stop) {
            try {
                System.out.println("Please enter Day 1-24, any other number to cancel: ");
                choice = _scanner.nextInt();
                stop = true;

                if (choice > 0 && choice < 25) {
                    day = DayFactory.getDay(choice);//_dayFactory.getDay(choice);
                    System.out.printf("%s selected%n", _dayProcessor.getFormattedName(day));
                    _dayProcessor.executeDay(day);
                } else {
                    System.out.printf("Day %d is currently not implemented. Please choose again.%n", choice);
                    stop = false;
                }
            } catch (final Exception e) {
                System.out.println("Invalid input was entered. Please try again.");
                stop = false;
            }


        }
    }
}