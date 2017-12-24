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
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
//        ArrayList<Earthquake> earthquakes = new ArrayList<>();
//        earthquakes.add(new Earthquake("1.1", "San Francisco", "2013.2.2"));
//        earthquakes.add(new Earthquake("1.1", "London", "2013.2.3"));
//        earthquakes.add(new Earthquake("1.1", "Tokyo", "2013.2.4"));
//        earthquakes.add(new Earthquake("1.1", "Mexico City", "2013.2.5"));
//        earthquakes.add(new Earthquake("1.1", "Moscow", "2013.2.5"));
//        earthquakes.add(new Earthquake("1.1", "Rio de Janeiro", "2013.2.6"));
//        earthquakes.add(new Earthquake("1.1", "Paris", "2013.2.7"));

        // get the list of earthquake form {@link QueryUtils}
        //ArrayList<Earthquake> earthquakes = QueryUtils.extractFeatureFromJson();


        //new EarthquakeAsyncTask().execute(USGS_REQUEST_URL);

        // Loader
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        // If there is no result, do nothing.
        if (earthquakes == null) {
            return;
        }

        // Update the information displayed to the user.
        updateUi(earthquakes);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
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
