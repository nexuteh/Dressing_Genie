package com.dressingGenie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.Map;


public class Analysis_m extends Activity {
    Firebase mRef ;
    String bshape,usize,lsize,height,shoulder,chest,waist,hip,inseam,shape,upper,lower;
    int psize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_m);
        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://dressinggenie.firebaseio.com/user");
         mRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                 Map<String, String> map = dataSnapshot.getValue(Map.class);
                 String gender=null;
                 if(map.get("gender").equals("1")){
                     gender = "Male" ;
                 }

                 height = map.get("height");
                 shoulder = map.get("shoulder");
                 chest = map.get("chest");
                 waist = map.get("waist");
                 hip = map.get("hip");
                 inseam = map.get("inseam");
                 shape = map.get("bodyShape");
                 upper = map.get("upperSize");
                 lower = map.get("lowerSize");
                 psize = (int) Math.round(Integer.parseInt(waist)/2.54);

                 TextView txtGender = (TextView) findViewById(R.id.textgender);
                 TextView txtHeight = (TextView) findViewById(R.id.textHeight);
                 TextView txtShoulder = (TextView) findViewById(R.id.textShoulder);
                 TextView txtChest = (TextView) findViewById(R.id.textChest);
                 TextView txtWaist = (TextView) findViewById(R.id.textWaist);
                 TextView txtHip = (TextView) findViewById(R.id.textHip);
                 TextView txtInseam = (TextView) findViewById(R.id.textInseam);
                 TextView txtShape = (TextView) findViewById(R.id.textshape);
                 TextView txtSize = (TextView) findViewById(R.id.textsize);
                 TextView txtSize2 = (TextView) findViewById(R.id.textsize2);

                 txtGender.setText(gender);
                 txtHeight.setText(String.valueOf(height)+" cm");
                 txtShoulder.setText(String.valueOf(shoulder)+" cm");
                 txtChest.setText(String.valueOf(chest)+" cm");
                 txtWaist.setText(String.valueOf(waist)+" cm");
                 txtHip.setText(String.valueOf(hip)+" cm");
                 txtInseam.setText(String.valueOf(inseam)+" cm");

                 if(shape.equals("[1]")) {
                     bshape = "Trapezoid or Hourglass\n(S/X Shaped)";
                 }else if(shape.equals("[2]")) {
                     bshape = "Rectangle or Straight or Column\n(H/I Shaped)";
                 }else if(shape.equals("[3]")) {
                     bshape = "Inverted Triangle or Strawberry\n(T/V/Y Shaped)";
                 }else if(shape.equals("[4]")) {
                     bshape = "Triangle or Pear\n(A Shaped)";
                 }else{
                     bshape = "Oval or Apple\n(O Shaped)";
                 }
                 txtShape.setText(bshape);

                 if(upper.equals("[1]")) {
                     usize = "XS";
                 }else if(upper.equals("[2]")) {
                     usize = "S";
                 }else if(upper.equals("[3]")) {
                     usize = "M";
                 }else if(upper.equals("[4]")) {
                     usize = "L";
                 }else if(upper.equals("[5]")) {
                     usize = "XL";
                 }else if(upper.equals("[6]")) {
                     usize = "XXL";
                 }else{
                     usize = "XXXL";
                 }
                 txtSize.setText(usize);

                 if(lower.equals("[1]")) {
                     lsize = "XS";
                 }else if(lower.equals("[2]")) {
                     lsize = "S";
                 }else if(lower.equals("[3]")) {
                     lsize = "M";
                 }else if(lower.equals("[4]")) {
                     lsize = "L";
                 }else if(lower.equals("[5]")) {
                     lsize = "XL";
                 }else if(lower.equals("[6]")) {
                     lsize = "XXL";
                 }else{
                     lsize = "XXXL";
                 }
                 txtSize2.setText(lsize+" | "+psize+'"');
             }

             @Override
             public void onCancelled(FirebaseError firebaseError) {
                 System.out.println("The read failed: " + firebaseError.getCode());
             }
        });

    }
}
