package com.chris.coordinatorlayoutsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.chris.coordinatorlayoutsample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 16/8/2.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TITLE = "title";

    private static class Sample {
        private String title;
        private Class className;

        public Sample(String title, Class className) {
            this.title = title;
            this.className = className;
        }
    }

    private static final Sample[] mSamples = {
            new Sample("NormalNavigationView", NormalNavigationViewActivity.class),
            new Sample("CustomActionBar", CustomActionBarActivity.class),
            new Sample("CustomNavigationView", CustomNavigationViewActivity.class),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list_view);

        ListAdapter adapter = new SimpleAdapter(this, createListItems(), android.R.layout.simple_list_item_1,
                new String[] {TITLE}, new int[] {android.R.id.text1});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> createListItems() {
        List<Map<String, Object>> items = new ArrayList<>();
        for (Sample mSample : mSamples) {
            Map<String, Object> map = new HashMap<>();
            map.put(TITLE, mSample.title);
            items.add(map);
        }
        return items;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, mSamples[position].className);
        startActivity(intent);
    }
}
