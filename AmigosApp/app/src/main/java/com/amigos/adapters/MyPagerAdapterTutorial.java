package com.amigos.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapterTutorial extends FragmentPagerAdapter{

	private ArrayList<Fragment> fragments;


	public MyPagerAdapterTutorial(FragmentManager manager) {
		super(manager);
		fragments = new ArrayList<Fragment>();
	}

	public void addFragment(Fragment fragment)
    {
		fragments.add(fragment);
	}


	
	@Override
	public Fragment getItem(int arg0) {
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}



}
