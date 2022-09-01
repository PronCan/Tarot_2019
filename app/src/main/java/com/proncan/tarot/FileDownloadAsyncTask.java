package com.proncan.tarot;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class FileDownloadAsyncTask extends AsyncTask<Long, Integer, String> {
    static final String FTP_HOST = "192.168.0.247";
    static final String FTP_USER = "cardtest";
    static final String FTP_PASS = "@cardtest123";

    private FTPClient mFtp;
    private String DIR = "/data/data/com.example.httpurlconnection/files/";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Long... voids) {

        if (this.isCancelled()) {
            return null;
        }
        getFileFromFTP();

        publishProgress(100);
        return null;
    }

    //FTP 연결 및 데이터 가져오는 기능 담당
    public void getFileFromFTP() {
        if (!FTPConnect(FTP_HOST)) {
//            msgBox("FTP 연결 실패");
            System.out.println("FTP 연결 실패");
            return;
        }
        if (!FTPLogin(FTP_USER, FTP_PASS)) {
//            msgBox("FTP 로그인 실패");
            Log.d("FTP", "getFileFromFTP: FTP 로그인 실패");
            FTPClose();
            return;
        }
        if (!FTPGetFile()) {
//            msgBox("FTP 파일 가져오기 실패");
            Log.d("FTP", "getFileFromFTP: FTP 파일 가져오기 실패");
            FTPClose();
            return;
        }
//        msgBox("성공");
        Log.d("FTP", "getFileFromFTP: 성공");
    }

    public boolean FTPConnect(String host) {
        try {
            mFtp = new FTPClient();
            mFtp.connect(host);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean FTPLogin(String Id, String Pw) {
        try {
            if (!mFtp.login(Id, Pw))
                return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean FTPClose() {
        try {
            mFtp.disconnect();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //파일 가져오는 부분
    public boolean FTPGetFile() {
        FileOutputStream os;
        File f;
        try {
            mFtp.changeWorkingDirectory("/cardtest/ftp/files/");
            //파일 전체 가져오기(순서대로)
            FTPFile files[] = mFtp.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().equals("tarot_data.csv") || files[i].getName().equals("tarot78data.csv")) {
                    f = new File(DIR + files[i].getName());
                    os = new FileOutputStream(f);
                    mFtp.retrieveFile(files[i].getName(), os);
                    Log.d(TAG, "FTPGetFile: " + DIR + files[i].getName() + "을저장했다");
                    os.close();
                }
            }
        } catch (IOException e) {
//            msgBox(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
