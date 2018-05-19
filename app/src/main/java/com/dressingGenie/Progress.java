package com.dressingGenie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class Progress extends AppCompatActivity{
    RingProgressBar progressBar;
    Handler myHandler;
    Firebase mRef ;
    String prgsTime= null;
    int progress =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar= (RingProgressBar)findViewById(R.id.progress1);
        ringProgress();
    }

    public  void ringProgress() {
        progressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                do{
                    progressTime();
                }while(prgsTime.equals("0"));
            }
        });

        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    if (progress < 100) {
                        progress++;
                        progressBar.setProgress(progress);
                    }
                }
            }
        };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    progressTime();
                    for (int i = 0; i < 100; i++)
                        try {
                            Thread.sleep(160);
                            myHandler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }).start();

    }

    public void progressTime() {
        mRef = new Firebase("https://dressinggenie.firebaseio.com/user");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String pgs = map.get("progress");
                if(pgs.equals("0")) {
                    Intent intent = new Intent(Progress.this, Result.class);
                    startActivity(intent);
                }
                prgsTime = pgs;
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getCode());
            }
        });
    }
}
