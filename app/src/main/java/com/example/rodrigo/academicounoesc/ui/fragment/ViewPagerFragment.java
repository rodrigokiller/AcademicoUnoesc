package com.example.rodrigo.academicounoesc.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.example.rodrigo.academicounoesc.R;
import com.example.rodrigo.academicounoesc.adapter.TabPagerItem;
import com.example.rodrigo.academicounoesc.adapter.ViewPagerAdapter;

public class ViewPagerFragment extends Fragment {
	private List<TabPagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTabPagerItem();
    }

    private void createTabPagerItem(){
        //mTabs.add(new TabPagerItem(getString(R.string.starred), MainFragment.newInstance(getString(R.string.starred))));
        //mTabs.add(new TabPagerItem(getString(R.string.important), MainFragment.newInstance(getString(R.string.important))));
        //mTabs.add(new TabPagerItem(getString(R.string.documents), MainFragment.newInstance(getString(R.string.documents))));
        mTabs.add(new TabPagerItem("01/2015", MainFragment.newInstance("01/2015")));
        mTabs.add(new TabPagerItem("02/2014", MainFragment.newInstance("02/2014")));
        mTabs.add(new TabPagerItem("01/2014", MainFragment.newInstance("01/2014")));
        mTabs.add(new TabPagerItem("02/2013", MainFragment.newInstance("02/2013")));
        mTabs.add(new TabPagerItem("01/2013", MainFragment.newInstance("01/2013")));
        mTabs.add(new TabPagerItem("02/2013", MainFragment.newInstance("02/2012")));
        mTabs.add(new TabPagerItem("01/2013", MainFragment.newInstance("01/2012")));
        mTabs.add(new TabPagerItem("02/2013", MainFragment.newInstance("02/2011")));
        mTabs.add(new TabPagerItem("01/2013", MainFragment.newInstance("01/2011")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
    	
    	mViewPager.setOffscreenPageLimit(mTabs.size());

        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));
        TabLayout mSlidingTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        //mSlidingTabLayout.setTabMode();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSlidingTabLayout.setElevation(15);
        }
        mSlidingTabLayout.setupWithViewPager(mViewPager);
    }
}