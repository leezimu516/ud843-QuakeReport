/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&" +
            "eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "TEST: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView)findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_textView);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);

        // Loader
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
        Log.d(LOG_TAG, "TEST: initLoader");

    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "TEST: onCreateLoader");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        Log.d(LOG_TAG, "TEST: onLoadFinished");

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText(R.string.no_earthquakes);

        // If there is no result, do nothing.
        if (earthquakes == null) {
            return;
        }

        // Update the information displayed to the user.
        updateUi(earthquakes);
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.d(LOG_TAG, "TEST: onLoaderReset");
        updateUi(new ArrayList<Earthquake>());

    }




    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(List<Earthquake> earthquakes) {
        // Create a new {@link EarthquakeAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);


        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }


}
