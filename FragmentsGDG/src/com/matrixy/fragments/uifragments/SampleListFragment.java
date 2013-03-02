/*
 * Copyright (c) 2013, Yossi Elkrief(MaTriXy)
 * All rights reserved.
 */

package com.matrixy.fragments.uifragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.matrixy.fragments.R;

public class SampleListFragment extends ListFragment {
	private OnItemSelectedListener itemSelectedListener;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) 
	{
		if (!((String) ((TextView)v).getText()).contains("Not"))
		{
			String[] links = getResources().getStringArray(R.array.items_links);
			String content = links[position];
			itemSelectedListener.onListItemSelected(content);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.items_titles,R.layout.list_item));
	}

	public interface OnItemSelectedListener {
		public void onListItemSelected(String Url);
	}

	@Override
	public void onAttach(Activity activity) 
	{
		super.onAttach(activity);
		try 
		{
			itemSelectedListener = (OnItemSelectedListener) activity;
		} 
		catch (ClassCastException e) 
		{
			throw new ClassCastException(activity.toString()+ " must implement OnTutSelectedListener");
		}
	}
}
