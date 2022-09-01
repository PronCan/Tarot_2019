package com.proncan.tarot;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.opencsv.CSVReader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {
    static String TAG = "LoadingActivity";
    static int[] imageId;
    static int[] allImageId;
    static JSONObject object;  // 최종 obj
    JSONArray array;    // data의 json 정보
    JSONObject dataInfo;    // 하나
    ArrayList<Data> dataList;

    static JSONObject allObject;  // 최종 obj
    JSONArray allArray;    // data의 json 정보
    JSONObject allDataInfo;    // 하나
    ArrayList<AllData> allDataList;

    Intent intent;
    Snackbar snackbar;

    String[] FileName = {"tarot_data.csv", "tarot78data.csv"};
    String SavePath;
    String SaveFolder = "/files";

//    static final String FTP_HOST = "192.168.0.247";
//    static final String FTP_USER = "cardtest";
//    static final String FTP_PASS = "@cardtest123";
//    private FTPClient mFtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SavePath = getFilesDir().getPath() + SaveFolder;

        setImageResource();
        readData();
        readAllData();
        Log.e("DOWNLOADACTIVITY%%", dataList.toString());
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("cardData", dataList);
        intent.putExtra("cardAllData", allDataList);
        Log.i(TAG, "onCreate: " + dataList.toString());
        Log.i(TAG, "onCreate: " + allDataList.toString());

        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if (first == false) {
            Log.d(TAG, "onCreate: firstStart");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
            View layout = findViewById(R.id.loading_activity);
            snackbar = Snackbar.make(layout, "다운로드 적용을 위해 앱을 재실행 합니다.", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("확인", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartApp();
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        } else {
            startActivity(intent);
            finish();
        }
    }

    public void restartApp() {
        Intent i = getBaseContext().getPackageManager().
                getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        stopService(i);
        startActivity(i);
        finish();
    }

    public void readData() {
        object = new JSONObject();
        array = new JSONArray();
        dataList = new ArrayList<>();
        try {
            BufferedReader is = new BufferedReader(new FileReader(SavePath + "/" + FileName[0]));
//            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.tarot_data));
            CSVReader reader = new CSVReader(is, ',', '"', 0);
            String[] data;
            int i = 0;
            Log.e("LOGDATA%%", "data = reader.readNext() : " + reader.readNext().toString());
            while ((data = reader.readNext()) != null) {
                dataInfo = new JSONObject();

                dataInfo.put("cardNo", data[0]);
                dataInfo.put("cardName", data[1]);
                dataInfo.put("cardEngName", data[2]);
                dataInfo.put("keyword", data[3]);
                dataInfo.put("comment1", data[4]);
                dataInfo.put("comment2", data[5]);
                dataInfo.put("comment3", data[6]);
                dataInfo.put("comment4", data[7]);
                dataInfo.put("comment5", data[8]);
                dataInfo.put("comment1_rev", data[9]);
                dataInfo.put("comment2_rev", data[10]);
                dataInfo.put("comment3_rev", data[11]);
                dataInfo.put("comment4_rev", data[12]);
                dataInfo.put("comment5_rev", data[13]);
                Data cardData = new Data(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                        data[9], data[10], data[11], data[12], data[13]);
                dataList.add(cardData);

                array.put(i, dataInfo);
                i++;
            }
            object.put("data", array);

            if (reader != null)
                reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAllData() {
        allObject = new JSONObject();
        allArray = new JSONArray();
        allDataList = new ArrayList<>();

        try {
            BufferedReader is = new BufferedReader(new FileReader(SavePath + "/" + FileName[1]));
//            InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.tarot78data));
            CSVReader reader = new CSVReader(is, ',', '"', 0);
            String[] data;

            int i = 0;
            while ((data = reader.readNext()) != null) {
                allDataInfo = new JSONObject();

                allDataInfo.put("cardNo", data[0]);
                allDataInfo.put("cardName", data[1]);
                allDataInfo.put("cardEngName", data[2]);
                allDataInfo.put("keyword", data[3]);
                allDataInfo.put("keyword_rev", data[4]);
                allDataInfo.put("desc", data[5]);
                allDataInfo.put("comment", data[6]);
                allDataInfo.put("comment_rev", data[7]);
                allDataInfo.put("positive", data[8]);
                allDataInfo.put("positive_rev", data[9]);
                AllData cardData = new AllData(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
                allDataList.add(cardData);

                allArray.put(i, allDataInfo);
                i++;
            }
            allObject.put("data", allArray);

            if (reader != null)
                reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setImageResource() {
        imageId = new int[]{R.drawable.magician01, R.drawable.highpriestess02, R.drawable.empress03, R.drawable.emperor04, R.drawable.hierophant05,
                R.drawable.lovers06, R.drawable.chariot07, R.drawable.strength08, R.drawable.hermit09, R.drawable.wheeloffortune10, R.drawable.justice11,
                R.drawable.hangedman12, R.drawable.death13, R.drawable.temperance14, R.drawable.devil15, R.drawable.tower16, R.drawable.star17,
                R.drawable.moon18, R.drawable.sun19, R.drawable.judgement20, R.drawable.world21, R.drawable.fool00};

        allImageId = new int[]{R.drawable.magician01, R.drawable.highpriestess02, R.drawable.empress03, R.drawable.emperor04, R.drawable.hierophant05,
                R.drawable.lovers06, R.drawable.chariot07, R.drawable.strength08, R.drawable.hermit09, R.drawable.wheeloffortune10, R.drawable.justice11,
                R.drawable.hangedman12, R.drawable.death13, R.drawable.temperance14, R.drawable.devil15, R.drawable.tower16, R.drawable.star17,
                R.drawable.moon18, R.drawable.sun19, R.drawable.judgement20, R.drawable.world21, R.drawable.fool00,
                R.drawable.aceofwands22, R.drawable.twoofwands23, R.drawable.threeofwands24, R.drawable.fourofwands25, R.drawable.fiveofwands26,
                R.drawable.sixofwands27, R.drawable.sevenofwands28, R.drawable.eightofwands29, R.drawable.nineofwands30, R.drawable.tenofwands31,
                R.drawable.pageofwands32, R.drawable.knightofwands33, R.drawable.queenofwands34, R.drawable.kingofwands35,
                R.drawable.aceofpentacles36, R.drawable.twoofpentacles37, R.drawable.threeofpentacles38, R.drawable.fourofpentacles39, R.drawable.fiveofpentacles40,
                R.drawable.sixofpentacles41, R.drawable.sevenofpentacles42, R.drawable.eightofpentacles43, R.drawable.nineofpentacles44, R.drawable.tenofpentacles45,
                R.drawable.pageofpentacles46, R.drawable.knightofpentacles47, R.drawable.queenofpentacles48, R.drawable.kingofpentacles49,
                R.drawable.aceofcups50, R.drawable.twoofcups51, R.drawable.threeofcups52, R.drawable.fourofcups53, R.drawable.fiveofcups54,
                R.drawable.sixofcups55, R.drawable.sevenofcups56, R.drawable.eightofcups57, R.drawable.nineofcups58, R.drawable.tenofcups59,
                R.drawable.pageofcups60, R.drawable.knightofcups61, R.drawable.queenofcups62, R.drawable.kingofcups63,
                R.drawable.aceofswords64, R.drawable.twoofswords67, R.drawable.threeofswords66, R.drawable.fourofswords67, R.drawable.fiveofswords68,
                R.drawable.sixofswords69, R.drawable.sevenofswords70, R.drawable.eightofswords71, R.drawable.nineofswords72, R.drawable.tenofswords73,
                R.drawable.pageofswords74, R.drawable.knightofswords75, R.drawable.queenofswords76, R.drawable.kingofswords77};
    }

}

