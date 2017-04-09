package tk.gpereira.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<String> mMovies;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ViewHolder(view);
    }

    MovieAdapter(ArrayList<String> movies) {
        mMovies = movies;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
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
            Picasso.with(itemView.getContext()).load(mMovies.get(position)).into(listItemView);
        }

    }

}
