package com.chemies.aoc2021.day;

public class DayFactory {

    public static Day getDay(final int day) {
        if (day == 1) {
            return new Day01();
        } else if (day == 2) {
            return new Day02();
        } else if (day == 3) {
            return new Day03();
        } else if (day == 4) {
            return new Day04();
        } else if (day == 5) {
            return new Day05();
        }

        return new UnimplementedDay(day);
    }
}
