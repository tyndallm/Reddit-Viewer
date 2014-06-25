package com.tyndall.redditviewer.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Robolectric unit tests for PostDetailFragment.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class PostDetailFragmentRobolectricTest {

    /**
     * Helper function used for testing fragment independently of Activity
     * @param fragment
     */
    public static void startFragment(Fragment fragment) {
        FragmentActivity activity = Robolectric.buildActivity(FragmentActivity.class).create().start().resume().get();

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(fragment, null);
        transaction.commit();
    }

    @Test
    public void testFragmentClassExists() {
        PostDetailFragment detailFragment = new PostDetailFragment();
        assert(detailFragment != null);

    }
}
