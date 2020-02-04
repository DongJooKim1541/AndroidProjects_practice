package com.example.project.ui.share;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class MyGameView extends View {

    private static final int BLANK=0;   //대기상태
    private static final int PLAY=1;    //게임 진행중
    private static final int DELAY=1500;//도형생성 시간

    private int status; //현재 상태

    private ArrayList<Shape> arShape=new ArrayList<Shape>();
    private Random rnd=new Random();
    private Activity mParent;

    //도형 관리 중첩 클래스
    private class Shape{
        public static final int RECT=0;     //사각형
        public static final int CIRCLE=1;   //원
        public static final int TRIANGLE=2; //삼각형

        int what;
        int color;
        Rect rt;
    }

    //생성자
    public MyGameView(Context context){
        super(context);

        mParent=(Activity)context;
        status=BLANK;

        //핸들러 실행
        handler.sendEmptyMessageDelayed(0,DELAY);
        android.util.Log.d("KDJ",Integer.toString(getWidth()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //캔버스를 검정색
        canvas.drawColor(Color.BLACK);

        if(status==BLANK){
            return;
        }

        int idx;

        for(idx=0;idx<arShape.size();idx++){
            Paint paint=new Paint();
            paint.setColor(arShape.get(idx).color);

            Rect rt=arShape.get(idx).rt;

            switch(arShape.get(idx).what){
                case Shape.RECT:
                    canvas.drawRect(rt,paint);
                    break;
                case Shape.CIRCLE:
                    canvas.drawCircle(rt.left+rt.width()/2,rt.top+rt.height()/2,rt.width()/2,paint);
                    break;
                case Shape.TRIANGLE:
                    Path path=new Path();
                    path.moveTo(rt.left+rt.width()/2,rt.top);
                    path.lineTo(rt.left,rt.bottom);
                    path.lineTo(rt.right,rt.bottom);
                    canvas.drawPath(path,paint);
                    break;
                default:
                    break;
            }
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            AddNewShape();  //도형생성
            status=PLAY;

            invalidate();   //onDraw 실행

            String title="stage - "+arShape.size();

            mParent.setTitle(title);
        }
    };

    //새로운 도형 생성 메소드
    public void AddNewShape(){
        Shape shape=new Shape();
        int idx;
        boolean bFindIntersect; //도형이 중복되는지 판별하는 변수
        Rect rt=new Rect();

        while(true){
            //렌덤으로 도형 사이즈 생성하기
            //50~150
            int size=rnd.nextInt(101)+50;

            //도형의 범위
            rt.left=rnd.nextInt(this.getWidth());
            android.util.Log.d("KDJ",Integer.toString(getWidth()));
            rt.top=rnd.nextInt(getHeight());
            rt.right=rt.left+size;
            rt.bottom=rt.top+size;

            if(rt.right>=getWidth()||rt.bottom>=getHeight()){
                continue;
            }

            bFindIntersect=false;

            for(idx=0;idx<arShape.size();idx++){
                if(rt.intersect(arShape.get(idx).rt)==true){
                    bFindIntersect=true;
                }
            }

            if(bFindIntersect==false){
                break;
            }
        }

        //도형 모양
        shape.what=rnd.nextInt(3);

        //도형 컬러 지정
        switch(rnd.nextInt(5)){
            case 0:
                shape.color= Color.WHITE;
                break;
            case 1:
                shape.color= Color.RED;
                break;
            case 2:
                shape.color= Color.GREEN;
                break;
            case 3:
                shape.color= Color.BLUE;
                break;
            case 4:
                shape.color= Color.YELLOW;
                break;
        }

        shape.rt=rt;
        arShape.add(shape);
    }

    //도형 터치 처리

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            int sel;
            sel=findShapeIdx((int)event.getX(),(int)event.getY());

            if(sel==-1){
                return true;
            }

            if(sel==arShape.size()-1){
                status=BLANK;
                invalidate();
                handler.sendEmptyMessageDelayed(0,DELAY);
            }
            else{
                //게임 종료
                AlertDialog.Builder builder=new AlertDialog.Builder(mParent);
                builder.setMessage("Restart Game??");
                builder.setTitle("Game Over");

                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //초기화 후 시작
                        arShape.clear();

                        status=BLANK;
                        invalidate();
                        handler.sendEmptyMessageDelayed(0,DELAY);
                    }
                });

                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });

                builder.setCancelable(false);
                builder.show();
            }
        }

        return true;
    }

    public int findShapeIdx(int x,int y){
        int idx;

        for(idx=0;idx<arShape.size();idx++){

            //arShape에 담긴 도형중에 터치된 x,y좌표를 가진 rect객체의 idx 반환
            if(arShape.get(idx).rt.contains(x,y)){
                return idx;
            }
        }

        return -1;
    }

}

