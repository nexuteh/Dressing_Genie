package com.dressingGenie;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Category extends AppCompatActivity {
    Firebase mRef ;
    FloatingActionButton fab;
    private ViewPager slider;
    private LinearLayout ll_dots;
    SlideAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category);
        Firebase.setAndroidContext(this);

        slider = (ViewPager) findViewById(R.id.viewPager);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);

        final String ctg = getIntent().getExtras().getString("category");
        mRef = new Firebase("https://dressinggenie.firebaseio.com/user");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String shape2 = map.get("bodyShape");
                showData(shape2,ctg);
                addBottomDots(0);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getCode());
            }
        });

        fab=(FloatingActionButton)findViewById(R.id.home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
        public void showData(String shape,String ctg) {
            slider_image_list = new ArrayList<>();
            if (shape.equals("[1]")) {
                if (ctg.equals("formal")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/formal/formal1.png?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=JGBICxA7GtPfjXPP5daGBUV3EiXdp6Ioxlve%2BkmH004%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/formal/formal2.png?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=hHHevWxHQgjGc1SJC4DNV8GfWiOw9QQfe1ANhh0ZeFk%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/formal/formal3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=iyMLbhWNzuG%2FUdJxRwSA3dAGSeCm2H1KfsXzQtMk0t4%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/formal/formal4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=V8HrLK3RlTtB84BNuBdwHlu1k5oSVR5YLmAvuxupexE%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/formal/formal5.png?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=sT2%2FZxqThxpQuFzb6GVrqc5p6aVdgHtRWlRe1x6M0lc%3D");
                } else if (ctg.equals("casual")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/casual/casual1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=HI2I%2BWnlDR0tVqti0wrUCcQTAIDMueIXsYs53m2cBec%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/casual/casual2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=FDza0iGQcX%2BsaVe2%2FZJukc7jjKbXdCuV2j6A4rSg574%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/casual/casual3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=qTnRFJEgKQxuwcR%2ByViwSE9NVNQ3Alq3t5ctYY2OD00%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/casual/casual4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=CSwCMhvoT3mqC1sMbtJC%2BPjcFl350uigq1Tw7MiMU08%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/casual/casual5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=Wx2YS5HjHCDXEwjd4vv2qgv9aRvMsXJyfK4bR9QbXpo%3D");
                } else if (ctg.equals("recreational")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/recreational/recrea1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=L%2BpygByVgbs4Jxa96EoL1KXvVTzFOPlRAu%2FH0Fy1GCw%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/recreational/recrea2.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=E%2BOS7EvqsDRoVZNJG%2B6KQft7iJ1Rmv4wkmxE8T1XQdw%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/recreational/recrea3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=wEVn9MPcmvJbeRXlim7o9BuVsK%2BNoscR8C71cMUS%2B5g%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/recreational/recrea4.jpeg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=U3sb3XrHURaenJXnE09YtNkzP5jEZn%2FeGmY9V7BhwuY%3D");
                } else {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/sports/sport1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=s1p5hYQwZpxPOdyLWvQuR7vowB7J7oimzJQyYKGCT9k%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/sports/sport2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=HxstJA98VTt7jP%2BOkaCqcpk1mv2T0O0DM%2Fc7AwWSp%2FU%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/sports/sport3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=Wj%2BGAhidOfgL2p8%2FAUZqcGZpLBwQDIF5s%2BzAQkjgMLc%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/1/sports/sport4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=P83Et2qszFcKUYf7DBX2hj9OYHNapcQzVr57RhMCK%2FU%3D");
                }
                sliderPagerAdapter = new SlideAdapter(Category.this, slider_image_list);
                slider.setAdapter(sliderPagerAdapter);
                slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) { addBottomDots(position); }
                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
            } else if (shape.equals("[2]")) {
                if (ctg.equals("formal")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/formal/formal1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=fKDf%2FpngEaf5Bvf7FCLUXfUfZMT13lS2DNHfLZZxlJM%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/formal/formal2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=JfLusiKlAjYvqcxRdOmYg9Y8mw9Vs0rqV%2B%2BHzurW6EU%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/formal/formal3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=sSpBRfrLPF8y3OQN3KQ4nqDoowAt9ifqEi8nDotcBI4%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/formal/formal4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=ieb3svrJltQ7cT9asUQvd9b6EBIlkasiv8NlLd0X6BE%3D");
                } else if (ctg.equals("casual")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/casual/casual1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=4OZ0OEBPlODA3%2Bz7a20rx2jj74VDJPc0JSgZ7kCnLjA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/casual/casual2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=MOS9n%2BbvokzKqXvQ%2B4piuTD50UAW5SwrYHUgd%2FZcfL8%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/casual/casual3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=ZaxtyN5uL7HSg4jPwUniZFwRAqBTb%2BX4Ud9OcMD%2BJI0%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/casual/casual4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=Vt2tuFacmuS%2F%2Bsc9Na1FXogCdvdZWZhwCLl2owp6eP0%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/casual/casual5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=ri7Lr4zPDQkEPDjNyd5qr4yAO1lkFwXUS3oKIRAbi4Q%3D");
                } else if (ctg.equals("recreational")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea1.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=AxaY3zEKzOhvVv0sawCtWXF7iyXR7v%2B0ZV9v2Vl9XEY%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea2.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=1ryZi8IqVJxwJzWhMFo0DVw0PdAM0w01rhTHd8z8duE%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=JtjvwGUkbgEDIqHSLc3mkGR6jdyKqTLy3eR6wjZToB0%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=BZt0kXwrgSDmDL9mYllCFxxj4D099ZKEBGYEVRoDPY4%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=dE%2BNCquB4ajef%2F%2BjI4C61GZpOO2tnrXqlF5DQpTHZ24%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea6.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=rSLkYctg1BWqiGvWtBfHyQGG9ajGcT8TeSVb0dGsdfI%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/recreational/recrea7.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=9avbMY1ns%2F2xA38ROQs%2FDhxKBO0%2FpTzxz7kF2EZiWlI%3D");
                } else {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/sports/sport1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=z%2FLUgjHtiKsws9za%2FAZeqxh8F6D1%2B9ISByQFGH0NYdg%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/sports/sport2.JPG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=1EpP%2FRuAMUeu2BVYJTCqyMGGtNWxz2OX3kalSVKu9%2FA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/sports/sport3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=EH%2BxlqyC1qKcFzsD6sESZTxZsZaUzgwNfFyqvLzvkbU%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/2/sports/sport4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=TXcOF6XTqFjf9zKJqrAQEakBnlnfVT2umIuoIwP3mm8%3D");
                }
                sliderPagerAdapter = new SlideAdapter(Category.this, slider_image_list);
                slider.setAdapter(sliderPagerAdapter);
                slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) { addBottomDots(position); }
                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
            } else if (shape.equals("[3]")) {
                if (ctg.equals("formal")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/formal/formal1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=zjrKzsgIEocyhobc%2B3v1Td0wLUnOBhWCyrLVA7akn70%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/formal/formal2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=wSQKN8yGjZRXotpa3CpelnUplXmL43Kv1kjueAL%2BFO8%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/formal/formal3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=TVK%2B7YH9X0qJigLdRSxQQtK%2BT%2Boa0eQBAU0JzWC63GI%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/formal/formal4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=J6ey%2FAAaCs1nd%2BtLTXfbDB0Pt0shKQ96K25YUfAcJKQ%3D");
                } else if (ctg.equals("casual")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=68yvS6gxGV3EdGLFNsU5tdZ%2FAn%2FO6yWxfD9HoXvDSWM%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=aQJSUJjZcGhyBBk5Oc3kNwIDR6hwBOIaEwHWXWcs11U%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=c5sVnl0BIhUHnVxd5NegjnK1GzOuDrQIPBp7TNkCD1E%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=ojrFSM7jv8S0mmxwy404bqnuII0OL%2Bn6Tjq702kgaFI%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=tmxAdgCG8lZWeGFEEyqbMX3vQgZ8hchoX7sfIt%2FKdRw%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual6.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=Tf8M%2Blv%2FGzart5yIxBxhVm%2BYQz6fPqM%2BlIOeyOmOQPI%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/casual/casual7.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=sxIZ4tvmwDqfUxvldsxA4D5F0NmEsCroV8W%2BXMItwMU%3D");
                } else if (ctg.equals("recreational")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=%2FIXSbDM6%2FzU7qGPVu2lYPho3mgzpPiSdD9dNvo1E%2F1M%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=by5r%2FHHJe8U69HHRvYCdSzXcls3bxOGmFayFiiUimHQ%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=l%2FUFYrFpWvmZCvDp9VPRPvwwM5rhjLCWIN1QCnrHBKE%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=SjuQKzlaTX8DQtyghltimEqFUG7ki8lzYGd7jhwptN8%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=yM9JXUw%2B1VUiJKHRGJP3md9kg6sJQL9yBuAucsLKQuk%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea6.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=BOegTjjRhSZ%2FheWD0NzByrZ4PoFJ0MBoYUiewb5ISyo%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/recreational/recrea7.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=7Li%2F6pdQb1KjpJVhmn24aut9vKoui%2FGmX5g1lsM9Ito%3D");
                } else {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/sports/sport1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=58vWFDPfuxrXzvBXMArOH2t%2BypleKayNJWVQstI4qdw%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/sports/sport2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=obeUVc4AuL9jzOCxmAccBMrnOGY0KEqAyCfdYp%2BiuOY%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/sports/sport3.jpeg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=drE%2FjdbmXoOBED0UrlsfKo3iaCrMG5A%2BB2tIERXWheE%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/sports/sport4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=qAhkCVrTN9Era%2Brvl4qHOaAX3AALjBTW5SIwFs7DYxk%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/3/sports/sport5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=SE46ImbedA5BUtHQ01AyVmptwHtzWFSGHkUNoUVKgVI%3D");
                }
                sliderPagerAdapter = new SlideAdapter(Category.this, slider_image_list);
                slider.setAdapter(sliderPagerAdapter);
                slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) { addBottomDots(position); }
                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
            } else if (shape.equals("[4]")) {
                if (ctg.equals("formal")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/formal/formal1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=LjCrw1UatosX8rV4BFIczV%2Fug4TceHV7NwoofUHLSME%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/formal/formal2.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=DrF%2F5afxo%2FTactrUfAyEff99jJqTQofNweIaJ8%2FTNxw%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/formal/formal3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=gb3LzIlVPKD34WBP8MhZljF17gRbbftb9QmD4U%2Fq3dU%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/formal/formal4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=nMuHGtb3kidTjqCBZKAIMBJyM%2ByG%2FGsIA46IHx2B1o0%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/formal/formal5.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=429%2BH7ELhdL5ahUwbUTDkgt8cichQVVh5WzA29EgkzE%3D");
                } else if (ctg.equals("casual")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/casual/casual1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=MAxACXs4n6lgzjegF7%2FGg9Z%2B5klfa%2B1FsJ4%2BJpPqrWc%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/casual/casual2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=e55ebGNilSHrSxwyiR2nH2BzI3J7xZFN0IDIUK%2FHQJo%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/casual/casual3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=zI39QEiY84WnWKaK3yn5o8peWPfiesyQ%2B7vd87n2FeY%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/casual/casual4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=z86EWB9SJMBeRJbeBiy7FPMdI2JVdYTw23gc0d%2BxOAk%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/casual/casual5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=b4IUCtDYco48A%2BFYBwxPkWlfK8NzSYeu9u%2FnHrcOOXw%3D");
                } else if (ctg.equals("recreational")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/recreational/recrea1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=WksM%2Bw1mOJXtfCriuKMfsC3XzNV7SGHl7jgNApftCoQ%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/recreational/recrea2.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=NqrPWirCKRjFeVZNl21yyWsBA7xlxYeis%2B9WhAz%2FJIg%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/recreational/recrea3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=xSGp4edXjSZ4ScCR3N4Y3qkqF16QYqDObbt0bzmERjc%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/recreational/recrea4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=HZvqoccSyQMOIVRbOBPx3GcDGVSW668bV4dnjd%2Fjk8Y%3D");
                } else {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/sports/sport1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=bFAyV0QM%2F0HyFuytL%2B3q9M2rDrGw2tq9U%2B5nTJh7m2Y%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/sports/sport2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=lirL%2FbGKxcQIvsSg5p8LoXmAL%2FKuiPoweDH5t4UQBaI%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/sports/sport3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=F7W7fVpYQpFUOPK35cFWYYICjUaOXOqgQkvzFozncV4%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/4/sports/sport4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=9t3y2euk9OjRYbMpgUDsqJhLGTkecX4Y9tY2WpTXh78%3D");
                }
                sliderPagerAdapter = new SlideAdapter(Category.this, slider_image_list);
                slider.setAdapter(sliderPagerAdapter);
                slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) { addBottomDots(position); }
                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
            } else {
                if (ctg.equals("formal")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/formal/formal1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=iPCE%2FjjyrimAG9GjVJYRNWa%2BJoP%2FbTlQDkkbDO9ryk0%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/formal/formal2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=1J1NfT77JqO4LmmeTC6Es7RioWps8dX7hTT2cyHPpWk%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/formal/formal3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=8Zrr4KO9uppNQc2vDZufkQqWLSKd2Z1wLgreKy5fI20%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/formal/formal4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=pb5EOrgVDiqxrrPkmEtpgo46Nw1Lo8WKgzeUgPAgirU%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/formal/formal5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=MCUFx5LMpH6gN8gPLmnEaRgwWpaefi5XOBK23Kw16dc%3D");
                } else if (ctg.equals("casual")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/casual/casual1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=m7vCYqon5dn1NlvesJFQX9FeZ%2BvwpNYbD4ZSiPWnSis%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/casual/casual2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=zMgPEvRg%2FMBawutZQ3viuTRl%2FmGpjs9UdP84Xz5rfvA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/casual/casual3.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=ZkKbQqfRBs73Xy6T0jKhNm5nibJ%2BkSI2A4x3EvrIGi8%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/casual/casual4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=8PyOQ9qyokLNvJOiSncvgrKLWJjSuocYXFBXYA7r7v8%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/casual/casual5.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=VRjcbp61OHEa5sQXgy6g45CPY01fNq%2FzHlDBIZAtwIs%3D");
                } else if (ctg.equals("recreational")) {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea1.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=w5FPdPKuxZAMs1VWWUZnfnTRMA%2BLO8mwHGziZ%2FvPxkA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea2.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=OymGw50NfRsPXJ%2FevoZn99AxGRrwIdsoR1dL93hgt2s%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=32f%2BcyItdCmQE15eqwxtRVfxAEbbnpgpvjJcixUFwuA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea4.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=L8VWCSZi6uobNbpLOqXdZtz4neNdJh6P58fULIdCTlY%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea5.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=BtRxR1x%2FfF%2FOjG2cDIFP98tfU%2FBQhUJm%2FDvNoObtaWo%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/recreational/recrea6.PNG?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=A4c7btOnukn1II48hMAfUhzm%2B%2Bmq%2FxxCi8sKtCVXAKA%3D");
                } else {
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/sports/sport1.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=nJLxUS5dBGmr0Do0cu8tiqCbegbbr%2Ba2eoZCNRXhUeg%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/sports/sport2.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=4Xh5%2BQ9DKO9d2L0zzXzDnkJoVdUPLXHrnpJev4xOL4E%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/sports/sport3.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=hGSwGeCRyv8%2Fr3ntMP6sqfpzUv3n7GPmBdv6FHt%2B2HA%3D");
                    slider_image_list.add("https://my239.blob.core.windows.net/dressinggenie/5/sports/sport4.jpg?st=2018-05-05T13%3A19%3A06Z&se=2019-05-06T13%3A19%3A00Z&sp=rwdl&sv=2017-04-17&sr=b&sig=5TWfOl4x7fMZOfMf3G1HAFp%2BBf1BH7Ooy5IqiKuZfng%3D");
                }
                sliderPagerAdapter = new SlideAdapter(Category.this, slider_image_list);
                slider.setAdapter(sliderPagerAdapter);
                slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) { addBottomDots(position); }
                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
            }
        }

    private void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];

        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#808080"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#DCDCDC"));
    }
}
