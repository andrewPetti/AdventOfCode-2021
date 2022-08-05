package com.chemies.aoc2021.util;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class TextFormatter {
    public String format(final int result, final Attribute colourAttribute) {
        return Ansi.colorize(String.format("%d", result), colourAttribute);
    }

    public String format(final long result, final Attribute colourAttribute) {
        return Ansi.colorize(String.format("%d", result), colourAttribute);
    }
}
