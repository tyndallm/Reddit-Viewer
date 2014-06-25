package com.tyndall.redditviewer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tyndall.R;
import com.tyndall.redditviewer.models.RedditPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * PostAdapter is responsible for displaying RedditPosts in the Frontpage List View
 */
public class PostAdapter extends ArrayAdapter<RedditPost> {
    private static final String TAG = PostAdapter.class.getSimpleName();

    private ImageLoader mImageLoader;
    private ArrayList<RedditPost> mRedditPosts = new ArrayList<RedditPost>();
    private Context mContext;

    public PostAdapter(Context context, ImageLoader loader){
        super(context, R.layout.post_row_layout);
        mContext = context;
        mImageLoader = loader;
    }

    @Override
    public int getCount() {
        return mRedditPosts.size();
    }

    @Override
    public RedditPost getItem(int position) {
        return mRedditPosts.get(position);
    }

    @Override
    public void add(RedditPost post) {
        mRedditPosts.add(post);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_row_layout, parent, false);

            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.subredditView = (TextView) convertView.findViewById(R.id.subreddit);
            holder.authorView = (TextView) convertView.findViewById(R.id.author);
            holder.thumbnailView = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        RedditPost post = getItem(position);
        holder.titleView.setText(post.getTitle());
        holder.subredditView.setText("r/" + post.getSubreddit());
        holder.authorView.setText("by " + post.getAuthor());

        if(post.getThumbnail().startsWith("http")){
            holder.thumbnailView.setVisibility(View.VISIBLE);
            holder.thumbnailView.setImageUrl(post.getThumbnail(), mImageLoader);

        }
        else{
            holder.thumbnailView.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void loadContent(JSONObject response) {
        mRedditPosts.clear();

        try {
            JSONObject jsonData = response.getJSONObject("data");

            JSONArray children = jsonData.getJSONArray("children");

            int count = children.length();
            for(int i = 0; i < count; i++) {
                JSONObject json = children.getJSONObject(i);

                JSONObject jsonPostData = json.getJSONObject("data");

                RedditPost post = new RedditPost();
                // TODO convert Strings to Constants
                post.setTitle(jsonPostData.getString("title"));
                post.setThumbnail(jsonPostData.optString("thumbnail", ""));
                post.setAuthor(jsonPostData.getString("author"));
                post.setPermalink(jsonPostData.getString("permalink"));
                post.setAuthor(jsonPostData.getString("author"));
                post.setUrl(jsonPostData.getString("url"));
                post.setSubreddit(jsonPostData.getString("subreddit"));

                String typePrefix = json.getString("kind");
                post.setType(typePrefix);

                mRedditPosts.add(post);

            }
        }
        catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON response:", e);
        }

        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private TextView titleView;
        private TextView subredditView;
        private TextView authorView;
        private NetworkImageView thumbnailView;
    }


}
