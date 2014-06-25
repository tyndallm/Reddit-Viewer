package com.tyndall.redditviewer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Reddit Post object model
 */
public class RedditPost implements Parcelable{

    public static final Parcelable.Creator<RedditPost> CREATOR
            = new Parcelable.Creator<RedditPost>() {
        public RedditPost createFromParcel(Parcel in) {
            return new RedditPost(in);
        }

        public RedditPost[] newArray(int size) {
            return new RedditPost[size];
        }
    };

    private String type;
    private String subreddit;
    private String author;
    private String thumbnail;
    private String permalink;
    private String url;
    private String title;

    public RedditPost() {

    }

    public RedditPost(Parcel in) {
        this.type = in.readString();
        this.subreddit = in.readString();
        this.author = in.readString();
        this.thumbnail = in.readString();
        this.permalink = in.readString();
        this.url = in.readString();
        this.title = in.readString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(type);
        out.writeString(subreddit);
        out.writeString(author);
        out.writeString(thumbnail);
        out.writeString(permalink);
        out.writeString(url);
        out.writeString(title);

    }
}
