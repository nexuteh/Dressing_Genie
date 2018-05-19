package com.dressingGenie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.NameValuePair;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private Button btnSubmit;
    private Firebase mRef;
    public String height,shoulder,chest,waist,hip2,inseam;
    public EditText he,sh,ch,wa,hip,ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        mRef = new Firebase("https://dressinggenie.firebaseio.com/user");
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultActivity();
            }
        });

        height = getIntent().getExtras().getString("Height","defaultKey");
        shoulder = getIntent().getExtras().getString("Shoulder","defaultKey");
        chest = getIntent().getExtras().getString("Chest","defaultKey");
        waist = getIntent().getExtras().getString("Waist","defaultKey");
        hip2 = getIntent().getExtras().getString("Hip","defaultKey");
        inseam = getIntent().getExtras().getString("Inseam","defaultKey");

        he = (EditText) findViewById(R.id.textHeight);
        sh = (EditText) findViewById(R.id.textShoulder);
        ch = (EditText) findViewById(R.id.textChest);
        wa = (EditText) findViewById(R.id.textWaist);
        hip = (EditText) findViewById(R.id.textHip);
        ins = (EditText) findViewById(R.id.textInseam);

        he.setText(height);
        sh.setText(shoulder);
        ch.setText(chest);
        wa.setText(waist);
        hip.setText(hip2);
        ins.setText(inseam);

    }


    public void resultActivity() {

            if (he.getText().toString().trim().equals("")) {
                he.setError("Height is required!");
                he.setHint("Please enter height!");
            } else if (sh.getText().toString().trim().equals("")) {
                sh.setError("Shoulder width is required!");
                sh.setHint("Please enter shoulder width!");
            } else if (ch.getText().toString().trim().equals("")) {
                ch.setError("Chest width is required!");
                ch.setHint("Please enter chest width!");
            } else if (wa.getText().toString().trim().equals("")) {
                wa.setError("Waist width is required!");
                wa.setHint("Please enter waist width!");
            } else if (hip.getText().toString().trim().equals("")) {
                hip.setError("Hip width is required!");
                hip.setHint("Please enter hip width!");
            } else if (ins.getText().toString().trim().equals("")) {
                ins.setError("Inseam Length is required!");
                ins.setHint("Please enter inseam length!");
            } else {
                Firebase childRef = mRef.child("progress");
                childRef.setValue("1");
                String gender = "1";
                Firebase childRef1 = mRef.child("gender");
                childRef1.setValue(gender);
                String height = he.getText().toString();
                Firebase childRef2 = mRef.child("height");
                childRef2.setValue(height);
                String shou = sh.getText().toString();
                Firebase childRef3 = mRef.child("shoulder");
                childRef3.setValue(shou);
                String chest = ch.getText().toString();
                Firebase childRef4 = mRef.child("chest");
                childRef4.setValue(chest);
                String waist = wa.getText().toString();
                Firebase childRef5 = mRef.child("waist");
                childRef5.setValue(waist);
                String hip2 = hip.getText().toString();
                Firebase childRef6 = mRef.child("hip");
                childRef6.setValue(hip2);
                String inseam = ins.getText().toString();
                Firebase childRef7 = mRef.child("inseam");
                childRef7.setValue(inseam);

                Thread thr = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebServiceCall wsc = new WebServiceCall();
                        JSONObject jsnObj = new JSONObject();
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        jsnObj = wsc.makeHttpRequest(wsc.fnGetURL(), "GET", params);
                        Log.d("respJSN", jsnObj.toString());
                    }
                });
                thr.start();

                Intent intent = new Intent(MainActivity.this, Progress.class);
                startActivity(intent);
            }
        }

}
