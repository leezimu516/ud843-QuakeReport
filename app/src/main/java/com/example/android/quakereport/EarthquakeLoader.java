package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/*
    * Loader
    * */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

	/** Query URL */
	private String mUrl;

	/** Tag for log messages */
	private static final String LOG_TAG = EarthquakeLoader.class.getName();

	public EarthquakeLoader(Context context, String url) {
		super(context);
		mUrl = url;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	public List<Earthquake> loadInBackground() {
		Log.d(LOG_TAG, "TEST: loadInBackground");
		// Don't perform the request if there are no URLs, or the first URL is null.
		if (mUrl == null) {
			return null;
		}

		// Perform the HTTP request for earthquake data and process the response.
		Log.d(LOG_TAG, "TEST: fetch the data");
		List<Earthquake> earthquakes = Utils.fetchEarthquakeData(mUrl);
		return earthquakes;
	}
}