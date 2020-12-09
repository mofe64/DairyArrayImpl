package com.nubari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    void setup(){
        user = new User("Eyimofe", "Ogunbiyi", "test1234");
    }
    @AfterEach
    void tearDown(){}

    @Test
    void testUserInstanceVariablesAreInitializedOnCreation(){
        User user = new User("Test", "Test", "1234");
        assertNotNull(user.getPassword());
        assertNotNull(user.getUserDiary());
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
    }
    @Test
    void authenticateReturnsFalseIfWrongPasswordGiven() {
        assertEquals(false, user.authenticate("1234567"));
    }

    @Test
    void getUserDiaryReturnsDiaryObject() {
        assertEquals(Diary.class,user.getUserDiary().getClass());
    }

    @Test
    void getPassword() {
        String password = "test1234";
        assertEquals(password, user.getPassword());
    }
}