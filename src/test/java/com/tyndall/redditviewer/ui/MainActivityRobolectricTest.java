package com.tyndall.redditviewer.ui;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import com.tyndall.R;
import com.tyndall.redditviewer.util.VolleyUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.tester.android.view.TestMenuItem;

import static org.junit.Assert.assertTrue;

/**
 * Robolectric unit tests for MainActivity.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class MainActivityRobolectricTest {

    static {
        // redirect the Log.x output to stdout. Stdout will be recorded in the test result report
        ShadowLog.stream = System.out;
    }

    @Test
    public void testDoesntBlowUp() throws Exception {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertTrue(activity != null);
    }

    /**
     * Test that our activity is successfully inflating fragment
     */
    @Test
    public void testFragmentInflates() {
        FragmentActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        FragmentManager fm = activity.getSupportFragmentManager();
        FrontpageFragment fragment = (FrontpageFragment) fm.findFragmentById(R.id.frontpageFragment);
        assertTrue(fragment != null);
    }

    /**
     * Test that our refresh action item will trigger a refresh
     * Not the best test, the idea is that when the refresh menu action is clicked it triggers a refresh which
     * updates the Volley RequestQueue.  We are simply verifying that Request Queue sequence number increased after
     * clicking, thus inferring that this was a success
     */
    @Test
    public void testRefreshActionClicked() {

        MenuItem item = new TestMenuItem() {
            public int getItemId() {
                return R.id.action_refresh;
            }
        };

        VolleyUtils.init(Robolectric.getShadowApplication().getApplicationContext());
        int sequenceBefore = VolleyUtils.getRequestQueue().getSequenceNumber();

        ActionBarActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        activity.onOptionsItemSelected(item);

        int sequenceAfter = VolleyUtils.getRequestQueue().getSequenceNumber();

        Assert.assertTrue(sequenceAfter > sequenceBefore);

    }
}



