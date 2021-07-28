package com.example.adote.helper;

import com.example.adote.models.user.User;

import java.util.HashMap;

public class UserDBM {
    private static final UserDBM dbm = new UserDBM(); // Singleton Database
    private HashMap<String, User> users;

    /**
     * Initializes a UserDBM instance.
     */
    public UserDBM() {
       users = new HashMap<>();
    }

    /**
     * Returns the UserDBM singleton instance.
     *
     * @return Singleton instance.
     */
    public static UserDBM getInstance() {
        return dbm;
    }

    /**
     * Adds a new user to the database.
     *
     * @param newUser User to be added.
     */
    public void addNewUser(User newUser) {
        String uid = newUser.getId();
        if (!users.containsKey(uid)) {
            users.put(uid, newUser);
        }
    }

    /**
     * Gets a new user from the UserDBM given their
     * unique id.
     *
     * @param uid Searched User's unique id.
     * @return Searched User.
     */
    public User getUser(String uid) {
        return users.get(uid);
    }
}


