package com.proncan.tarot;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadActivity extends AppCompatActivity {
    Intent intent;

    //    static final String FTP_HOST = "192.168.0.247";
//    static final String FTP_USER = "cardtest";
//    static final String FTP_PASS = "@cardtest123";
    private FTPClient mFtp;

    //    String[] FileName = {"tarot_data.csv", "tarot78data.csv"};
    String FileName = "tarot_data.csv";
    String FileName2 = "tarot78data.csv";
    String FileExtend = ".csv";
    //    String fileURL = "http://192.168.0.247/cardtest/ftp/files/";
    String fileURL = "https://cardtarot.dnew.co.kr/files";
    String SavePath;
    String SaveFolder = "/files";

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        // request callback
        startApp();
        nextActivity();
    }

    void startApp() {
        SavePath = getFilesDir().getPath() + SaveFolder;

        DownloadThread mThread = new DownloadThread(fileURL + "/" + FileName, SavePath, FileName);
        DownloadThread mThread2 = new DownloadThread(fileURL + "/" + FileName2, SavePath, FileName2);
        File dir = new File(SavePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        mThread.start();
        mThread2.start();
    }

    void nextActivity() {
        intent = new Intent(DownloadActivity.this, LoadingActivity.class);
        startActivity(intent);
        finish();
    }


    class DownloadThread extends Thread {
        String ServerUrl;
        String LocalPath;
        String fileName;

        DownloadThread(String serverPath, String localPath, String fName) {
            ServerUrl = serverPath;
            LocalPath = localPath;
            fileName = fName;
        }

        @Override
        public void run() {
            URL url;
            int Read;
            try {
                url = new URL(ServerUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
//                int len = conn.getContentLength();
                byte[] tempByte = new byte[1024 * 3];
                InputStream is = conn.getInputStream();
                File file = new File(LocalPath + "/" + fileName);
                String TAG = "LoadingActivity";
                Log.i(TAG, "run: " + file.toString());
                FileOutputStream os = new FileOutputStream(file);
                for (; ; ) {
                    Read = is.read(tempByte);
                    if (Read <= 0) {
                        break;
                    }
                    os.write(tempByte, 0, Read);
                }
                is.close();
                os.close();
                conn.disconnect();
            } catch (Exception e) {
                Log.e("filedownloaderror", e.getMessage());
            }
            Log.i("완료", "Thread 완료");
        }
    }
}

