package com.chris.coordinatorlayoutsample.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chris.coordinatorlayoutsample.R;
import com.chris.coordinatorlayoutsample.fragment.ItemFragment;
import com.chris.coordinatorlayoutsample.tool.LowerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 16/8/2.
 */
public class CustomNavigationViewActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    ActionBar mActionBar;

    RecyclerView rvList;
    LinearLayoutManager linearLayoutManager;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_navigation_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        rvList = (RecyclerView) findViewById(R.id.rvNavigation);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvList.setLayoutManager(linearLayoutManager);

        List<String> upperList = new ArrayList<>();
        List<LowerItem> lowerList = new ArrayList<>();

        upperList.add("Home");
        upperList.add("Message");
        lowerList.add(new LowerItem("Friend",R.drawable.my_b));
        lowerList.add(new LowerItem("Discussion",R.drawable.payslip_b));

        mAdapter = new MyAdapter(upperList, lowerList);

        rvList.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ItemFragment fragment = ItemFragment.newInstance(upperList.get(0));
        gotoSubmainFragment(fragment);

        mActionBar.setTitle(upperList.get(0));
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
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<String> upperlist;
        List<LowerItem> lowerlist;
        private final int VIEW_TYPE_UPPER = 0;
        private final int VIEW_TYPE_LOWER = 1;

        int upperSize = 0;

        public MyAdapter(List<String> upperlist, List<LowerItem> lowerlist) {
            this.upperlist = upperlist;
            this.lowerlist = lowerlist;

            upperSize = upperlist.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            if (viewType == VIEW_TYPE_UPPER) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_navigation_upper_item, viewGroup, false);
                return new MyUpperViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOWER) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_navigation_lower_item, viewGroup, false);
                return new MyLowerViewHolder(view);
            }

            return null;
        }

        @Override
        public int getItemViewType(int position) {

            if (position < upperSize) {
                return VIEW_TYPE_UPPER;
            } else {
                return VIEW_TYPE_LOWER;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            if (holder instanceof MyUpperViewHolder) {

                MyUpperViewHolder myViewHolder = (MyUpperViewHolder) holder;

                myViewHolder.tvText.setText(upperlist.get(position));

                myViewHolder.rlGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemFragment fragment = ItemFragment.newInstance(upperlist.get(position));
                        gotoSubmainFragment(fragment);
                        mDrawerLayout.closeDrawers();
                        mActionBar.setTitle(upperlist.get(position));
                    }
                });
            } else if (holder instanceof MyLowerViewHolder) {
                MyLowerViewHolder myViewHolder = (MyLowerViewHolder) holder;

                myViewHolder.tvText.setText(lowerlist.get(position - upperSize).text);
                myViewHolder.ivImage.setBackgroundResource(lowerlist.get(position - upperSize).image);

                myViewHolder.rlGroup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemFragment fragment = ItemFragment.newInstance(lowerlist.get(position - upperSize).text);
                        gotoSubmainFragment(fragment);
                        mDrawerLayout.closeDrawers();
                        mActionBar.setTitle(lowerlist.get(position - upperSize).text);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return upperSize + lowerlist.size();
        }

        public class MyUpperViewHolder extends RecyclerView.ViewHolder {

            public TextView tvText;
            public RelativeLayout rlGroup;

            public MyUpperViewHolder(View itemView) {
                super(itemView);
                tvText = (TextView) itemView.findViewById(R.id.tvText);
                rlGroup = (RelativeLayout) itemView.findViewById(R.id.rlGroup);
            }
        }

        public class MyLowerViewHolder extends RecyclerView.ViewHolder {

            public TextView tvText;
            public ImageView ivImage;
            public RelativeLayout rlGroup;

            public MyLowerViewHolder(View itemView) {
                super(itemView);
                tvText = (TextView) itemView.findViewById(R.id.tvText);
                ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
                rlGroup = (RelativeLayout) itemView.findViewById(R.id.rlGroup);
            }
        }
    }
}
