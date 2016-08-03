package com.chris.coordinatorlayoutsample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Chris on 16/8/3.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public android.support.design.widget.TabLayout mTabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    abstract void setupTab();
}
