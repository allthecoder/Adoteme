package com.example.adote.models.user;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
    INSTITUTIONAL(0), PRIVATE(1);

    private int value;
    private static Map map = new HashMap<>();

    private UserType(int value) {
        this.value = value;
    }

    static {
        for (UserType type : UserType.values()) {
            map.put(type.value, type);
        }
    }

    public static UserType valueOf(int userType) {
        return (UserType) map.get(userType);
    }

    public int getValue() {
        return value;
    }
}
