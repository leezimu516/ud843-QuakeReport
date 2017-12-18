package com.example.android.quakereport;

/**
 * Created by kaili on 2017-12-16.
 */

public class Earthquake {
	// level of earthquake
	private String mLevel;
	// location
	private String mLocation;
	// date
	private String mDate;

	/* Create new Earthquake object
	* @param mEarchquakeLevel
	* @param mLocation
	* @param mDate
	* */
	public Earthquake(String level, String location, String date) {
		mLevel = level;
		mLocation = location;
		mDate = date;
	}

	/*
	* get the earthquake level
	* */
	public String getLevel() {
		return mLevel;
	}

	/*
	get earthquake location
	*/
	public String getLocation() {
		return mLocation;
	}

	/*
	* get earthquake date
	* */
	public String getDate() {
		return mDate;
	}


}
