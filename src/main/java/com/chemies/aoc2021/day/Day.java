package com.chemies.aoc2021.day;

public interface Day {
    void executePartA();

    void executePartB();

    String getName();
}

//    public String formatAnswer(int result) {
//        return Ansi.colorize(String.format("%d", result), Attribute.GREEN_TEXT());
//    }
//
//    public String formatAnswer(long result) {
//        return Ansi.colorize(String.format("%d", result), Attribute.GREEN_TEXT());
//    }
//}
