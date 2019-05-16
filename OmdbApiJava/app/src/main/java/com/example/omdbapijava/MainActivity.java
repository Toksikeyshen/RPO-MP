package com.example.omdbapijava;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Fragment fragment = new RecyclerviewFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment ,fragment.getClass().getSimpleName()).commit();
    }

}
