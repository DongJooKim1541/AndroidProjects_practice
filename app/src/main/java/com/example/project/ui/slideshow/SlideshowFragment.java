package com.example.project.ui.slideshow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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

public class SlideshowFragment extends Fragment {

    private TextView watch,record;
    private Button btn1,btn2;
    private long time,time1,time2,time3,ms,s,m,h;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        watch=(TextView)root.findViewById(R.id.textview_watch);
        record=(TextView)root.findViewById(R.id.textview_record);
        btn1=(Button)root.findViewById(R.id.button);
        btn2=(Button)root.findViewById(R.id.button1);

        btn2.setEnabled(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str=btn1.getText().toString().trim();

                switch(str){
                    case "시작":
                        time1=MakeTime();
                        mHandler.sendEmptyMessage(0);
                        btn2.setEnabled(true);
                        btn1.setText("멈춤");
                        btn2.setText("기록");
                        break;
                    case "멈춤":
                        time3=time2;
                        mHandler.removeMessages(0);
                        btn1.setText("시작");
                        btn2.setText("리셋");
                        break;
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str=btn2.getText().toString().trim();

                switch(str){
                    case "기록":
                        String str1=record.getText().toString().trim();
                        record.setText(str1+"\n"+h+":"+m+":"+s+":"+ms);

                        break;
                    case "리셋":
                        mHandler.removeMessages(0);
                        btn1.setText("시작");
                        btn2.setText("기록");
                        btn2.setEnabled(false);
                        h=0;
                        m=0;
                        s=0;
                        ms=0;
                        time3=0;
                        watch.setText(h+":"+m+":"+s+":"+ms);
                        record.setText("");
                        break;
                }
            }
        });

        return root;
    }

    private Handler mHandler=new Handler() {

        //Handler가 실행되는 동안 실행하는 메소드, Handler의 실행 내용이 적히는 메소드
        @Override
        public void handleMessage(@NonNull Message msg) {

            time=MakeTime();
            if(time==time1){
                time2 = time - time1;
            }
            else{
                time2 = time - time1 + time3;
            }
            ms = time2 % 1000;
            s = (time2 / 1000) % 60;
            m = (time2 / 1000 / 60) % 60;
            h = (time2 / 1000 / 60 / 60) % 60;

            watch.setText(h+":"+m+":"+s+":"+ms);
            mHandler.sendEmptyMessage(0);
        }
    };

    private Long MakeTime(){
        Long tNum= SystemClock.elapsedRealtime();//실행된 시간을 밀리언 초 단위로 반환하는 메소드
        return tNum;
    }
}