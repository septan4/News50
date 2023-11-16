package com.example.news50.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news50.MainActivity;
import com.example.news50.R;
import com.example.news50.model.Article;

import org.jetbrains.annotations.NonNls;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<Article> articles;
    Context context;

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.textViewSource.setText(article.getSource().getName());
        holder.textViewNews.setText(article.getDescription());
        holder.textViewDate.setText(article.getPublishedAt());
        holder.textViewAuthor.setText(article.getAuthor());
        Glide.with(context).load(article.getImageUrl()).into(holder.imageViewNews);


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNews;
        TextView textViewDate;
        TextView textViewAuthor;
        TextView textViewSource;
        ImageView imageViewNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewNews = itemView.findViewById(R.id.textViewNews);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            imageViewNews = itemView.findViewById(R.id.imageViewNews);
            imageViewNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Article article = articles.get(getAdapterPosition());
                    String articleUrl = article.getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(articleUrl));
                    startActivity(context, intent, null);
                }
            });
        }


    }
}