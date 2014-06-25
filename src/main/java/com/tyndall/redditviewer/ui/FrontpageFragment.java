package com.tyndall.redditviewer.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tyndall.R;
import com.tyndall.redditviewer.adapter.PostAdapter;
import com.tyndall.redditviewer.core.Constants;
import com.tyndall.redditviewer.models.RedditPost;
import com.tyndall.redditviewer.util.VolleyUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This fragment is responsible for displaying a list view featuring the front page posts from Reddit.com
 */
public class FrontpageFragment extends Fragment implements AdapterView.OnItemClickListener{
    private static final String TAG = FrontpageFragment.class.getSimpleName();

    private ImageLoader mImageLoader;
    private PostAdapter mPostAdapter;
    private ListView mFrontpageListView;
    private ProgressBar mProgressIndicator;

    public FrontpageFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageLoader = new ImageLoader(VolleyUtils.getRequestQueue(), VolleyUtils.getMemoryCache());
        mPostAdapter = new PostAdapter(getActivity(), mImageLoader);

    }

    public void refresh(){
        mProgressIndicator.setVisibility(View.VISIBLE);
        mFrontpageListView.setVisibility(View.GONE);

        String FRONTPAGE_URL = Constants.Reddit.REDDIT_BASE_URL + Constants.Reddit.REDDIT_FRONTPAGE_JSON_URL;
        JsonObjectRequest req = new JsonObjectRequest(FRONTPAGE_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                            mPostAdapter.loadContent(response);
                            mFrontpageListView.setVisibility(View.VISIBLE);
                            mProgressIndicator.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            Log.v(TAG, e.getMessage().toString());
                        }

                    }

                } ,new Response.ErrorListener() {

            @Override
            public void onErrorResponse (VolleyError error){
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        req.setTag(this);
        VolleyUtils.getRequestQueue().add(req);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frontpage_fragment, null);

        mProgressIndicator = (ProgressBar) view.findViewById(R.id.pb_loading);
        mFrontpageListView = (ListView) view.findViewById(R.id.frontpageListView);
        mFrontpageListView.setAdapter(mPostAdapter);
        mFrontpageListView.setOnItemClickListener(this);

        refresh();

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RedditPost post = mPostAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra(Constants.Reddit.REDDIT_POST_PARCELABLE_IDENTIFIER, post);
        startActivity(intent);

    }
}
