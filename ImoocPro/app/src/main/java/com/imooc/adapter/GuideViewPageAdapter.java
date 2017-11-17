package com.imooc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobaima on 17-11-17.
 */

public class GuideViewPageAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	public GuideViewPageAdapter(FragmentManager fm) {
		super(fm);
	}
	public GuideViewPageAdapter(FragmentManager fragmentManager,List<Fragment> arrayList) {
		super(fragmentManager);
		this.fragmentList=arrayList;
	}
	@Override
	public Fragment getItem(int arg0) {
		return fragmentList.get(arg0);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

}
