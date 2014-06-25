package com.tyndall.redditviewer.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader;

/**
 * Bitmap Memory (LRU) Cache
 */
public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache{

    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        // The cache size will be measured in bytes rather than number of items.
        return value.getByteCount();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);

    }
}
