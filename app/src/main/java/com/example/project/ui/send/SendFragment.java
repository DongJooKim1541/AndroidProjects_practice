package com.example.project.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class SendFragment extends Fragment {

    private Button button;
    private TextView box,textView;

    private boolean isCheck;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);


        button=(Button)root.findViewById(R.id.button);
        box=(TextView)root.findViewById(R.id.box);
        textView=(TextView)root.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheck){
                    box.setText("No Event!");
                }
                else{
                    box.setText("Event!");
                }

                isCheck=!isCheck;
            }
        });

        //터치 이벤트
        box.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String msg = null;
                int x=0;
                int y=0;

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        msg="Result: Action Down";
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x=(int)event.getX();
                        y=(int)event.getY();
                        msg="Result: MOVE(X: "+x+", y: "+y+")";
                        break;
                    case MotionEvent.ACTION_UP:
                        x=(int)event.getX();
                        y=(int)event.getY();
                        msg="Result: UP(X: "+x+", y: "+y+")";
                        break;
                }

                textView.setText(msg);
                return isCheck;
            }
        });

        return root;
    }

}