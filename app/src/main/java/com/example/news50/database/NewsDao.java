package com.example.news50.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.news50.model.Article;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    void putAllArticleInToDatabase(Article article);


    @Query("SELECT * FROM Article")
    List<Article> getAllArticle();

}
