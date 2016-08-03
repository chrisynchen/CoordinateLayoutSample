package com.chris.coordinatorlayoutsample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chris.coordinatorlayoutsample.R;

/**
 * Created by Chris on 16/8/3.
 */
public class ItemDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "item_detail";
    public static final int poster[] = {
            R.drawable.android_1_0,
            R.drawable.android_1_5,
            R.drawable.android_1_6,
            R.drawable.android_2_0,
            R.drawable.android_2_2,
            R.drawable.android_2_3,
            R.drawable.android_3_0,
            R.drawable.android_4_0,
            R.drawable.android_4_3,
            R.drawable.android_4_4,
            R.drawable.android_5_0,
            R.drawable.android_6_0,
            R.drawable.android_7_0};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_main_activity);
        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        ViewPager ivPagerPoster = (ViewPager) findViewById(R.id.ivPagerPoster);

        ivPagerPoster.setAdapter(new PosterPagerAdapter(poster));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_star) {
            Toast.makeText(this,"click star!",Toast.LENGTH_SHORT).show();
            return true;
        } if (id == R.id.menu_search) {
            Toast.makeText(this,"click search!",Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public class PosterPagerAdapter extends PagerAdapter {
        private int[] poster;

        public PosterPagerAdapter(int poster[]) {
            this.poster = poster;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return poster.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = container.getContext();
            View view = LayoutInflater.from(context).inflate(R.layout.poster_viewpager, container, false);

            ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);

            Glide.with(context).load(poster[position]).centerCrop().into(ivImage);

            container.addView(view, 0);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
