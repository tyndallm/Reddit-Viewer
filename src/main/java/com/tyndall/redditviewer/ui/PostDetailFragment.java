package com.tyndall.redditviewer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.tyndall.R;
import com.tyndall.redditviewer.core.Constants;
import com.tyndall.redditviewer.models.RedditPost;

/**
 * Shows Reddit post link in webview
 */
public class PostDetailFragment extends Fragment {

    private RedditPost mPost;
    private WebView mWebview;

    public PostDetailFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_fragment, null);

        Intent intent = getActivity().getIntent();
        mPost = intent.getParcelableExtra(Constants.Reddit.REDDIT_POST_PARCELABLE_IDENTIFIER);

        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(mPost.getTitle());

        mWebview = (WebView) view.findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.loadUrl(mPost.getUrl());

        return view;
    }

    public void updateUrl(String url) {
        mWebview.loadUrl(url);
    }
}
