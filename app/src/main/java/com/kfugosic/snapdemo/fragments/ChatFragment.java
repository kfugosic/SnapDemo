package com.kfugosic.snapdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.kfugosic.snapdemo.R;

/**
 * Created by Kristijan on 15.9.2017..
 */

public class ChatFragment extends BaseFragment {

    public static ChatFragment create(){
        return new ChatFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle bundle) {

    }
}
