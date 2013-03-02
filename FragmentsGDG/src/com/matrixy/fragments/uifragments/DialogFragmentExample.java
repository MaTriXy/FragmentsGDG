package com.matrixy.fragments.uifragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class DialogFragmentExample extends DialogFragment implements DialogInterface.OnClickListener 
{
	private EditText mUrl;
	
	private ConfirmationDialogFragmentListener listener;

	public static DialogFragment newInstance(int title) {
		DialogFragment frag = new DialogFragment();
		Bundle args = new Bundle();
		args.putInt("title", title);
		frag.setArguments(args);
		return frag;
	}

	public interface ConfirmationDialogFragmentListener {
		public void onPositiveClick(String url);

		public void onNegativeClick(DialogInterface dialog);
	}

	public void setConfirmationDialogFragmentListener(ConfirmationDialogFragmentListener listener) 
	{
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		
		mUrl = new EditText(getActivity());

		mUrl.setHint("http://www.google.com");

		return new AlertDialog.Builder(getActivity())
		  .setTitle("Enter Sample URL")
		  .setMessage("Insert a valid url")
		  .setView(mUrl)
		  .setPositiveButton(android.R.string.ok, this)
		  .setNegativeButton(android.R.string.cancel, this).create(); 
	}

	@Override
	public void onClick(DialogInterface dialog, int which) 
	{
		if (listener != null) 
		{
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				if (mUrl!=null) 
					listener.onPositiveClick(mUrl.getText().toString());
			default:
				listener.onNegativeClick(dialog);
			}
		}
		dialog.dismiss();
	}
}
