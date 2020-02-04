package com.example.project.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.project.R;

public class GalleryFragment extends Fragment {

    private TextView textView;
    private Button[] btn;
    private int randNum;

    private int setRandom(){
        int rand = (int)(Math.random()*4)+1;//1,2,3,4
        android.util.Log.d("KDJ",Integer.toString(rand));
        return rand;
    }

    private void setTxt(int rnd, int idx){
        if(rnd==idx+1){
            textView.setText("true");
        }
        else{
            textView.setText("false");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        btn = new Button[5];

        textView=(TextView)root.findViewById(R.id.textview);
        btn[0] = (Button)root.findViewById(R.id.button1);
        btn[1] = (Button)root.findViewById(R.id.button2);
        btn[2] = (Button)root.findViewById(R.id.button3);
        btn[3] = (Button)root.findViewById(R.id.button4);
        btn[4] = (Button)root.findViewById(R.id.button5);
        randNum=setRandom();
        for(int i=0;i<btn.length;i++){
            final int index=i;
            btn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(index){
                        case 0:
                            setTxt(randNum,index);
                            break;
                        case 1:
                            setTxt(randNum,index);
                            break;
                        case 2:
                            setTxt(randNum,index);
                            break;
                        case 3:
                            setTxt(randNum,index);
                            break;
                        case 4:
                            randNum=setRandom();
                            break;
                    }
                }
            });
        }
        return root;
    }
}