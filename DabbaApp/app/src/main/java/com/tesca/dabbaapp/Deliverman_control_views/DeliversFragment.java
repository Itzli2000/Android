package com.tesca.dabbaapp.Deliverman_control_views;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tesca.dabbaapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliversFragment extends Fragment {




    public DeliversFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivers, container, false);
    }

}
