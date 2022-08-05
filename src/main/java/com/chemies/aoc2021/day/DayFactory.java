package com.chemies.aoc2021.day;

public class DayFactory {

    public static Day getDay(final int day) {
        if (day == 1) {
            return new Day01();
        } else if (day == 2) {
            return new Day02();
        }

        return new UnimplementedDay(day);
    }
}
