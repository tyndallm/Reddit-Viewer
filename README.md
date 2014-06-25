# Reddit-Viewer

Reddit-Viewer is a simple app that fetches the stories making up the frontpage of reddit.com and displays them in a list view.

The main screen of the application is the FrontpageFragment which contains the listview containing all of the posts.  Each row in the list view displays a thumbnail if available, the post title, subreddit, and author.  Each item in the ListView is managed by the PostAdapter which is responsible for taking the raw JSON response, converting it to RedditPost models and updating each view as its called from the List View.  It uses the ViewHolder pattern to prevent having to call findViewById over and over which can be expensive.

When a list item is clicked it then Parcels the RedditPost model into an intent which launches another activity and fragment view displaying the title, subreddit and a webview of the actual Reddit post link.   

RedditViewer uses the Google Library Volley to handle the network requests and image downloading.  The application can statically refer to and add requests to the Volley RequestQueue which handle the asynchronous downloading of data.  The app also makes use of a MemoryCache which helps to speed up the app by Lazy loading images and caching them as you scroll through the list view.
* The library included in the build.gradle is an official Volley repository

The app uses Robolectric for unit tests and originates from the Deckard-gradle project

Feel free to email me if you have any questions! mattctyndall@gmail.com  


