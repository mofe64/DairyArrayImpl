package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryTest {
    Diary diary;
    Entry entry;

    @BeforeEach
    void setUp() {
        diary = new Diary();
        entry = new Entry("Test title", "Test Entry");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUserShouldBeAbleToAddAnEntry() {
        diary.addEntry(entry);
        assertEquals(entry, diary.getEntry("Test title"));
    }

    @Test
    void testUserShouldBeAbleToViewAllEntries() {
        Entry entry = new Entry("Test title", "test entry");
        Entry entry2 = new Entry("Test title2", "test entry 2");
        Entry[] arrayOfEntries = {entry, entry2};
        diary.addEntry(entry);
        diary.addEntry(entry2);
        assertNotNull(diary.listEntries());
        assertEquals(arrayOfEntries.length, diary.listEntries().length);
        assertArrayEquals(arrayOfEntries, diary.listEntries());
    }

    @Test
    void testUserShouldBeAbleToGetAnEntryViaItsTitleWithTheExactSearchEntryMethod() {
        Entry entry1 = new Entry("test1", "Test entry 2");
        Entry entry2 = new Entry("test2");
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        assertEquals(entry1, diary.getEntry("test1"));
    }

    @Test
    void testUserShouldGetNullValueIfEntrySearchedForDoesNotExist() {
        Entry entry = new Entry("Test");
        diary.addEntry(entry);
        assertEquals(null, diary.getEntry("Test Entry Cannot be found"));

    }

    @Test
    void testUserShouldBeAbleToDeleteAnEntryViaItsTitle() {
        Entry entry = new Entry("TEST1");
        Entry entry1 = new Entry("TEST2");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        assertEquals(2, diary.listEntries().length);
        diary.deleteEntry("TEST1");
        //System.out.println(diary.listEntries()[0]);
        assertEquals(null, diary.getEntry("TEST1"));
        assertEquals(1, diary.deleteEntry("TEST2"));
    }

    @Test
    void testUserShouldGetAListOfAllMatchingEntriesWhenBroadSearchMethodCalled() {
        Entry entry = new Entry("Testing ");
        Entry entry1 = new Entry("different entry");
        Entry entry2 = new Entry("some different entry");
        Entry entry3 = new Entry("testiiiiiiiiing");
        Entry entry4 = new Entry("test");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        diary.addEntry(entry3);
        diary.addEntry(entry4);
        assertEquals(5, diary.listEntries().length);
        Entry[] matchingEntriesForOurTest = {entry, entry3, entry4};
        Entry[] matchingEntries = diary.searchEntries("test");
        assertEquals(matchingEntriesForOurTest.length, matchingEntries.length);
    }

    @Test
    void testUserShouldGetAListOfAllMatchingEntriesWhenSearchByKeywordMethodIsCalled(){
        Entry entry = new Entry("My love story", "test entry");
        Entry entry1 = new Entry("My sad story", "life sucks doesn't it ");
        Entry entry2 = new Entry("Life is hard", "Well I'm sad again");
        Entry entry3 = new Entry("Will i find love again", "The search continues");
        Entry entry4 = new Entry("untitled", "love is a scam");
        diary.addEntry(entry);
        diary.addEntry(entry1);
        diary.addEntry(entry2);
        diary.addEntry(entry3);
        diary.addEntry(entry4);
        assertEquals(5, diary.listEntries().length);
        Entry[] containingEntries1 = {entry, entry3, entry4};
        Entry[] matchingEntries = diary.matchKeyword("love");
        assertEquals(containingEntries1.length, matchingEntries.length);
        Entry[] containingEntries2 = {entry1, entry2};
        assertEquals(containingEntries2.length, diary.matchKeyword("life").length);

    }
}