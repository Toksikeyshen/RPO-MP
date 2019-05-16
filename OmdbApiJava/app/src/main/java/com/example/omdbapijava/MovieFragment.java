package com.example.omdbapijava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieFragment extends Fragment {
    private ImageView poster;
    private TextView title, type, year;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.movie, container, false);
        poster = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.movie_title);
        type = view.findViewById(R.id.movie_type);

        assert this.getArguments() != null;
        String poster1 = this.getArguments().getString("Poster");
        String movieTitle = this.getArguments().getString("Title");
        String movieType = this.getArguments().getString("Type");
        Picasso.with(getContext()).load(poster1).fit().centerInside().into(poster);

        title.setText("Title: " + movieTitle);
        type.setText("Type: " + movieType);


        return view;
    }
}
