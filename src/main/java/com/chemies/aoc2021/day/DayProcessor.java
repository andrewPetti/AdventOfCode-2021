package com.chemies.aoc2021.day;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class DayProcessor {
    private final Scanner _scanner = new Scanner(in);

    public void executeDay(final Day day) {
        boolean stop = false;
        while (!stop) {
            out.printf("Run %s part a or b? (q to quit)%n", getFormattedName(day));
            stop = true;
            final String response = _scanner.next().toLowerCase();
            switch (response) {
                case "a":
                    day.executePartA();
                    break;
                case "b":
                    day.executePartB();
                    break;
                case "q":
                    out.printf("Exiting %s%n", getFormattedName(day));
                    return;
                default:
                    stop = false;
                    out.println("Invalid choice. Please pick again");
                    break;
            }
        }
    }

    public String getFormattedName(final Day day) {
        return Ansi.colorize(day.getName(), Attribute.RED_TEXT());
    }
}
