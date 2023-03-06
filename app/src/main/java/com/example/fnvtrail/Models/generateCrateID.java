package com.example.fnvtrail.Models;

public class generateCrateID {

    private static final long LIMIT = 10000000000L;
    private static long last = 0;

    public static long generateCrateID() {
        long id = System.currentTimeMillis() % LIMIT;
        if ( id <= last ) {
            id = (last + 1) % LIMIT;
        }
        return last = id;
    }
}
