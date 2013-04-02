package com.example.viewpger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpager.R;

public class ContentFragment extends Fragment {
	private static final String DATA_EXTRA = "extra_data";

	private String str;
	private TextView textview;
	private View rootView;

	public static ContentFragment newInstance(String str) {
		final ContentFragment f = new ContentFragment();

		final Bundle args = new Bundle();
		args.putString(DATA_EXTRA, str);

		f.setArguments(args);

		return f;
	}

	public ContentFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		str = getArguments() != null ? getArguments().getString(DATA_EXTRA)
				: null;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (View) inflater.inflate(R.layout.view_content,
				container, false);
		textview = (TextView) rootView.findViewById(R.id.text);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (textview != null && str != null) {
			textview.setText(str);
		}

	}

}
