package tk.gpereira.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import static tk.gpereira.movielist.MainActivity.movies;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    final private MovieClickListener mOnClickListener;

    interface MovieClickListener {
        void onMovieClick(int clickedMovieIndex);
    }

    MovieAdapter(ArrayList<Movie> mMovies, MovieClickListener listener) {
        mOnClickListener = listener;
        movies = mMovies;
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
        return movies.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position).getPoster());
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView listItemView;

        MovieViewHolder(View itemView) {
            super(itemView);
            listItemView = (ImageView) itemView.findViewById(R.id.iv_item);
            itemView.setOnClickListener(this);
        }

        void bind(String movie) {
            Picasso.with(itemView.getContext()).load(movie).into(listItemView);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onMovieClick(getAdapterPosition());
        }
    }
}
