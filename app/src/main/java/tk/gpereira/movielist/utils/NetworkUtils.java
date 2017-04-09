package tk.gpereira.movielist.utils;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    private final static String TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/popular";

    private final static String PARAM_API = "api_key";

    private final static String PARAM_SORT = "sort_by";

    /**
     * Builds the URL used to query TMDB.
     *
     * @param tmdbSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the GitHub.
     */
    public static URL buildUrl(String tmdbSearchQuery, String API_KEY) {
        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API, API_KEY)
                .appendQueryParameter(PARAM_SORT, tmdbSearchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}