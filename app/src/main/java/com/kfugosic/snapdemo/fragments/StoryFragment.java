package com.kfugosic.snapdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.kfugosic.snapdemo.R;

/**
 * Created by Kristijan on 15.9.2017..
 */

public class StoryFragment extends  BaseFragment {

    public static StoryFragment create(){
        return new StoryFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle bundle) {

    }
}
