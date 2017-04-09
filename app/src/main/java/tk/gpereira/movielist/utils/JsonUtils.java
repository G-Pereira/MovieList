package tk.gpereira.movielist.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static void parse(ArrayList<String> movies, String resultsJSONString) throws JSONException {
        JSONObject mJSON = new JSONObject(resultsJSONString);
        String RESULTS_ARRAY_STRING = "results";
        if (!mJSON.has(RESULTS_ARRAY_STRING)) {
            return;
        }
        JSONArray movieArray = mJSON.getJSONArray("results");
        for (int i = 0; i < movieArray.length(); i++) {
            String POSTER_STRING = "poster_path";
            String BASE_URL = "http://image.tmdb.org/t/p/w185/";
            movies.add(BASE_URL + movieArray.getJSONObject(i).getString(POSTER_STRING));
        }
    }
}
