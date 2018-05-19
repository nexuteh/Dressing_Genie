package com.dressingGenie;

import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebServiceCall {

    JSONObject jsonObj ;
    String strUrl  = "" ;

    public WebServiceCall()
    {
        jsonObj = null;
        strUrl = "http://rku.utem.edu.my/webservicejson/trigger.php";
       // strUrl ="http://192.168.1.36:80/webservicejson/trigger.php";
    }

    public String fnGetURL()
    {
        return strUrl;
    }

    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params)
    {
        InputStream is = null;
        String json = "";
        JSONObject jObj = null;
        // Making HTTP request

        try {
            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

            jObj = new JSONObject(json);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return jObj;
    }

    public static String detectHumanBody(String imageName) {

        OkHttpClient client = new OkHttpClient();
        final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");

        File file= new File(Environment.getExternalStorageDirectory()+"/"+imageName+".png");

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("api_key", "")
                .addFormDataPart("api_secret", "")
                .addFormDataPart("image_file", imageName+".jpg", RequestBody.create(MEDIA_TYPE_JPG, file))
                //.addFormDataPart("image_url", "http://hairremovalmanhattan.com/wp-content/uploads/2014/06/man-front1.png")
                .build();

        Request request = new Request.Builder()
                .url("https://api-us.faceplusplus.com/humanbodypp/v1/segment")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jObj = new JSONObject(response.body().string());
            String result = jObj.getString("result");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static double detectHumanHead(String imageName) {

        OkHttpClient client = new OkHttpClient();
        final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
        String imagePath = Environment.getExternalStorageDirectory()+"/"+imageName+".png";

        File file= new File(imagePath);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imagePath, options);
        int height = options.outHeight;
        double height2 = (double) height;

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("api_key", "")
                .addFormDataPart("api_secret", "")
                .addFormDataPart("image_file", imageName+".jpg", RequestBody.create(MEDIA_TYPE_JPG, file))
                //.addFormDataPart("image_url", "http://hairremovalmanhattan.com/wp-content/uploads/2014/06/man-front1.png")
                .addFormDataPart("return_landmark","1")
                .build();

        Request request = new Request.Builder()
                .url("https://api-us.faceplusplus.com/facepp/v3/detect")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject jObj = new JSONObject(response.body().string());
            JSONArray jArr = jObj.getJSONArray("faces");
            JSONObject jObj3 = (JSONObject) jArr.get(0);
            JSONObject jObj4 = jObj3.getJSONObject("landmark").getJSONObject("contour_chin");
            String result = jObj4.getString("y");
            double finalResult = Double.parseDouble(result);
            return finalResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
