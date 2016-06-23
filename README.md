# BottomNavigation
Material Design Bottom Navigation Component
https://material.google.com/components/bottom-navigation.html


##使用
###基本设置
**activity_main.xml:**
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

**MainActivity.java**
```java
bnv = (BottomNavigation) findViewById(R.id.bn);
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cloud_off_white_24dp,"Cloud"));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_cast_connected_white_24dp,"Chrome"));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_mail_white_24dp,"Mail"));
        bnv.addItem(new BottomNavigationItem(R.drawable.ic_format_list_numbered_white_24dp,"List"));
bnv.setOnBottomNavigationClickListener(new BottomNavigationItemClickListener() {
            @Override
            public void onBottomNavigationItemSelected(int position) {
            }

            @Override
            public void onBottomNavigationItemReselected(int position) {
            }
        });
```

###自定义设置
####设置背景动画方式
```java
bnv.setBackgroundStyle(BottomNavigation.BACKGROUND_STYLE_RIPPLE);
```
####设置Item显示和动画
```java
bnv.setMode(BottomNavigation.MODE_SHIFTING);
```
####设置字体
```java
bnv.setTypeFace("Roboto-Regular.ttf");
```
####设置badge红色小圆点显示方式,带数字显示或者只显示小圆点
```java
bnv.setBadgeStyle(BottomNavigation.BADGE_STYLE_NUM);
```
####显示隐藏badge红色小圆点,设置为带数字的badge时,超过100则显示"..."
```java
bnv.showBadge(0,"100");
```
####设置badge红色小圆点是否在Item点击后自动隐藏
```java
bnv.setBadgeAutoHide(false);
```





