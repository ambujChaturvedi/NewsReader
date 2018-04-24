package com.example.user.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.user.newsreader.adapters.ListArticleAdapter;
import com.example.user.newsreader.clients.ArticleClient;
import com.example.user.newsreader.model2.ArticleModel;
import com.example.user.newsreader.services.ArticleService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopheadlinesActivity extends AppCompatActivity {
    private static final String TAG = "TopheadlinesActivity";
    ArticleService service;
    RecyclerView articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topheadlines);

        //Toast.makeText(this,getIntent().getStringExtra("source"),Toast.LENGTH_SHORT).show();
        articles = (RecyclerView) findViewById(R.id.ArticleList);

        articles.setLayoutManager(new LinearLayoutManager(this));

        String source = getIntent().getStringExtra("source");
        String API_KEY = "47ccbc1a5d3646028fcaa01a481e267f";
        letdotheNetworking(source, API_KEY);


    }

    public void letdotheNetworking(String source, String apikey) {
        service = ArticleClient.getClient().create(ArticleService.class);
        service.getArticles(source, apikey).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                Log.d(TAG, "onResponse: the responce is" + response);
                ListArticleAdapter articleAdapter=new ListArticleAdapter(getBaseContext(),response.body());
                articles.setAdapter(articleAdapter);
                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {
                Log.d(TAG, "onFailure: there was a problem connecting the url" + t.getMessage());

            }
        });

    }
}
