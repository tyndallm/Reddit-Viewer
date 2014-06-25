package com.tyndall;

import android.app.Application;
import com.tyndall.redditviewer.util.VolleyUtils;

public class RedditViewerApplication extends Application {

    private static RedditViewerApplication instance;

    public RedditViewerApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        VolleyUtils.init(instance);
    }


    public static RedditViewerApplication getInstance() {
        return instance;
    }



}
