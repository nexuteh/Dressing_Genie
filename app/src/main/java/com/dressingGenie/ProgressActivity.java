package com.dressingGenie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

import static com.dressingGenie.ImageController.saveBitmap;
import static com.dressingGenie.WebServiceCall.detectHumanBody;
import static com.dressingGenie.WebServiceCall.detectHumanHead;

public class ProgressActivity extends AppCompatActivity{

    private RingProgressBar progressBar1;
    private Handler myHandler;
    int progress = 0;
    double head,height,shoulder,chest,waist,hip,inseam,chestSide,waistSide,hipSide,imageHeight,imageRatio,heightUser = 0;
    public String heightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar1 = findViewById(R.id.progress1);
        heightInput = getIntent().getExtras().getString("Height","defaultKey");
        if (heightInput==null) throw new AssertionError("Invalid Bitmap");
        heightUser = Double.valueOf(heightInput);
        ringProgress();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                String firstBase64 = detectHumanBody("firstImage");
                Double humanChin = detectHumanHead("firstImage");

                byte[] firstDecodedString = Base64.decode(firstBase64, Base64.DEFAULT);
                Bitmap firstDecodedByte = BitmapFactory.decodeByteArray(firstDecodedString, 0, firstDecodedString.length);

                int[] pixels = new int[firstDecodedByte.getHeight() * firstDecodedByte.getWidth()];
                firstDecodedByte.getPixels(pixels, 0, firstDecodedByte.getWidth(), 0, 0, firstDecodedByte.getWidth(), firstDecodedByte.getHeight());
                imageHeight = firstDecodedByte.getHeight();
                imageRatio = heightUser/imageHeight;

