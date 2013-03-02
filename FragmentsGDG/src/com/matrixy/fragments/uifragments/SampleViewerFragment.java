/*
 * Copyright (c) 2013, Yossi Elkrief(MaTriXy)
 * All rights reserved.
*/

package com.matrixy.fragments.uifragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.matrixy.fragments.R;

public class SampleViewerFragment extends Fragment {
    private WebView mViewer = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
    {
    	mViewer = (WebView) inflater.inflate(R.layout.sample_viewer_layout, container, false);
    	mViewer.setWebViewClient(new MyWebViewClient());
    	mViewer.getSettings().setBuiltInZoomControls(false); 
    	mViewer.getSettings().setSupportZoom(false);
    	mViewer.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   
    	mViewer.getSettings().setAllowFileAccess(true); 
    	mViewer.getSettings().setDomStorageEnabled(true);
        return mViewer;
    }

    public void updateUrl(String newUrl) {
        if (mViewer != null) {
        	mViewer.loadUrl(newUrl);
        }
    }
    
    
    
    public class MyWebViewClient extends WebViewClient 
    {        
    	
        @Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminate(true);
			((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(true);
		}

        
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminate(false);
			((SherlockFragmentActivity) getActivity()).setSupportProgressBarIndeterminateVisibility(false);
		}


		@Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".mp4")) //this is to show video files in a player and not inside a webview
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "video/*");

                view.getContext().startActivity(intent);
                return true;
            } 
            else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
        
        
    }
    
}
