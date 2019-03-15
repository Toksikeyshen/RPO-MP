package com.example.omdbapijava;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static android.arch.persistence.room.Room.databaseBuilder;

@Database(entities = {Item.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDAO();
    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase (final Context context){
        if (INSTANCE == null){
            synchronized (MovieDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movieDatabase").build();
                }
            }
        }

        return INSTANCE;
    }
}
