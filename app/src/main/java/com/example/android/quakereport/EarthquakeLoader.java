package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/*
    * Loader
    * */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

	/** Query URL */
	private String mUrl;

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
		// Don't perform the request if there are no URLs, or the first URL is null.
		if (mUrl == null) {
			return null;
		}

		// Perform the HTTP request for earthquake data and process the response.
		List<Earthquake> earthquakes = Utils.fetchEarthquakeData(mUrl);
		return earthquakes;
	}
}