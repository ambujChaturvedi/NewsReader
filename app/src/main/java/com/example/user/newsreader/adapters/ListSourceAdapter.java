package com.example.user.newsreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.newsreader.R;
import com.example.user.newsreader.TopheadlinesActivity;
import com.example.user.newsreader.common.Common;
import com.example.user.newsreader.model.IconModel;
import com.example.user.newsreader.model.SourceModel;
import com.example.user.newsreader.model.Source;
import com.example.user.newsreader.services.IconBetterIdeaService;
import com.example.user.newsreader.services.MyItemClickListner;
import com.squareup.picasso.Picasso;
import com.example.user.newsreader.model.Icon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 05-02-2018.
 */

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceAdapter.ListViewHolder> {
    private static final String TAG = "ListSourceAdapter";
    Context context;
    private SourceModel sourceModel;
    IconBetterIdeaService iconBetterIdeaService;

    public ListSourceAdapter(Context context, SourceModel sourceModel) {
        this.context = context;
        this.sourceModel = sourceModel;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.sourcelist,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {

        holder.title.setText(sourceModel.getSources().get(position).getName());


        iconBetterIdeaService= Common.getIconService();
            final StringBuilder apendedUrl=new StringBuilder("https://besticon-demo.herokuapp.com/allicons.json?url=");
        apendedUrl.append(sourceModel.getSources().get(position).getUrl());
        Log.d(TAG, "onBindViewHolder: "+apendedUrl);

        iconBetterIdeaService.getIcon(apendedUrl.toString()).enqueue(new Callback<IconModel>() {
            @Override
            public void onResponse(Call<IconModel> call, Response<IconModel> response) {
                Log.d(TAG, "onResponse: the responce code is "+response);

                if(response.body().getIcons().size()>0) {

                    Picasso.with(context).load(response.body().getIcons().get(0).getUrl())
                            .placeholder(R.drawable.ic_launcher_background).into(holder.icon);
                }

            }

            @Override
            public void onFailure(Call<IconModel> call, Throwable t) {
                Log.d(TAG, "onFailure:  got error reciving imagegs"+t.getMessage());

            }
        });

        holder.setMyItemClickListner(new MyItemClickListner() {
            @Override
            public void onclick(View view, int postion, boolean isLongClick) {
                Intent intent=new Intent(context, TopheadlinesActivity.class);
                intent.putExtra("source",sourceModel.getSources().get(position).getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

            return sourceModel.getSources().size();

        // this fixed the crash null pointer issue but somehow i think we are not geting data
        //return (sourceModel.getSources()==null) ? 0 :sourceModel.getSources().size();

        }


    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MyItemClickListner myItemClickListner;
        TextView title;
        ImageView icon;
        public ListViewHolder(View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            icon=(ImageView) itemView.findViewById(R.id.image_icon);
            itemView.setOnClickListener(this);
        }

        public void setMyItemClickListner(MyItemClickListner myItemClickListner) {
            this.myItemClickListner = myItemClickListner;
        }

        @Override
        public void onClick(View view) {

            myItemClickListner.onclick(view,getAdapterPosition(),false);

        }
    }
}
