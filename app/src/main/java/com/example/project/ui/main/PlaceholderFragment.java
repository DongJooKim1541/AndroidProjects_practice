package com.example.project.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.project.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    int tabName;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle data = new Bundle();
        data.putInt("tabName",index);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data=getArguments();
        tabName=data.getInt("tabName");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.textView);
        final TextView textView1 = root.findViewById(R.id.textView1);
        final TextView textView2 = root.findViewById(R.id.textView2);
        switch(tabName){
            case 1:
                textView.setText("tab 1");

                break;
            case 2:
                textView1.setText("tab 2");

                break;
            case 3:
                textView2.setText("tab 3");

                break;
        }


        return root;
    }
}