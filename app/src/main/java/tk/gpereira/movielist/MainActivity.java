package tk.gpereira.movielist;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieClickListener {

    MovieAdapter movieAdapter;
    RecyclerView mMovieList;

    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = (RecyclerView) findViewById(R.id.rv_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mMovieList.setLayoutManager(layoutManager);
        mMovieList.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(movies, this);
        mMovieList.setAdapter(movieAdapter);
        refreshData();
    }

    @Override
    public void onMovieClick(int clickedMovieIndex) {
        Intent intent = new Intent(MainActivity.this, MovieDescription.class);
        intent.putExtra("poster", movies.get(clickedMovieIndex).getPoster());
        intent.putExtra("title", movies.get(clickedMovieIndex).getTitle());
        intent.putExtra("description", movies.get(clickedMovieIndex).getDescription());
        intent.putExtra("date", movies.get(clickedMovieIndex).getDate());
        intent.putExtra("rating", movies.get(clickedMovieIndex).getRating());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            refreshData();
        }
        return super.onOptionsItemSelected(item);
    }

    void refreshData() {
        String sortParameter = "release_date.desc";
        URL tmdbUrl = NetworkUtils.buildUrl(sortParameter, getString(R.string.tmdb_api_key));
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        new MovieQueryTask().execute(tmdbUrl);
    }

    private class MovieQueryTask extends AsyncTask<URL, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieResults = null;
            ArrayList<Movie> movies = new ArrayList<>();
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
        protected void onPostExecute(ArrayList<Movie> results) {
            movies.clear();
            movies.addAll(results);
            movieAdapter.notifyDataSetChanged();
        }
    }
}