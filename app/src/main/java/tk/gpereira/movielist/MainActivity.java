package tk.gpereira.movielist;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import tk.gpereira.movielist.utils.JsonUtils;
import tk.gpereira.movielist.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;

    public ArrayList<String> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mMovieList = (RecyclerView) findViewById(R.id.rv_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(movies);
        mMovieList.setAdapter(movieAdapter);
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.refresh){
            refreshData();
        }
        return super.onOptionsItemSelected(item);
    }

    void refreshData(){
        String sortParameter = "release_date.desc";
        URL tmdbUrl = NetworkUtils.buildUrl(sortParameter);
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        new MovieQueryTask().execute(tmdbUrl);
    }

    private class MovieQueryTask extends AsyncTask<URL, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieResults = null;
            ArrayList<String> movies = new ArrayList<>();
            try {
                movieResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (movieResults != null && !movieResults.equals("")) {
                try {
                    JsonUtils.parse(movies, movieResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<String> results) {
            movies.clear();
            movies.addAll(results);
            movieAdapter.notifyDataSetChanged();
        }
    }
}