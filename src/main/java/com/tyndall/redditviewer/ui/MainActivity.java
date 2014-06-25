package com.tyndall.redditviewer.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.tyndall.R;

/**
 * Main Activity is responsible for loading the main list view fragment as well as detail views
 */
public class MainActivity extends ActionBarActivity {

    FrontpageFragment mFrontpageFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Frontpage");

        FragmentManager fm = getSupportFragmentManager();
        mFrontpageFragment = (FrontpageFragment) fm.findFragmentById(R.id.frontpageFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // notify fragment it needs to refresh
                mFrontpageFragment.refresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
