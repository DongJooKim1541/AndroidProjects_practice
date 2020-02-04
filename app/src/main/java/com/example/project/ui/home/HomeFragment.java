package com.example.project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;

public class HomeFragment extends Fragment {

    private EditText editText;
    private Button button;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        editText = (EditText)root.findViewById(R.id.editText);
        button = (Button)root.findViewById(R.id.button);
        textView = (TextView)root.findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = editText.getText().toString().trim();

                //String str1 = "";

                //for(int i = str.length()-1; i >= 0; i--){
                //    str1 += str.charAt(i);
                //}

                StringBuffer sbuf = new StringBuffer(str);

                String str1 = sbuf.reverse().toString();

                if(str.equals(str1)){
                    textView.setText("True");
                }else{
                    textView.setText("False");
                }
            }
        });

        return root;
    }
}