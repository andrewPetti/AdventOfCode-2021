package com.chemies.aoc2021.day;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static java.lang.System.in;

public abstract class AbstractDay {
    private final Scanner _scanner = new Scanner(in);
    // protected FileHelper _fileHelper = new FileHelper();

    protected String dayName;

    public abstract void executePartA();

    public abstract void executePartB();

    protected abstract String getName();

    public String getFormattedName() {
        return Ansi.colorize(getName(), Attribute.RED_TEXT());
    }


}

//    public String formatAnswer(int result) {
//        return Ansi.colorize(String.format("%d", result), Attribute.GREEN_TEXT());
//    }
//
//    public String formatAnswer(long result) {
//        return Ansi.colorize(String.format("%d", result), Attribute.GREEN_TEXT());
//    }
//}
