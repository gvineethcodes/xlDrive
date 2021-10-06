package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.entity.mime.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Workbook workbook;
    AsyncHttpClient asyncHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        mediaPlayer = new MediaPlayer();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
        }
        try {
            mediaPlayer.setDataSource("https://drive.google.com/uc?id=0B49pAHfFJUpASjNmanVmYTZLbHM&export=download&resourcekey=0-FPnH0HG5uAnbLSk56AATZw");
            Log.e("log","1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        Log.e("log","2");

        mediaPlayer.setOnPreparedListener(mediaPlayer -> {
            //mediaPlayer.start();
            Log.e("log","3");

        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Log.e("log","4");
                return false;
            }
        });




        String URL = "https://github.com/gvineethcodes/drive/raw/main/xlssheet.xls";
        //String URL = "https://bikashthapa01.github.io/excel-reader-android-app/story.xls";


        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(URL, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, File file) {
                Toast.makeText(MainActivity.this, "Error in Downloading Excel File", Toast.LENGTH_SHORT).show();
                Log.e("mylog","1");
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, File file) {

                Log.e("mylog","2");

                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null){

                    try {
                        workbook = Workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);

                        for(int i = 0;i< sheet.getRows();i++){
                            Cell[] row = sheet.getRow(i);
                            Log.e("mylog",row[0].getContents());
                            Log.e("mylog",row[1].getContents());
                            Log.e("mylog",row[2].getContents());
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }

        });




        try {
        String url = "https://docs.google.com/spreadsheets/d/1VxQruR4Yt1Ive6qLqQZ2iV7qCr4x9GRL49yA9XM5GP8/export?format=csv";
            CSVReader reader = new CSVReader(new FileReader("https://docs.google.com/spreadsheets/d/1VxQruR4Yt1Ive6qLqQZ2iV7qCr4x9GRL49yA9XM5GP8/export?format=csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                Log.e("mylogS",nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (IOException e) {
            Log.e("mylogE",e.getMessage());
        }


         */

        class ReadFile extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                HttpURLConnection conn = null;
                try {
                    String urlString = "https://docs.google.com/spreadsheets/d/1VxQruR4Yt1Ive6qLqQZ2iV7qCr4x9GRL49yA9XM5GP8/export?format=csv";

                    URL url = new URL(urlString);
                    conn = (HttpURLConnection) url.openConnection();
                    InputStream in = conn.getInputStream();
                    if(conn.getResponseCode() == 200)
                    {
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String inputLine;
                        while ((inputLine = br.readLine()) != null) {
                            Log.e("mylog",inputLine);
                        }
                    }

                }catch (Exception e){
                    Log.e("mylog", e.toString());
                }
                finally
                {
                    if(conn!=null)
                        conn.disconnect();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {

            }

            @Override
            protected void onPreExecute() {}
        }

        new ReadFile().execute("");

    }
}