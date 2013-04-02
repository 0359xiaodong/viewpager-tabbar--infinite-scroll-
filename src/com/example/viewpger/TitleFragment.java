package com.example.viewpger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpager.R;

public class TitleFragment extends Fragment {
	private static final String DATA_EXTRA = "extra_data";
	private static final String DATA_POS = "pos_data";

	private OnPageClickListener onPageClickListener;
	private View rootView;
	private TextView title;

	public static TitleFragment newInstance(String str, int pos) {
		final TitleFragment f = new TitleFragment();

		final Bundle args = new Bundle();
		args.putString(DATA_EXTRA, str);
		args.putInt(DATA_POS, pos);
		f.setArguments(args);

		return f;
	}

	public TitleFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = (View) inflater.inflate(R.layout.view_tab, container, false);
		title = (TextView) rootView.findViewById(R.id.text);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getArguments().getString(DATA_EXTRA) != null) {
			title.setText(getArguments().getString(DATA_EXTRA));
		}
		title.setTag(getArguments().getInt(DATA_POS));
		title.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onPageClickListener != null) {
					onPageClickListener.onPageClick((Integer) v.getTag());
				}

			}
		});
	}

	public void setOnPageClickListener(OnPageClickListener l) {
		onPageClickListener = l;
	}

}
