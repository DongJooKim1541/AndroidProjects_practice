package com.example.project;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.project.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //키가 눌러졌을때 자동으로 실행되는 메소드

        if(event.getAction()==KeyEvent.ACTION_DOWN){

            if(keyCode==KeyEvent.KEYCODE_BACK){
                //alertDialog 생성
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                //dialog.setPositiveButton(); - 동의, 확인
                //dialog.setNegativeButton(); - 취소, 비동의
                //dialog.setNeutralButton(); - 설명, 중립

                //버튼을 중복적으로 정의하게 되면 가장 나중에 있는 버튼으로 갱신된다.
                //중복되지 않도록 정의하여야 한다.
                //dismiss는 없어도 된다.

                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //finish();
                        // - 종료 메소드
                        // - Activity를 숨기고 멈추고 소멸시키는 역할
                        // - 실행중인 프로세스는 살아있다.
                        // - 어플리케이션 실행 중에 액티비티를 닫을때 사용한다.

                        //System.exit(0);
                        // - 프로세스 실행중에 앱을 모두 종료하고 모든 리소스를 반환하는 메소드
                        // - 0 - 정상종료   1 - 비정상 종료
                        // - 어플리케이션 종료시 사용된다

                        // 현재 System.exit() 메소드로 앱을 종료 시켰을때 프로세스를 완전히 종료하지 못한다는 의견이 있다.

                        android.os.Process.killProcess(android.os.Process.myPid());
                        //이것을 사용하여 프로세스를 종료시키는 사람도 있다.

                        //Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"CANCEL",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setTitle("EXIT?");
                dialog.show();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
