/*
 * Copyright (c) 2013, Yossi Elkrief(MaTriXy)
 * All rights reserved.
*/

package com.matrixy.fragments.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.matrixy.fragments.R;
import com.matrixy.fragments.uifragments.DialogFragmentExample;
import com.matrixy.fragments.uifragments.DialogFragmentExample.ConfirmationDialogFragmentListener;
import com.matrixy.fragments.uifragments.SampleViewerFragment;

public class SampleViewerActivity extends SherlockFragmentActivity implements ConfirmationDialogFragmentListener
{
	SampleViewerFragment mViewer;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.sample_fragment_layout);
        initActionBar();
        Intent launchingIntent = getIntent();
        String content = launchingIntent.getData().toString();
        mViewer= (SampleViewerFragment) getSupportFragmentManager().findFragmentById(R.id.sampleview_fragment);
        mViewer.updateUrl(content);
        
        
    }

	private void initActionBar()
	{
		ActionBar ab = getSupportActionBar();
		if (ab!=null)
		{
			ab.setDisplayHomeAsUpEnabled(true);
			
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
      switch (item.getItemId()) 
      {
      case android.R.id.home:
    	  finish();
          return false;
      case R.id.action_settings:
    	  showEditDialog();
    	  return false;
       default:
		return super.onOptionsItemSelected(item);
      }
	}

	
	private void showEditDialog()
	{
		FragmentManager fm = getSupportFragmentManager();
		DialogFragmentExample editNameDialog = new DialogFragmentExample();
		editNameDialog.setConfirmationDialogFragmentListener(this);
		editNameDialog.show(fm, "fragment_edit_name");
	}
	
	@Override
	public void onPositiveClick(String url) 
	{
		mViewer.updateUrl(url);
	}

	@Override
	public void onNegativeClick(DialogInterface dialog) 
	{
		dialog.dismiss();
	}
	


	
	
	
}
