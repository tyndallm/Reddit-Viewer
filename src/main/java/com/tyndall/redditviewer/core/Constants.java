package com.tyndall.redditviewer.core;


public final class Constants {

    private Constants() {}

    public static final class Reddit {
        private Reddit() {}

        /**
         * Reddit base url
         */
        public static final String REDDIT_BASE_URL = "http://www.reddit.com/";

        /**
         * Reddit front page JSON api
         */
        public static final String REDDIT_FRONTPAGE_JSON_URL = ".json";

        /**
         * Reddit Post Parcelable String Identifier
         */
        public static final String REDDIT_POST_PARCELABLE_IDENTIFIER = "REDDIT_POST_PARCELABLE_IDENTIFIER";
    }
}
