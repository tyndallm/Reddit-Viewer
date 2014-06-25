package com.tyndall.redditviewer.models;

import android.os.Parcel;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Robolectric unit tests for RedditPost.class
 */
@Config(emulateSdk = 18) // Robolectric doesn't support API 19 yet
@RunWith(RobolectricTestRunner.class)
public class RedditPostRobolectricTest {

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

    @Test
    public void testClassExists() {
        RedditPost post = new RedditPost();
        assert(post != null);
    }

    /**
     * Test that our parcelable implementation works correctly, its easy to mess up ordering of fields,
     * this will catch any issues related to that
     */
    @Test
    public void testParcelableWorks() {
        RedditPost testPost = createTestPost();

        // Obtain a Parcel object and write the parcelable RedditPost to it:
        Parcel parcel = Parcel.obtain();
        testPost.writeToParcel(parcel, 0);

        // After done writing, reset the parcel for reading:
        parcel.setDataPosition(0);

        // Reconstruct object from parcel
        RedditPost createdPostFromParcel = RedditPost.CREATOR.createFromParcel(parcel);

        // Test Parcel mapping is correct
        Assert.assertEquals(createdPostFromParcel.getTitle(), testPost.getTitle());
        Assert.assertEquals(createdPostFromParcel.getAuthor(), testPost.getAuthor());
        Assert.assertEquals(createdPostFromParcel.getSubreddit(), testPost.getSubreddit());
        Assert.assertEquals(createdPostFromParcel.getPermalink(), testPost.getPermalink());
        Assert.assertEquals(createdPostFromParcel.getThumbnail(), testPost.getThumbnail());
        Assert.assertEquals(createdPostFromParcel.getUrl(), testPost.getUrl());
        Assert.assertEquals(createdPostFromParcel.getType(), testPost.getType());
    }
}
