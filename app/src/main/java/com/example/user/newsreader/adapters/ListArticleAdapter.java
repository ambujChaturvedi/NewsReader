package com.example.user.newsreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.newsreader.R;
import com.example.user.newsreader.ReadWebpage;
import com.example.user.newsreader.model2.ArticleModel;
import com.example.user.newsreader.services.MyItemClickListner;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 09-02-2018.
 */

public class ListArticleAdapter extends RecyclerView.Adapter<ListArticleAdapter.ListArticleviewHolder> {

    Context context;
    ArticleModel articleModel;


    public ListArticleAdapter(Context context, ArticleModel articleModel) {
        this.context = context;
        this.articleModel = articleModel;
    }

    @Override
    public ListArticleviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.articleheadlinelayout, parent, false);
        return new ListArticleviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListArticleviewHolder holder, final int position) {

        holder.title.setText(articleModel.getArticles().get(position).getTitle());
        if(articleModel.getArticles().get(position).getDescription().length()>65){
            holder.Description.setText(articleModel.getArticles().get(position).getDescription().substring(0,65)+".....");

        }
        else{
        holder.Description.setText(articleModel.getArticles().get(position).getDescription());}

        Picasso.with(context).load(articleModel.getArticles().get(position).getUrlToImage()).placeholder(R.drawable.ic_launcher_background)
                .into(holder.title_image);
        holder.setItemClickListner(new MyItemClickListner() {
            @Override
            public void onclick(View view, int postion, boolean isLongClick) {
                 Intent intent=new Intent(context, ReadWebpage.class);
                 intent.putExtra("weburl",articleModel.getArticles().get(position).getUrl());
                 context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleModel.getArticles().size();
    }

    public class ListArticleviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MyItemClickListner itemClickListner;
        TextView title;
        TextView Description;
        ImageView title_image;

        public void setItemClickListner(MyItemClickListner itemClickListner) {
            this.itemClickListner = itemClickListner;
        }

        public ListArticleviewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_news);
            Description = (TextView) itemView.findViewById(R.id.description);
            title_image = (ImageView) itemView.findViewById(R.id.post_image);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            itemClickListner.onclick(view, getAdapterPosition(), false);
        }
    }

}
