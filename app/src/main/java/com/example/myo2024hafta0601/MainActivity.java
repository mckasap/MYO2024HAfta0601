package com.example.myo2024hafta0601;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
ImageView iv;
    public class MyTask extends AsyncTask<String, Void,String>{


        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("AsynchTask","PreExecute");
        }

        @Override
        protected String doInBackground(String... strings) {
            //for (String s :strings)
              //      Log.d("AsynchTask",s);
            try {
                Document doc =  Jsoup.connect(strings[0]).get();
                return doc.title();
            } catch (IOException e) {
                return "FAiled";
            }catch ( RuntimeException ex){

                return "FAiled";
            }


        }
    }

    public class myImageDownloader extends  AsyncTask<String,Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {
            String url="http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg";
            try {
                URL url1= new URL(url);
                HttpURLConnection con=(HttpURLConnection) url1.openConnection();
                con.connect();
                InputStream in =con.getInputStream();
                Bitmap bmp= BitmapFactory.decodeStream(in);
                return bmp;

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }


    public void Download(View V) {
        myImageDownloader downloader= new myImageDownloader();
        try {
            Bitmap bmp= downloader.execute().get();
            iv.setImageBitmap(bmp);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.imageView);

        MyTask theTask = new MyTask();


        try {
            String str= theTask.execute("https://www.turkiye.gov.tr").get();
            Log.d("MAIN_TITLE",str);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}