package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

	/** Sample JSON response for a USGS query */
	private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
	/**
	 * Create a private constructor because no one should ever create a {@link QueryUtils} object.
	 * This class is only meant to hold static variables and methods, which can be accessed
	 * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
	 */
	private QueryUtils() {
	}

	/**
	 * Return a list of {@link Earthquake} objects that has been built up from
	 * parsing a JSON response.
	 */
	public static ArrayList<Earthquake> extractEarthquakes() {

		// Create an empty ArrayList that we can start adding earthquakes to
		ArrayList<Earthquake> earthquakes = new ArrayList<>();

		// Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
		// is formatted, a JSONException exception object will be thrown.
		// Catch the exception so the app doesn't crash, and print the error message to the logs.
		try {

			// TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
			// build up a list of Earthquake objects with the corresponding data.
			JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
			JSONArray earthquakeJsonArray = root.optJSONArray("features");

			for (int i = 0; i < earthquakeJsonArray.length(); i++) {
				JSONObject currentEarthquake = earthquakeJsonArray.getJSONObject(i);
				JSONObject mag_place_timeObject = currentEarthquake.getJSONObject("properties");
				String mag = mag_place_timeObject.getString("mag");
				String place = mag_place_timeObject.getString("place");
				String time = mag_place_timeObject.getString("time");

				earthquakes.add(new Earthquake(mag, place, time));

			}

		} catch (JSONException e) {
			// If an error is thrown when executing any of the above statements in the "try" block,
			// catch the exception here, so the app doesn't crash. Print a log message
			// with the message from the exception.
			Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
		}

		// Return the list of earthquakes
		return earthquakes;
	}

}
