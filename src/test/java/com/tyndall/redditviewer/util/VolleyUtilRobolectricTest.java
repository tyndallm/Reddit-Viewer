package com.tyndall.redditviewer.util;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Robolectric unit tests for VolleyUtils.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class VolleyUtilRobolectricTest {

    @Test
    public void testUnitializedVolleyUtilThrowsException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("mRequestQueue is null, did you call init?");
        try{
            VolleyUtils.getRequestQueue();
        }
        catch (Exception e) {
            // Check message
            Assert.assertTrue(expectedException.getMessage().equals(e.getMessage()));
            // Check exception type
            Assert.assertTrue(expectedException.getClass().getSimpleName().equals(e.getClass().getSimpleName()));
        }
    }

    @Test
    public void testUninitializedmemoryCacheThrowsException() {
        IllegalArgumentException expectedException = new IllegalArgumentException("mMemoryCache is null, did you call init?");
        try {
            VolleyUtils.getMemoryCache();
        }
        catch (Exception e) {
            // Check exception message
            Assert.assertTrue(expectedException.getMessage().equals(e.getMessage()));
            // Check exception type
            Assert.assertTrue(expectedException.getClass().getSimpleName().equals(e.getClass().getSimpleName()));
        }
    }
}
