package com.tyndall.redditviewer.adapter;

import android.content.Context;
import com.android.volley.toolbox.ImageLoader;
import com.tyndall.redditviewer.util.VolleyUtils;
import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

/**
 * Robolectric unit test for PostAdapter.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class PostAdapterRobolectricTest {

    private String URL = "http://www.reddit.com/.json";
    private PostAdapter mPostAdapter;
    private ImageLoader mImageLoader;


    /**
     * Helper function, we will attempt to load a saved json test file from assets with the same data as if we made the actual
     * network connection
     */
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = Robolectric.getShadowApplication().getAssets().open("frontpage_test.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Before
    public void setUp(){
        Context context = Robolectric.getShadowApplication().getApplicationContext();
        VolleyUtils.init(context);
        mImageLoader = mImageLoader = new ImageLoader(VolleyUtils.getRequestQueue(), VolleyUtils.getMemoryCache());
        mPostAdapter = new PostAdapter(context, mImageLoader);
    }

    /**
     * Post Adapter should be initially empty
     */
    @Test
    public void testPostAdapterIsEmpty() {
        final int EXPECTED_COUNT = 0;
        Assert.assertEquals(mPostAdapter.getCount(), EXPECTED_COUNT);
    }

    /**
     * We will try loading a JSONObject that represents a reddit frontpage
     * There should be 25 RedditPosts populated after loadContent is called
     */
    @Test
    public void testLoadContent() {
        final int EXPECTED_COUNT = 25;
        try{
            JSONObject rawJson = new JSONObject(loadJSONFromAsset());
            mPostAdapter.loadContent(rawJson);
        }
        catch(JSONException e){
            System.out.println("Error parsing JSON: " + e.getMessage());
        }

        Assert.assertEquals(mPostAdapter.getCount(), EXPECTED_COUNT);
    }

}
