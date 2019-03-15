package com.example.omdbapijava;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieDao {
    @Insert
    void insertAll(ArrayList<Item> items);

    @Query("SELECT * from Movies")
    List<Item> getAllMovies();
}
