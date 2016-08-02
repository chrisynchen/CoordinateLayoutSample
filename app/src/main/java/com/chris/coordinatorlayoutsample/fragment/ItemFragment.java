package com.chris.coordinatorlayoutsample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chris.coordinatorlayoutsample.R;
import com.chris.coordinatorlayoutsample.activity.CustomActionBarActivity;
import com.chris.coordinatorlayoutsample.activity.NormalNavigationViewActivity;

/**
 * Created by Chris on 16/8/1.
 */
public class ItemFragment extends Fragment {

    private AppCompatActivity mActivity;

    public static ItemFragment newInstance(String s) {

        ItemFragment fragment = new ItemFragment();

        Bundle b = new Bundle();
        b.putString("TEMP_KEY",s);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof NormalNavigationViewActivity) {
            mActivity = (NormalNavigationViewActivity) context;
        }else if(context instanceof CustomActionBarActivity){
            mActivity = (CustomActionBarActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, null);

        initialView(view);

        return view;
    }

    private void initialView(View v) {

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });


        String s = getArguments().getString("TEMP_KEY");

        ((TextView) v.findViewById(R.id.tvText)).setText(s);
    }
}
