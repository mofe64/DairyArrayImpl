package com.nubari;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Diary {
    private int maxNumberOfEntries = 10;
    private Entry[] entries = new Entry[maxNumberOfEntries];
    private int numberOfEntriesSoFar = 0;


    public void addEntry(Entry entry) {
        if (numberOfEntriesSoFar < maxNumberOfEntries) {
            for (int i = 0; i < entries.length; i++) {
                if (entries[i] == null) {
                    entries[i] = entry;
                    numberOfEntriesSoFar++;
                    System.out.println("Entry saved successfully");
                    break;
                }
            }
        } else {
            System.out.println("Diary is currently full");
        }

    }

    public Entry[] listEntries() {
        Entry[] filledEntries = new Entry[numberOfEntriesSoFar];
        int counter = 0;
        for (Entry entry : entries) {
            if (entry != null) {
                filledEntries[counter] = entry;
                counter++;
            }
        }
        return filledEntries;
    }

    public Entry getEntry(String title) {
        title = title.toLowerCase();
        for (Entry entry : entries) {
            if (entry != null && entry.getTitle().equals(title)) {
                System.out.println("Entry found successfully");
                return entry;
            }
        }
        return null;
    }

    public Entry[] searchEntries(String query) {
        query = query.toLowerCase();
        int[] nullValuePositions = new int[entries.length];
        int numberOfNullValues = 0;
        Entry[] matchingEntriesWithNullValues = new Entry[numberOfEntriesSoFar];
        for (int position = 0; position < entries.length; position++) {
            if (entries[position] != null && entries[position].getTitle().startsWith(query)) {
                matchingEntriesWithNullValues[position] = entries[position];
            } else if (entries[position] != null) {
                nullValuePositions[position] = position;
                numberOfNullValues++;
            }
        }
        Entry[] matchingEntriesWithoutNullValues = new Entry[matchingEntriesWithNullValues.length - numberOfNullValues];
        int counter = 0;
        for (Entry entry : matchingEntriesWithNullValues) {
            if (counter < matchingEntriesWithoutNullValues.length) {
                if (entry != null) {
                    matchingEntriesWithoutNullValues[counter] = entry;
                    counter++;
                }
            }
        }
        return matchingEntriesWithoutNullValues;
    }

    public Entry[] matchKeyword(String keyword) {
        int[] nullValuePositions = new int[entries.length];
        int numberOfNullValues = 0;
        Entry[] matchingEntriesWithNullValues = new Entry[numberOfEntriesSoFar];
        for (int position = 0; position < numberOfEntriesSoFar; position++) {
            System.out.println("position is " + position);
            if (entries[position] != null && (entries[position].getTitle().toLowerCase().contains(keyword.toLowerCase()))
                    || entries[position].getEntry().toLowerCase().contains(keyword.toLowerCase())){
                matchingEntriesWithNullValues[position] = entries[position];
            } else if (entries[position] != null) {
                nullValuePositions[position] = position;
                numberOfNullValues++;
            }
        }
        Entry[] matchingEntriesWithoutNullValues = new Entry[matchingEntriesWithNullValues.length - numberOfNullValues];
        int counter = 0;
        for (Entry entry : matchingEntriesWithNullValues) {
            if (counter < matchingEntriesWithoutNullValues.length) {
                if (entry != null) {
                    matchingEntriesWithoutNullValues[counter] = entry;
                    counter++;
                }
            }
        }
        return matchingEntriesWithoutNullValues;
    }

    public int deleteEntry(String entryTitle) {
        for (int entryIndex = 0; entryIndex < entries.length; entryIndex++) {
            if (entries[entryIndex] != null && entries[entryIndex].getTitle().equals(entryTitle)) {
                entries[entryIndex] = null;
                numberOfEntriesSoFar--;
                return 1;
            }
        }
        return -1;
    }
}
