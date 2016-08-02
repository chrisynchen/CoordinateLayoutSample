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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chris.coordinatorlayoutsample.R;
import com.chris.coordinatorlayoutsample.fragment.ItemFragment;

public class NormalNavigationViewActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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
        mActionBar.setTitle("Home");
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
                            mActionBar.setTitle("Home");
                        }else if(menuItem.getItemId() == R.id.nav_messages){
                            ItemFragment fragment = ItemFragment.newInstance("Message");
                            gotoSubmainFragment(fragment);
                            mActionBar.setTitle("Message");
                        }else if(menuItem.getItemId() == R.id.nav_friends){
                            ItemFragment fragment = ItemFragment.newInstance("Friend");
                            gotoSubmainFragment(fragment);
                            mActionBar.setTitle("Friend");
                        }else if(menuItem.getItemId() == R.id.nav_discussion){
                            ItemFragment fragment = ItemFragment.newInstance("Discussion");
                            gotoSubmainFragment(fragment);
                            mActionBar.setTitle("Discussion");
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void gotoSubmainFragment(android.support.v4.app.Fragment target) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, target);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}
