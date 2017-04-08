package tk.gpereira.movielist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import tk.gpereira.movielist.utils.JsonUtils;
import tk.gpereira.movielist.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 5;
    MovieAdapter movieAdapter;
    private RecyclerView mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(NUM_LIST_ITEMS);
        mMovieList.setAdapter(movieAdapter);
    }
}