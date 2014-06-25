package com.tyndall.redditviewer.util;

import android.app.ActivityManager;
import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Responsible for managing Volley Request Queue, also contains memory cache for images
 */
public class VolleyUtils {

    private static RequestQueue mRequestQueue;
    private static BitmapLruCache mMemoryCache;

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        ActivityManager mgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // Get memory class of the device, try using 1/4th of the available memory for this memory cache
        // to prevent OutOfMemory exceptions
        mMemoryCache = new BitmapLruCache(mgr.getMemoryClass() / 4 * 1024 * 1024);
    }

    public static RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            throw new IllegalArgumentException("mRequestQueue is null, did you call init?");
        }
        return mRequestQueue;
    }

    public static BitmapLruCache getMemoryCache() {
        if (mMemoryCache == null) {
            throw new IllegalArgumentException("mMemoryCache is null, did you call init?");
        }
        return mMemoryCache;
    }


}
