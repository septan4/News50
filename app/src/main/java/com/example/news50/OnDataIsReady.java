package com.example.news50;

import com.example.news50.model.Article;

import java.util.List;

public interface OnDataIsReady {
    void onDataRead(List<Article> articles);
}
