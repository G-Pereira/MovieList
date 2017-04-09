package tk.gpereira.movielist.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tk.gpereira.movielist.Movie;

public class JsonUtils {

    public static void parse(ArrayList<Movie> movies, String resultsJSONString) throws JSONException {
        JSONObject mJSON = new JSONObject(resultsJSONString);
        String RESULTS_ARRAY_STRING = "results";
        if (!mJSON.has(RESULTS_ARRAY_STRING)) {
            return;
        }
        JSONArray movieArray = mJSON.getJSONArray("results");
        Log.i("DATA", movieArray.getJSONObject(0).toString(1));
        for (int i = 0; i < movieArray.length(); i++) {
            Movie movie = new Movie();
            movie.setPoster("http://image.tmdb.org/t/p/w185/" + movieArray.getJSONObject(i).getString("poster_path"));
            movie.setDescription(movieArray.getJSONObject(i).getString("overview"));
            movie.setTitle(movieArray.getJSONObject(i).getString("original_title"));
            movie.setDate(movieArray.getJSONObject(i).getString("release_date").substring(0, 4));
            movie.setRating(movieArray.getJSONObject(i).getString("vote_average"));
            movies.add(movie);
        }
    }
}
