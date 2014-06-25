package com.tyndall.redditviewer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import com.tyndall.R;
import com.tyndall.redditviewer.core.Constants;
import com.tyndall.redditviewer.models.RedditPost;

/**
 * Shows post detail view fragment
 */
public class PostDetailActivity extends ActionBarActivity{

    private RedditPost mPost;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_detail_activity);

        Intent intent = getIntent();
        mPost = intent.getParcelableExtra(Constants.Reddit.REDDIT_POST_PARCELABLE_IDENTIFIER);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("r/" + mPost.getSubreddit());


    }
}
