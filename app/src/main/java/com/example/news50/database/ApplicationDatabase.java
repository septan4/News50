package com.example.news50.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.news50.model.Article;

@Database(entities = Article.class, exportSchema = false, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {
    public static final String DB_NAME = "NewDatabase";
    private static ApplicationDatabase instance;

    public static synchronized ApplicationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ApplicationDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return  instance;
    }
    public abstract NewsDao newsDao();
}
