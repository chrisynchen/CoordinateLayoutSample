package com.chris.coordinatorlayoutsample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chris.coordinatorlayoutsample.R;
import com.chris.coordinatorlayoutsample.fragment.ItemFragment;

/**
 * Created by Chris on 16/8/2.
 */
public class CustomActionBarActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    TextView mActionBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setActionBar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setupTab();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ItemFragment fragment = ItemFragment.newInstance("Home");
        gotoSubmainFragment(fragment);
        mActionBarTitle.setText("Home");
    }

    @Override
    void setupTab() {
        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tab);

        for (int i = 0; i < 10; i++) {
            mTabs.addTab(mTabs.newTab().setText("TAB" + i));
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        if(menuItem.getItemId() == R.id.nav_home){
                            ItemFragment fragment = ItemFragment.newInstance("Home");
                            gotoSubmainFragment(fragment);
                            mActionBarTitle.setText("Home");
                        }else if(menuItem.getItemId() == R.id.nav_messages){
                            ItemFragment fragment = ItemFragment.newInstance("Message");
                            gotoSubmainFragment(fragment);
                            mActionBarTitle.setText("Message");
                        }else if(menuItem.getItemId() == R.id.nav_friends){
                            ItemFragment fragment = ItemFragment.newInstance("Friend");
                            gotoSubmainFragment(fragment);
                            mActionBarTitle.setText("Friend");
                        }else if(menuItem.getItemId() == R.id.nav_discussion){
                            ItemFragment fragment = ItemFragment.newInstance("Discussion");
                            gotoSubmainFragment(fragment);
                            mActionBarTitle.setText("Discussion");
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void gotoSubmainFragment(android.support.v4.app.Fragment target) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, target);
        transaction.commit();
    }

    private void setActionBar() {

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        mActionBarTitle = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView ivStar = (ImageView) mCustomView.findViewById(R.id.ivStar);
        ImageView ivSearch = (ImageView) mCustomView.findViewById(R.id.ivSearch);

        mActionBarTitle.setText("My Own Title");
        ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomActionBarActivity.this,"click star!",Toast.LENGTH_SHORT).show();
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomActionBarActivity.this,"click search!",Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout rlTopLeftTitle = (RelativeLayout) mCustomView.findViewById(R.id.rlTopLeftTitle);
        rlTopLeftTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
