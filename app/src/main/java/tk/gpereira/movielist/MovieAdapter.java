package tk.gpereira.movielist;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.net.Uri;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import tk.gpereira.movielist.utils.JsonUtils;
import tk.gpereira.movielist.utils.NetworkUtils;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private int mNumberItems;
    private ArrayList<String> movies = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ViewHolder(view);
    }

    MovieAdapter(int numberOfItems){
        mNumberItems = numberOfItems;
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView listItemView;
        private ViewHolder(View itemView) {
            super(itemView);
            listItemView = (ImageView) itemView.findViewById(R.id.iv_item);
        }
        void bind(int position) {
            String sortParameter = "release_date.desc";
            URL tmdbUrl = NetworkUtils.buildUrl(sortParameter);
            new MovieQueryTask().execute(tmdbUrl);
        }
        private class MovieQueryTask extends AsyncTask<URL, Void, String> {

            @Override
            protected String doInBackground(URL... urls) {
                URL searchUrl = urls[0];
                String movieResults = null;
                try{
                    movieResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                } catch (IOException e){
                    e.printStackTrace();
                }
                return movieResults;
            }

            @Override
            protected void onPostExecute(String resultsJSONString) {
                if(resultsJSONString != null && !resultsJSONString.equals("")){
                    try {
                        JsonUtils.parse(movies, resultsJSONString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Picasso.with(itemView.getContext()).load(movies.get(1)).into(listItemView);
                }
            }
        }
    }

}
