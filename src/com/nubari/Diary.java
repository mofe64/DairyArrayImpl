package com.nubari;

import java.util.ArrayList;

public class Diary {
    private int maxNumberOfEntries =10;
    private Entry[] entries = new Entry[maxNumberOfEntries];
    private int lastEntryPosition = 0;
    private int numberOfEntriesSoFar = 0;
    private int[] filledSlots = new int[maxNumberOfEntries];


    public void addEntry(Entry entry) {
        for (int i = 0; i< entries.length; i++){
            if(entries[i] == null){
                entries[i] = entry;
                numberOfEntriesSoFar++;
                filledSlots[i] = i;
                System.out.println("Entry saved successfully");
                break;
            }
        }
    }

    public Entry[] listEntries() {
        Entry[] filledEntries = new Entry[numberOfEntriesSoFar];
        int counter =0;
        for(Entry entry: entries){
            if(entry!=null){
                filledEntries[counter] = entry;
                counter++;
            }
        }
        return filledEntries;
    }

    public Entry getEntry(String test_title) {
        for (Entry entry : entries) {
            if (entry != null && entry.getTitle().equals(test_title)) {
                System.out.println("Entry found successfully");
                return entry;
            }
        }
        return null;
    }


    public int getLastEntryPosition(){
        return lastEntryPosition;
    }

    public int deleteEntry(String entryTitle) {
        for (int entryIndex = 0; entryIndex < entries.length; entryIndex++) {
            if (entries[entryIndex] != null && entries[entryIndex].getTitle().equals(entryTitle)) {
                entries[entryIndex] = null;
                return 1;
            }
        }
        return -1;
    }
}
