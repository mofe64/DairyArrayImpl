package com.nubari;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    private static int sentinel = 1;

    public static void main(String[] args) {
        try {
            JOptionPane.showMessageDialog(null, "Welcome to the The user Diary APP. Follow the following instructions to register and set up your diary");
            String firstName = JOptionPane.showInputDialog("Enter your firstname");
            String lastName = JOptionPane.showInputDialog("Enter your lastname");
            String password = JOptionPane.showInputDialog("Enter your password");
            JOptionPane.showMessageDialog(null, "Creating your account now ......");
            User user = new User(firstName, lastName, password);
            JOptionPane.showMessageDialog(null, "Account created successfully");
            int userInput;
            String userPassword = JOptionPane.showInputDialog("Enter your password to proceed");
            boolean authenticated = user.authenticate(userPassword);
            if (authenticated != true) {
                JOptionPane.showMessageDialog(null, "Incorrect Password, exiting .... ");
            } else {
                JOptionPane.showMessageDialog(null, "Welcome to your diary");
                while (sentinel != -1) {
                    userInput = Integer.parseInt(JOptionPane.showInputDialog("1. press 1 to create a new Entry \n" +
                            "2. press 2 to view all diary entries \n" +
                            "3. press 3 to Search for entries\n" +
                            "4. press 4 to delete a specific diary entry \n" +
                            "5. press 5 to exit "));

                    switch (userInput) {
                        case 1: {
                            createEntry(user);
                            break;
                        }
                        case 2: {
                            viewEntries(user);
                            break;
                        }
                        case 3: {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog("1. Enter 1 to search via keywords" + "\n" +
                                    "2. Enter 2 to search for an entry via its title"));
                            if (userInput == 1) {
                                KeywordEntrySearch(user);
                                break;
                            } else if (userInput == 2) {
                                getSpecificEntry(user);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid option selected. Returning to main menu");
                                break;
                            }
                        }
                        case 4: {
                            deleteSpecificEntry(user);
                            break;
                        }
                        case 5: {
                            JOptionPane.showMessageDialog(null, "Thank you for using the app, Exiting now ...");
                            sentinel = -1;
                            break;
                        }
                        default: {
                            JOptionPane.showMessageDialog(null, "Valid options are only between 1 to 5, returning to main menu .....");
                            break;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "GoodBye ....");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
        }
    }

    public static void createEntry(User user) {
        try {
            String entryTitle = JOptionPane.showInputDialog("Enter the Entry Title");
            String entryBody;
            int userChoice = Integer.parseInt(JOptionPane.showInputDialog("Do you wish to enter the entry details at this time, press 1 for yes and 2 for no "));
            if (userChoice == 1) {
                System.out.println("Enter Entry body");
                entryBody = JOptionPane.showInputDialog("Enter entry body");
                JOptionPane.showMessageDialog(null, "Entry added successfully... saving now....");
                Entry entry = new Entry(entryTitle, entryBody);
                user.getUserDiary().addEntry(entry);
            } else {
                JOptionPane.showMessageDialog(null, "Saving the entry with just the title");
                Entry entry = new Entry(entryTitle);
                user.getUserDiary().addEntry(entry);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye ....");
            sentinel = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
            sentinel = -1;
        }
    }

    public static void viewEntries(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Entry[] entries = user.getUserDiary().listEntries();
            if (entries.length > 0) {
                JOptionPane.showMessageDialog(null, "Here are all entries so far");
                for (Entry entry : entries) {
                    stringBuilder.append(entry);
                    stringBuilder.append("\n");
                    stringBuilder.append("\n");
                }
                JOptionPane.showMessageDialog(null, stringBuilder);
            } else {
                JOptionPane.showMessageDialog(null, "There are currently no entries yet");
                JOptionPane.showMessageDialog(null, "Returning to main menu");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye ....");
            sentinel = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
            sentinel = -1;
        }
    }

    public static void getSpecificEntry(User user) {
        try {
            int numberOfConsecutiveSearches = 0;
            Entry entry;
            String title = JOptionPane.showInputDialog("Please enter the title of the entry");
            if (user.getUserDiary().getEntry(title) != null) {
                entry = user.getUserDiary().getEntry(title);
                JOptionPane.showMessageDialog(null, entry);
            } else {
                JOptionPane.showMessageDialog(null, "Entry not found ....");
                int userInput = Integer.parseInt(JOptionPane.showInputDialog("Do you wish to try searching again with another title, enter 1 for yes, 2 for no"));
                if (userInput == 1 && numberOfConsecutiveSearches <= 3) {
                    numberOfConsecutiveSearches++;
                    getSpecificEntry(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Exiting to menu ....");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye ....");
            sentinel = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
            sentinel = -1;
        }
    }

    public static void deleteSpecificEntry(User user) {
        try {
            int noOfUnsuccessfulTriesSoFar = 0;
            String title = JOptionPane.showInputDialog("Enter the title of the entry you wish to delete");
            if (user.getUserDiary().deleteEntry(title) == 1) {
                JOptionPane.showMessageDialog(null, "Entry deleted sucessfully");
            } else {
                int userInput = Integer.parseInt(JOptionPane.showInputDialog("Could not find that entry, do you wish to try searching again with another title, enter 1 for yes, 2 for no"));
                if (userInput == 1 && noOfUnsuccessfulTriesSoFar <= 3) {
                    noOfUnsuccessfulTriesSoFar++;
                    deleteSpecificEntry(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Exiting to menu ....");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye ....");
            sentinel = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
            sentinel = -1;
        }
    }

    public static void KeywordEntrySearch(User user) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            int numberOfConsecutiveSearches = 0;
            String keyword = JOptionPane.showInputDialog("Enter Keyword to search for ");
            Entry[] entries = user.getUserDiary().matchKeyword(keyword);
            if (entries.length > 0) {
                JOptionPane.showMessageDialog(null, "Here are all entries matching " + keyword);
                for (Entry entry : entries) {
                    stringBuilder.append(entry);
                    stringBuilder.append("\n");
                    stringBuilder.append("\n");
                }
                JOptionPane.showMessageDialog(null, stringBuilder);
            } else {
                JOptionPane.showMessageDialog(null, "No Entry matching that keyword found ....");
                int userInput = Integer.parseInt(JOptionPane.showInputDialog("Do you wish to try searching again with another title, enter 1 for yes, 2 for no"));
                if (userInput == 1 && numberOfConsecutiveSearches <= 3) {
                    numberOfConsecutiveSearches++;
                    KeywordEntrySearch(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Exiting to menu ....");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Goodbye ....");
            sentinel = -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Looks like something went wrong exiting now ....");
            sentinel = -1;
        }
    }
}


