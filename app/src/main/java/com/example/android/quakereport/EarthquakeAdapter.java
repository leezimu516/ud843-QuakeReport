package com.example.android.quakereport;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kaili on 2017-12-16.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

	private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

	/**
	 * This is our own custom constructor (it doesn't mirror a superclass constructor).
	 * The context is used to inflate the layout file, and the list is the data we want
	 * to populate into the lists.
	 *
	 * @param context        The current context. Used to inflate the layout file.
	 * @param earthquake A List of AndroidFlavor objects to display in a list
	 */
	public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
		// Here, we initialize the ArrayAdapter's internal storage for the context and the list.
		// the second argument is used when the ArrayAdapter is populating a single TextView.
		// Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
		// going to use this second argument, so it can be any value. Here, we used 0.
		super(context, 0, earthquake);
	}


	/**
	 * Provides a view for an AdapterView (ListView, GridView, etc.)
	 *
	 * @param position The position in the list of data that should be displayed in the
	 *                 list item view.
	 * @param convertView The recycled view to populate.
	 * @param parent The parent ViewGroup that is used for inflation.
	 * @return The View for the position in the AdapterView.
	 */


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Check if the existing view is being reused, otherwise inflate the view
		View listItemView = convertView;
		if (listItemView == null) {
			listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
		}

		// Get the {@link Earthquake} object loacted at this position in the list
		Earthquake currentEarthquake = getItem(position);

		// Find the TextView in the list_view.xml
		TextView levelTextView = (TextView)listItemView.findViewById(R.id.level);
		// Get the level of current Earthquake object and set the level to the textview
		levelTextView.setText(currentEarthquake.getLevel());
		Log.v(LOG_TAG,"level");

		// Find the TextView in the list_view.xml
		TextView locationTextView = (TextView)listItemView.findViewById(R.id.location);
		// Get the location of current Earthquake object and set the level to the textview
		locationTextView.setText(currentEarthquake.getLocation());
		Log.v(LOG_TAG,"location");

		// Find the TextView in the list_view.xml
		TextView dateTextView = (TextView)listItemView.findViewById(R.id.date);
		// Get the date of current Earthquake object and set the level to the textview
		dateTextView.setText(currentEarthquake.getDate());
		Log.v(LOG_TAG,"date");



		return listItemView;
	}
}
