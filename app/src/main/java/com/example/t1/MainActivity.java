package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.entity.mime.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

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

         */


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

    }
}