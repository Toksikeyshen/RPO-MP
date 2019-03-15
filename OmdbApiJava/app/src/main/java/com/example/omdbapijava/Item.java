package com.example.omdbapijava;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Movies")
public class Item {
    @PrimaryKey
    @NonNull
    private String MovieTitle;
    @ColumnInfo(name = "imageUrl")
    private String ImageUrl;
    @ColumnInfo(name = "movieType")
    private String MovieType;

    public Item(String imageUrl, @NonNull String movieTitle, String movieType) {
        ImageUrl = imageUrl;
        MovieTitle = movieTitle;
        MovieType = movieType;
    }

    public Item() {
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getMovieTitle() {
        return MovieTitle;
    }

    public String getMovieType() {
        return MovieType;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public void setMovieType(String movieType) {
        MovieType = movieType;
    }
}
