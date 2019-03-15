package com.example.omdbapijava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private ArrayList<Item> MovieList = new ArrayList<>();
    private RequestQueue requestQueue;
    public MovieDatabase movieDatabase;
    public MovieDao movieDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDao = MovieDatabase.getDatabase(getApplicationContext()).movieDAO();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
        Button search = findViewById(R.id.serch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(!MovieList.isEmpty()){
                    MovieList.clear();
                }*/
                Log.i("llll", MovieList.toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        movieDao.insertAll(MovieList);
                    }
                }).start();
                //parseJSON();
            }
        });
    }


    private void parseJSON() {
        EditText editText = findViewById(R.id.movie);
        String movie = editText.getText().toString();

        // String url = "http://www.omdbapi.com/?apikey=d2fe627a&s=" + movie +"%22";
        String url = "http://www.omdbapi.com/?apikey=d2fe627a&s=ave";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Search");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mov = jsonArray.getJSONObject(i);

                                String imageUrl = mov.getString("Poster");
                                String MovieTitle = mov.getString("Title");
                                String MovieType = mov.getString("Type");

                                MovieList.add(new Item(imageUrl, MovieTitle, MovieType));
//                                Log.i("llll", MovieList.toString());
//                                movieDao.insertAll(new Item(imageUrl, MovieTitle, MovieType));
                            }

                            mAdapter = new Adapter(MainActivity.this, MovieList);
                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Item> dbMovieList = movieDao.getAllMovies();
                        mAdapter = new Adapter(MainActivity.this, dbMovieList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }).start();
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}
