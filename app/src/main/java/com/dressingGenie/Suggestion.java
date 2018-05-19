package com.dressingGenie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import java.util.Map;

public class Suggestion extends Activity {
    Firebase mRef ;
    ExpandableTextView expandableTextView;
     String description,description2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Firebase.setAndroidContext(this);

        mRef = new Firebase("https://dressinggenie.firebaseio.com/user");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String shape = map.get("bodyShape");

                if(shape.equals("[1]")) {
                    description = "♦&nbsp;&nbsp;Vertical stripes shirts<br/>" +
                                  "♦&nbsp;&nbsp;Suit coat <br/>" +
                                  "♦&nbsp;&nbsp;Typical length and width(around 3-3 ½ cm)<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;neckties <br/>" +
                                  "♦&nbsp;&nbsp;Fit trousers<br/>";
                    description2 = "♦&nbsp;&nbsp;Loose garments<br/>";
                }else if(shape.equals("[2]")) {
                    description = "♦&nbsp;&nbsp;Horizontal stripes and circular necklines<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;shirts<br/>" +
                                  "♦&nbsp;&nbsp;Layered looks clothing <br/>" +
                                  "♦&nbsp;&nbsp;Button-down shirt and fine-gage group neck<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jumper<br>" +
                                  "♦&nbsp;&nbsp;Prints, color pops, and detailing shirts<br/>"+
                                  "♦&nbsp;&nbsp;Organized overcoats and suit coats with<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;structured tailoring<br/>"+
                                  "♦&nbsp;&nbsp;Flawlessly tied or hung scarf<br/>";
                    description2 = "♦&nbsp;&nbsp;Photographic T-shirts<br/>" +
                                   "♦&nbsp;&nbsp;Double-breasted or sleeveless shirts and<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jackets<br/>" +
                                   "♦&nbsp;&nbsp;Layer block colours clothing<br/>";

                }else if(shape.equals("[3]")) {
                    description = "♦&nbsp;&nbsp;Horizontal stripes, slim fit and cotton polo<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;shirts<br/>" +
                                  "♦&nbsp;&nbsp;Regular V-neck T-shirts <br/>" +
                                  "♦&nbsp;&nbsp;Fit coat <br>" +
                                  "♦&nbsp;&nbsp;Unstructured double-breasted jackets<br/>"+
                                  "♦&nbsp;&nbsp;Straight-leg trousers and jeans with larger<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;seat drop<br/>";
                    description2 = "♦&nbsp;&nbsp;Structured tailoring suit coats and jackets<br/>" +
                                    "♦&nbsp;&nbsp;Shoulder line design too wide clothing<br/>" +
                                    "♦&nbsp;&nbsp;Scoop neckline shirts<br/>" +
                                    "♦&nbsp;&nbsp;Skinny cut trousers or jeans<br/>";
                }else if(shape.equals("[4]")) {
                    description = "♦&nbsp;&nbsp;Vertical stripes and brighter colour panels<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;shirts<br/>" +
                                  "♦&nbsp;&nbsp;Tailored patterned blazers<br/>" +
                                  "♦&nbsp;&nbsp;Jackets with structured shoulders <br/>" +
                                  "♦&nbsp;&nbsp;Straight-leg or twill trousers <br/>" +
                                   "♦&nbsp;&nbsp;Single-breasted and button-down suits<br/>";
                    description2 = "♦&nbsp;&nbsp;Horizontal stripes, fitted polo and roll necks<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;shirts<br/>"+
                                   "♦&nbsp;&nbsp;Brighter colors and busy prints shirts<br/>"+
                                    "♦&nbsp;&nbsp;Skinny, heavily tapered and awl pants or<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;jeans<br/> ";
                }else{
                    description = "♦&nbsp;&nbsp;Vertical stripes, solid match and free shirts <br/>" +
                                  "♦&nbsp;&nbsp;More extensive neckline shirts especially<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;wide neck and face<br/>" +
                                  "♦&nbsp;&nbsp;Dull, single-breasted, internal twist and<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;middle an edge coa <br/>" +
                                   "♦&nbsp;&nbsp;Adequately wide neckties <br/>" +
                                   "♦&nbsp;&nbsp;Suspenders<br/>" +
                                   "♦&nbsp;&nbsp;Fit or slightly loose trousers and never<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;belted so tight<br/>" +
                                  "♦&nbsp;&nbsp;Pleats jeans<br/>";
                    description2 = "♦&nbsp;&nbsp;Too tight garments<br/>" +
                                   "♦&nbsp;&nbsp;Horizontal stripes, cowl necks, polo or wide<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;crew necks shirts<br/>" +
                                   "♦&nbsp;&nbsp;Double-breasted jackets<br/>" +
                                   "♦&nbsp;&nbsp;Boots<br/>" +
                                   "♦&nbsp;&nbsp;Necktie that window hangings over the<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;lump of the stomach<br/>"+
                                   "♦&nbsp;&nbsp;Brighter color belts<br/>";
                }

                expandableTextView = (ExpandableTextView)findViewById(R.id.expand_view);
                expandableTextView.setText(Html.fromHtml(description));
                expandableTextView = (ExpandableTextView)findViewById(R.id.expand_view2);
                expandableTextView.setText(Html.fromHtml(description2));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getCode());
            }
        });
        ListView listView= (ListView)findViewById(R.id.list);
        String category[]= new String[] {"Formal","Casual","Recreational","Sports"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.listrow,R.id.text1,category);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    Intent intent = new Intent(view.getContext(),Category.class);
                    intent.putExtra("category","formal");
                    startActivityForResult(intent,0);
                }else if(position == 1){
                    Intent intent = new Intent(view.getContext(),Category.class);
                    intent.putExtra("category","casual");
                    startActivityForResult(intent,1);
                }else if(position == 2){
                    Intent intent = new Intent(view.getContext(),Category.class);
                    intent.putExtra("category","recreational");
                    startActivityForResult(intent,2);
                }else{
                    Intent intent = new Intent(view.getContext(),Category.class);
                    intent.putExtra("category","sports");
                    startActivityForResult(intent,3);
                }
            }
        });



    }
}
