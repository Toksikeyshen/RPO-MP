package com.example.omdbapijava;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context MovieContext;
    private List<Item> MovieList;

    public Adapter (Context context, List<Item> List){
        MovieContext = context;
        MovieList = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(MovieContext).inflate(R.layout.item,viewGroup, false );
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Item currentItem = MovieList.get(i);

        String imageUrl = currentItem.getImageUrl();
        String title = currentItem.getMovieTitle();
        String movieType = currentItem.getMovieType();

        viewHolder.MovieTitle.setText(title);
        viewHolder.MovieType.setText(movieType);
        Picasso.with(MovieContext).load(imageUrl).fit().centerInside().into(viewHolder.ImageUrl);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Title", currentItem.getMovieTitle());
                bundle.putString("Type", currentItem.getMovieType());
                bundle.putString("Poster", currentItem.getImageUrl());

                MovieFragment movieFragment = new MovieFragment();
                movieFragment.setArguments(bundle);
                ((MainActivity) MovieContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, movieFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ImageUrl;
        TextView MovieTitle;
        TextView MovieType;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageUrl = itemView.findViewById(R.id.imageView);
            MovieTitle = itemView.findViewById(R.id.title);
            MovieType = itemView.findViewById(R.id.type);
        }
    }
}
