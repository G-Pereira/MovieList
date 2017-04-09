package tk.gpereira.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> mMovies;

    final private MovieClickListener mOnClickListener;

    interface MovieClickListener{
        void onMovieClick(int clickedMovieIndex);
    }

    MovieAdapter(ArrayList<Movie> movies, MovieClickListener listener)
    {
        mOnClickListener = listener;
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position).getPoster());
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView listItemView;

        MovieViewHolder(View itemView) {
            super(itemView);
            listItemView = (ImageView) itemView.findViewById(R.id.iv_item);
            itemView.setOnClickListener(this);
        }

        void bind(String movie) {
            Log.i("DATA", movie);
            Picasso.with(itemView.getContext()).load(movie).into(listItemView);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onMovieClick(getAdapterPosition());
        }
    }
}
