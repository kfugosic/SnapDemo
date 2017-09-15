package com.kfugosic.snapdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kfugosic.snapdemo.fragments.ChatFragment;
import com.kfugosic.snapdemo.fragments.EmptyFragment;
import com.kfugosic.snapdemo.fragments.StoryFragment;

/**
 * Created by Kristijan on 15.9.2017..
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return ChatFragment.create();
            case 1:
                return EmptyFragment.create();
            case 2:
                return StoryFragment.create();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
