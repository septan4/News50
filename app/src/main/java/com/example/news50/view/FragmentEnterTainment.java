package com.example.news50.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news50.OnDataIsReady;
import com.example.news50.R;
import com.example.news50.adapter.NewsAdapter;
import com.example.news50.database.ApplicationDatabase;
import com.example.news50.model.Article;
import com.example.news50.model.News;
import com.example.news50.network.NewsWebServiceBusiness;
import com.example.news50.network.NewsWebServiceEnt;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentEnterTainment extends Fragment {
    boolean isPermissionGranted = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.entertainment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Dexter.withContext(getContext())
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            isPermissionGranted = true;
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView openNews = view.findViewById(R.id.imageViewNews);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewNews);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create()).build();


        NewsWebServiceEnt newsWebService = retrofit.create(NewsWebServiceEnt.class);
        newsWebService.getAllNews().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();
                downloadImage(news.getArticles());
                NewsAdapter newsAdapter = new NewsAdapter(news.getArticles(), getContext());
                recyclerView.setAdapter(newsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {


            }
        });
        getAllArticles((OnDataIsReady) getContext());


    }

    public void downloadImage(List<Article> articleList) {
        for (Article article : articleList) {
            Ion.with(requireContext())
                    .load(article.getImageUrl())
                    .withBitmap()
                    .asBitmap()
                    .setCallback(new FutureCallback<Bitmap>() {
                        @Override
                        public void onCompleted(Exception e, Bitmap result) {
                            if (result != null) {
                                String filePath = saveImage(article.getTitle(), result);
                                article.setImageFilePath(filePath);
                                storeInDatabase(article);
                            }

                        }
                    });
        }
    }

    public String saveImage(String title, Bitmap imageFileBitmap) {
        if (isExternalStorageWritable()) {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDirectory = new File(root + "/saved_images");
            myDirectory.mkdir();
            String fName = title + ".jpg";
            File saveFile = new File(myDirectory, fName);
            if (saveFile.exists()) {
                saveFile.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(saveFile);
                imageFileBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
                return myDirectory + "/" + fName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public void storeInDatabase(Article article) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getInstance(getContext());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                applicationDatabase.newsDao().putAllArticleInToDatabase(article);

            }
        });
    }

    public void getAllArticles( OnDataIsReady onDataIsReady) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getInstance(getContext());
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                onDataIsReady.onDataRead(applicationDatabase.newsDao().getAllArticle());


            }
        });
    }



}