package tk.gpereira.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDescription extends AppCompatActivity {

    ImageView mPosterImageView;
    TextView mDescriptionTextView;
    TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        Intent previousIntent = getIntent();
        mPosterImageView = (ImageView) findViewById(R.id.iv_desc);
        mDescriptionTextView = (TextView) findViewById(R.id.tv_desc);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
        Picasso.with(this).load(previousIntent.getStringExtra("poster")).into(mPosterImageView);
        mDescriptionTextView.setText(previousIntent.getStringExtra("description"));
        mTitleTextView.setText(previousIntent.getStringExtra("title") + " (" + previousIntent.getStringExtra("date") + ") " + previousIntent.getStringExtra("rating") + "/10");
    }

}
