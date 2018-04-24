package com.example.user.newsreader;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.newsreader.adapters.ListSourceAdapter;
import com.example.user.newsreader.common.Common;
import com.example.user.newsreader.model.SourceModel;
import com.example.user.newsreader.services.NewsService;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "NewsReader";

    private static final String API_KEY="47ccbc1a5d3646028fcaa01a481e267f";

    RecyclerView sourcelist;
    NewsService mNewsServce;
    RecyclerView.LayoutManager layoutManager;
    SpotsDialog alertDialog;
    ListSourceAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // init cache
        Paper.init(this);

        //initService
        mNewsServce=Common.getService();

        //init views
        sourcelist=(RecyclerView) findViewById(R.id.recyclerview);
        sourcelist.setHasFixedSize(true);
         layoutManager =new LinearLayoutManager(this);
        sourcelist.setLayoutManager(layoutManager);
         alertDialog=new SpotsDialog(this);
         swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
         swipeRefreshLayout.setOnRefreshListener(
                 new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 loadSourceData(true);
             }
         });

         loadSourceData(false);

    }

    private void loadSourceData(boolean isRefreshed) {
//
//        if(!isRefreshed){
//            // is refreshed =false
//
//            String cache=Paper.book().read("cache");
//            if(cache!=null && !cache.isEmpty()){
//                // then we have cache data
//                SourceModel model =new Gson().fromJson(cache,SourceModel.class);
//                adapter=new ListSourceAdapter(this,model);
//                adapter.notifyDataSetChanged();
//                sourcelist.setAdapter(adapter);
//            }
//            else{
//                // we have no data in cache
//                alertDialog.show();
//                mNewsServce.getSources(API_KEY).enqueue(new Callback<SourceModel>() {
//                    @Override
//                    public void onResponse(Call<SourceModel> call, Response<SourceModel> response) {
//                        Log.d(TAG, "onResponse: we have recived data "+response);
//                        adapter=new ListSourceAdapter(getBaseContext(),response.body());
//                        adapter.notifyDataSetChanged();
//                        sourcelist.setAdapter(adapter);
//
//                        // we have to save the data in cache
//                        Paper.book().write("cache",new Gson().toJson(response.body()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<SourceModel> call, Throwable t) {
//
//                    }
//                });
//            }
//        }
//
//        else{
            // if from swipe to refresh
            alertDialog.show();
            mNewsServce.getSources(API_KEY).enqueue(new Callback<SourceModel>() {
                @Override
                public void onResponse(Call<SourceModel> call, Response<SourceModel> response) {
                    Log.d(TAG, "onResponse: we have recived data "+response);
                    adapter=new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    sourcelist.setAdapter(adapter);

                    // we have to save the data in cache
                  //  Paper.book().write("cache",new Gson().toJson(response.body()));
                    alertDialog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<SourceModel> call, Throwable t) {
                    Log.d(TAG, "onFailure: recived no data");

                }
            });
        }

    }

//}
