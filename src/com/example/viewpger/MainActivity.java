package com.example.viewpger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;

import com.example.viewpager.R;

public class MainActivity extends FragmentActivity {

	private ViewPager pager;
	private ViewPager tab;

	public static final int LOOPCOUNT = 1000; // 这个数字不能设置过大，会OOM
	public static final int MIDCOUNT = LOOPCOUNT / 2;

	public static String[] strs = new String[] { "item1", "item2", "item3",
			"item4", "item5", "item6" };

	public static float TABITEMWIDTH = 0.3f;
	public static float TABITEMMARGIN = 0.05f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pager = (ViewPager) findViewById(R.id.pager);
		tab = (ViewPager) findViewById(R.id.tab);
		initTitleGallery();
		initContentGallery();
	}

	private void initTitleGallery() {
		TitleAdapter titleAdapter = new TitleAdapter(
				getSupportFragmentManager());
		tab.setAdapter(titleAdapter);
		tab.setPageMargin((int) (getDisplayWidth() * TABITEMMARGIN));
		tab.setOffscreenPageLimit(2);
		tab.setCurrentItem(MIDCOUNT - 1, false);
		titleAdapter.setOnPageClickListener(new OnPageClickListener() {

			@Override
			public void onPageClick(int arg0) {
				tab.setCurrentItem(arg0 - 1);

			}
		});
		tab.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				pager.setCurrentItem(arg0 + 1, true);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initContentGallery() {

		PagerAdapter galleryAdapter = new PagerAdapter(
				getSupportFragmentManager());
		pager.setAdapter(galleryAdapter);
		pager.setPageMargin(20);
		pager.setOffscreenPageLimit(2);
		pager.setCurrentItem(MIDCOUNT, false);
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				tab.setCurrentItem(arg0 - 1, true);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private int getDisplayWidth() {
		int w = 0;
		Display display = this.getWindowManager().getDefaultDisplay();
		w = display.getWidth();
		return w;
	}

	private class PagerAdapter extends FragmentStatePagerAdapter {

		public PagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public int getCount() {
			return LOOPCOUNT;
		}

		@Override
		public Fragment getItem(int position) {

			int s = 0;
			if (position < MIDCOUNT) {
				s = strs.length - (Math.abs(position - MIDCOUNT) % strs.length);
			} else {
				s = position - MIDCOUNT;
			}
			int pos = s % strs.length;

			ContentFragment fragment = ContentFragment.newInstance(strs[pos]);

			return fragment;
		}

	}

	private class TitleAdapter extends FragmentStatePagerAdapter {

		private OnPageClickListener ml;

		public TitleAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public int getCount() {
			return LOOPCOUNT;
		}

		@Override
		public float getPageWidth(int position) {

			return 0.3f;// 如果是显示三个tab的话就0.3，剩余的0.1用margin分配，确保中间的tab居正中间。
		}

		@Override
		public Fragment getItem(int position) {

			int s = 0;
			if (position < MIDCOUNT) {
				s = strs.length - (Math.abs(position - MIDCOUNT) % strs.length);
			} else {
				s = position - MIDCOUNT;
			}
			int pos = s % strs.length;

			TitleFragment fragment = TitleFragment.newInstance(strs[pos],
					position);

			fragment.setOnPageClickListener(new OnPageClickListener() {

				@Override
				public void onPageClick(int arg0) {
					if (ml != null) {
						ml.onPageClick(arg0);
					}

				}
			});
			return fragment;
		}

		public void setOnPageClickListener(OnPageClickListener l) {
			ml = l;
		}

	}

}
