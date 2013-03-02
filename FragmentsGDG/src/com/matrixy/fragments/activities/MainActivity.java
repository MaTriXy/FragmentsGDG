/*
 * Copyright (c) 2013, Yossi Elkrief(MaTriXy)
 * All rights reserved.
 */

package com.matrixy.fragments.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Window;
import com.matrixy.fragments.R;
import com.matrixy.fragments.uifragments.SampleListFragment;
import com.matrixy.fragments.uifragments.SampleViewerFragment;

public class MainActivity extends SherlockFragmentActivity implements SampleListFragment.OnItemSelectedListener {
	
    private ActionBar mActionBar;
    

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	    setContentView(R.layout.activity_fragments_via_layout);
		initActionBar();
	}

	private void initActionBar() 
	{
		mActionBar=getSupportActionBar();
		if (mActionBar!=null)
		{
			
		}
	}
	
	
	@Override
	public void onListItemSelected(String url) 
	{
		SampleViewerFragment viewer = (SampleViewerFragment) getSupportFragmentManager().findFragmentById(R.id.SampleView_fragment);
		if (viewer == null || !viewer.isInLayout()) 
		{
			Intent showContent = new Intent(getApplicationContext(),SampleViewerActivity.class);
			showContent.setData(Uri.parse(url));
			startActivity(showContent);
		} else {
			viewer.updateUrl(url);
		}
	}

	
}