                try {
                    saveBitmap(firstDecodedByte, "firstResult");
                    int pixelArray[][] = pixelArray(firstDecodedByte);
                    head = getHead(pixelArray,"firstImage", humanChin);
                    height = getHumanHeight(pixelArray);
                    shoulder = getShoulder(pixelArray);
                } catch (IOException ex) {
                    System.out.println("Could not find file.");
                }
            }
        };

        final Runnable run1 = new Runnable(){
            @Override
            public void run() {
                String secondBase64 = detectHumanBody("secondImage");
                Double humanChin = detectHumanHead("secondImage");

                byte[] secondDecodedString = Base64.decode(secondBase64, Base64.DEFAULT);
                Bitmap secondDecodedByte = BitmapFactory.decodeByteArray(secondDecodedString, 0, secondDecodedString.length);

                int[] pixels = new int[secondDecodedByte.getHeight() * secondDecodedByte.getWidth()];
                secondDecodedByte.getPixels(pixels, 0, secondDecodedByte.getWidth(), 0, 0, secondDecodedByte.getWidth(), secondDecodedByte.getHeight());

                try {
                    saveBitmap(secondDecodedByte, "secondResult");
                    int pixelArray[][] = pixelArray(secondDecodedByte);
                    head = getHead(pixelArray,"secondImage", humanChin);
                    chestSide = getChest(pixelArray, (int)Math.round(head));
                    waistSide = getWaist(pixelArray, (int)Math.round(head));
                    hipSide = getHip(pixelArray, (int)Math.round(head));
                    inseam = getInseam((int)Math.round(head), (int)height);
                } catch (IOException ex) {
                    System.out.println("Could not find file.");
                }

            }
        };

        final Runnable run2 = new Runnable(){
            @Override
            public void run() {
                String thirdBase64 = detectHumanBody("thirdImage");
                Double humanChin = detectHumanHead("thirdImage");

                byte[] thirdDecodedString = Base64.decode(thirdBase64, Base64.DEFAULT);
                Bitmap thirdDecodedByte = BitmapFactory.decodeByteArray(thirdDecodedString, 0, thirdDecodedString.length);

                int[] pixels = new int[thirdDecodedByte.getHeight() * thirdDecodedByte.getWidth()];
                thirdDecodedByte.getPixels(pixels, 0, thirdDecodedByte.getWidth(), 0, 0, thirdDecodedByte.getWidth(), thirdDecodedByte.getHeight());

                try {
                    saveBitmap(thirdDecodedByte, "thirdResult");
                    int pixelArray[][] = pixelArray(thirdDecodedByte);
                    head = getHead(pixelArray,"thirdImage", humanChin);
                    chest = getChest(pixelArray, (int)Math.round(head));
                    waist = getWaist(pixelArray, (int)Math.round(head));
                    hip = getHip(pixelArray, (int)Math.round(head));
                } catch (IOException ex) {
                    System.out.println("Could not find file.");
                }

                Intent myIntent = new Intent(ProgressActivity.this,MainActivity.class);
                myIntent.putExtra("Height", heightInput);
                myIntent.putExtra("Shoulder",String.valueOf((int) Math.round(shoulder*2*imageRatio*1.2)));
                myIntent.putExtra("Chest",String.valueOf((int) Math.round(((chest*2*0.9)+(chestSide*2*0.9))*imageRatio)));
                myIntent.putExtra("Waist",String.valueOf((int) Math.round(((waist*2*0.9)+(waistSide*2*0.8))*imageRatio)));
                myIntent.putExtra("Hip",String.valueOf((int) Math.round((((hip*2*0.9)+(hipSide*2*0.7)))*imageRatio)));
                myIntent.putExtra("Inseam",String.valueOf((int) Math.round(inseam*imageRatio)));
                ProgressActivity.this.startActivity(myIntent);
            }
        };

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(run);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                final Thread thread1 = new Thread(run1);
                thread1.start();
            }
        }, 10000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                final Thread thread2 = new Thread(run2);
                thread2.start();
            }
        }, 15000);

    }

    public void ringProgress() {
        myHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 0) {
                    if (progress < 100) {
                        progress++;
                            progressBar1.setProgress(progress);
                    }
                }
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(600);
                        myHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static int[][] pixelArray(Bitmap src){

        int width = src.getWidth();
        int height = src.getHeight();
        int R, G, B;
        int pixel;

        int[][] pixelArray = new int[width][height];
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {

                pixel = src.getPixel(x, y);

                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                int pixelXY = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);

                // use 128 as threshold, above -> white, below -> black
                if (pixelXY > 100) {
                    pixelXY = 1;
                }
                else{
                    pixelXY = 0;
                }
                // set new pixel color to array
                pixelArray[x][y] = pixelXY;
            }
        }
        return pixelArray;
    }

    public double getHead(int[][] pixelArray,String imageName, double humanChin){

        //Remember to change back to JPG
        String imagePath = Environment.getExternalStorageDirectory()+"/"+imageName+".png";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imagePath, options);
        int heightColor = options.outHeight;
        double heightColor2 = (double) heightColor;

        double width = pixelArray.length;
        double height = pixelArray[0].length;

        double blackToColorRatio = height / heightColor2;
        double headSize=0;
        double chinValue;

        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                if(pixelArray[y][x] == 1){
                    chinValue = x;
                    headSize = (humanChin * blackToColorRatio) - chinValue;
                    break;
                }
            }
            if(headSize>0){
                break;
            }
        }
        return headSize;
    }

    public double getHumanHeight(int[][] pixelArray){
        int width = pixelArray.length;
        int height = pixelArray[0].length;
        double humanHeight=0;

        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                if(pixelArray[y][x] == 1){
                    humanHeight = humanHeight + 1;
                    break;
                }
            }
        }
        return humanHeight;
    }

    public double getShoulder(int[][] pixelArray){
        int width = pixelArray.length;
        int height = pixelArray[0].length;
        double shoulderSize=0;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if(pixelArray[x][y] == 1){
                    shoulderSize = shoulderSize + 1;
                    break;
                }
            }
        }
        return shoulderSize;
    }

    public double getChest(int[][] pixelArray, int headSize){
        double chestSize=0;
        double upperHead = getUpperHead(pixelArray);
        int arrayRow = (int)((headSize * 1.9)+upperHead);

        for (int j = 0; j < pixelArray.length; j++){
            if(pixelArray[j][arrayRow] == 1){
                chestSize = chestSize + 1;
            }
        }

        return chestSize;
    }

    public double getWaist(int[][] pixelArray, int headSize){
        double waistSize=0;
        double upperHead = getUpperHead(pixelArray);
        int arrayRow = (int)((headSize * 2.7)+upperHead);

        for (int j = 0; j < pixelArray.length; j++){
            if(pixelArray[j][arrayRow] == 1){
                waistSize = waistSize + 1;
            }
        }

        return waistSize;
    }

    public double getHip(int[][] pixelArray, int headSize){
        double hipSize=0;
        double upperHead = getUpperHead(pixelArray);
        int arrayRow = (int)((headSize * 3.25)+upperHead);

        for (int j = 0; j < pixelArray.length; j++){
            if(pixelArray[j][arrayRow] == 1){
                hipSize = hipSize + 1;
            }
        }

        return hipSize;
    }

    public double getInseam(int headSize, int height){
        int result = (int)((headSize * 3.25));
        return height-result;
    }

    public double getUpperHead(int[][] pixelArray){
        double width = pixelArray.length;
        double height = pixelArray[0].length;
        double upperHead=0;

        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                if(pixelArray[y][x] == 1){
                    upperHead = x;
                    break;
                }
            }
            if(upperHead>0){
                break;
            }
        }
        return upperHead;
    }
}