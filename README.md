# BottomNavigation
Material Design Bottom Navigation Component
https://material.google.com/components/bottom-navigation.html


使用
=================
layout.xml中配置Bottom Navigation
```xml 
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.mybottomnavigationdemo2.MainActivity">

    <com.example.bottomnavigation.BottomNavigation
        android:id="@+id/bn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        />
</android.support.design.widget.CoordinatorLayout>
```

MainActivity.java中设置
```java
bnv = (BottomNavigation) findViewById(R.id.bn);
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cloud_off_white_24dp,"Cloud",R.color.green));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cast_connected_white_24dp,"Chrome",android.R.color.holo_green_dark));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_mail_white_24dp,"Mail",android.R.color.holo_orange_dark));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_format_list_numbered_white_24dp,"List", R.color.colorAccent));
bnv.setOnBottomNavigationClickListener(new BottomNavigationItemClickListener() {
            @Override
            public void onBottomNavigationItemSelected(int position) {
            }

            @Override
            public void onBottomNavigationItemReselected(int position) {
            }
        });
```
