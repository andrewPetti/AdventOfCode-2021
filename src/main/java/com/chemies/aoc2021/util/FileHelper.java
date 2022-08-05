package com.chemies.aoc2021.util;

import com.google.common.collect.ImmutableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHelper {
    public ImmutableList<String> fileToStringList(final String filename) {
        final ArrayList<String> list = new ArrayList<>();

        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }


    public ImmutableList<Integer> fileToIntegerList(final String filename) {
        final ArrayList<Integer> list = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }

    public ImmutableList<Long> fileToLongList(final String filename) {
        final ArrayList<Long> list = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(Long.parseLong(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }

    public ImmutableList<ImmutableList<String>> fileToGroupedList(final String filename) {
        final ArrayList<ImmutableList<String>> outerList = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            final ArrayList<String> innerList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                if (line == null || line.isEmpty()) {
                    outerList.add(ImmutableList.copyOf(innerList));
                    innerList.clear();
                    if (line == null) {
                        cont = false;
                    }
                } else {
                    innerList.add(line);
                }
                line = reader.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(outerList);
    }

    private BufferedReader getReader(final String filename) throws FileNotFoundException {
        final BufferedReader reader;
        try {
            reader =
                    new BufferedReader(new FileReader("D:\\Git\\AdventOfCode-2021\\src\\main\\java\\com\\chemies\\AoC2021\\data\\" + filename));

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException(e.getMessage());
        }

        return reader;
    }
}
