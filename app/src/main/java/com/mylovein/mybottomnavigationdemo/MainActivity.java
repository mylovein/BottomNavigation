package com.mylovein.mybottomnavigationdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mylovein.bottomnavigation.BottomNavigation;
import com.mylovein.bottomnavigation.BottomNavigationItemClickListener;
import com.mylovein.bottomnavigation.item.BottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigation bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = (BottomNavigation) findViewById(R.id.bn);
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cloud_off_white_24dp,"Cloud", R.color.green));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cast_connected_white_24dp,"Chrome",android.R.color.holo_green_dark));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_mail_white_24dp,"Mail",android.R.color.holo_orange_dark));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_format_list_numbered_white_24dp,"List", R.color.colorAccent));
        bnv.setBadgeStyle(BottomNavigation.BADGE_STYLE_NUM);
//        bnv.setBackgroundStyle(BottomNavigation.BACKGROUND_STYLE_RIPPLE);
//        bnv.setMode(BottomNavigation.MODE_SHIFTING);
//        bnv.setTypeFace("Roboto-Regular.ttf");
//        bnv.showBadge(0,"100");
        bnv.setOnBottomNavigationClickListener(new BottomNavigationItemClickListener() {
            @Override
            public void onBottomNavigationItemSelected(int position) {
//                Toast.makeText(MainActivity.this,"点击了"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBottomNavigationItemReselected(int position) {
//                Toast.makeText(MainActivity.this,"又点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction(
                        "Action",
                        null
                ).show();
            }
        });

        Button btn = (Button) findViewById(R.id.btn);
//        btn.setText(new String(Character.toChars(0x1f333)));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnv.showBadge(2,"3");
//                bnv.toggleVisibility(false);
            }
        });



    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
