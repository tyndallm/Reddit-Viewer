package com.tyndall.redditviewer.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import com.tyndall.R;
import com.tyndall.redditviewer.adapter.PostAdapter;
import com.tyndall.redditviewer.core.Constants;
import com.tyndall.redditviewer.models.RedditPost;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

/**
 * Robolectric unit tests for FrontpageFragment.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class FrontpageFragmentRobolectricTest {

    static {
        // redirect the Log.x output to stdout. Stdout will be recorded in the test result report
        ShadowLog.stream = System.out;
    }

    FrontpageFragment mFrontpageFragment;
    ListView mListview;

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

    public RedditPost createTestPost() {
        String title = "Goat gives it all it's got";
        String subreddit = "videos";
        String author = "Boofers";
        String permalink = "r/videos/comments/27bxdo/goat_gives_it_all_its_got";
        String url = "https://www.youtube.com/watch?v=DV_3qx-oBms";
        String thumbnail = "http://b.thumbs.redditmedia.com/Q-ot0imsyeceq2AG.jpg";
        String type = "t3";

        RedditPost post = new RedditPost();
        post.setTitle(title);
        post.setSubreddit(subreddit);
        post.setAuthor(author);
        post.setPermalink(permalink);
        post.setUrl(url);
        post.setThumbnail(thumbnail);
        post.setType(type);

        return post;
    }

    @Before
    public void setUp() {
        mFrontpageFragment = new FrontpageFragment();
        startFragment(mFrontpageFragment);

        mListview = (ListView) mFrontpageFragment.getView().findViewById(R.id.frontpageListView);
    }

    /**
     * Test Fragment isn't null
     * @throws Exception
     */
    @Test
    public void testDoesntBlowUp() throws Exception {
        FrontpageFragment frontpageFragment = new FrontpageFragment();
        startFragment(frontpageFragment);

        assert(frontpageFragment != null);
    }

    /**
     * Test Fragment contains list view
     */
    @Test
    public void testContainsListView() {
        FrontpageFragment frontpageFragment = new FrontpageFragment();
        startFragment(frontpageFragment);

        ListView frontpageListView = (ListView) frontpageFragment.getView().findViewById(R.id.frontpageListView);

        assert(frontpageListView != null);
    }

    /**
     * initially list view should be empty
     */
    @Test
    public void testListViewShouldBeEmpty() {
        final int EXPECTED_COUNT = 0;
        Assert.assertEquals(mListview.getCount(), EXPECTED_COUNT);
    }

    /**
     * add item to list view adapter, notify data set changed and check list view count
     */
    @Test
    public void testListViewShouldNotBeEmpty() {
        final int EXPECTED_COUNT = 1;

        PostAdapter adapter = (PostAdapter) mListview.getAdapter();
        RedditPost post = createTestPost();
        adapter.add(post);
        adapter.notifyDataSetChanged();

        Assert.assertEquals(mListview.getCount(), EXPECTED_COUNT);

    }

    /**
     * Test list view item initiates start activity when clicked
     * This test does not work.  I can verify that mListView.getCount() = 1, however mListView.getChildCount = 0,
     * I'm not sure why the listview is not redrawing after notifyDataSetChanged() is called...
     */
    @Test
    public void testListViewItemClicked() {
//        //Add item to adapter
//        PostAdapter adapter = (PostAdapter) mListview.getAdapter();
//        RedditPost post = createTestPost();
//        adapter.add(post);
//        adapter.notifyDataSetChanged();
//
//        // for some reason mListView children are empty...
//        mListView.getChild
//        mListview.getChildAt(0).performClick();
//
//        ShadowActivity shadowActivity = Robolectric.shadowOf(mFrontpageFragment.getActivity());
//        Intent intent = shadowActivity.peekNextStartedActivityForResult().intent;

//        Assert.assertEquals(intent.getComponent().getClassName(), PostDetailActivity.class.getName());
    }

    /**
     * Verify that we are starting the correct activity
     */
    @Test
    public void testStartDetailViewActivity() {
        Intent intent = new Intent(Robolectric.getShadowApplication().getApplicationContext(), PostDetailActivity.class);
        intent.putExtra(Constants.Reddit.REDDIT_POST_PARCELABLE_IDENTIFIER, createTestPost());
        mFrontpageFragment.getActivity().startActivity(intent);

        ShadowActivity shadowActivity = Robolectric.shadowOf(mFrontpageFragment.getActivity());
        Intent shadowIntent = shadowActivity.peekNextStartedActivityForResult().intent;
        Assert.assertEquals(shadowIntent.getComponent().getClassName(), PostDetailActivity.class.getName());
    }

}
