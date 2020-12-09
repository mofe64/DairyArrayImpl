package com.nubari;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private Diary diary;

    public User(String firstName, String lastName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        diary = new Diary();
    }

    public boolean authenticate(String givenPassword) {
        if (password.equals(givenPassword)) {
            return true;
        } else {
            return false;
        }
    }
    public Diary getUserDiary(){
        return diary;
    }
    public String getPassword(){
        return password;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }



}
