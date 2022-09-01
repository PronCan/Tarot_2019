package com.proncan.tarot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnToday, btnEtc, btnSelect;
    TextView tvToday, tvEtc, tvSelect;
    Intent intent;
    ArrayList<Data> dataList;
    ArrayList<AllData> allDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = (ArrayList<Data>)getIntent().getSerializableExtra("cardData");
        allDataList = (ArrayList<AllData>)getIntent().getSerializableExtra("cardAllData");

        btnToday = findViewById(R.id.btn_today);
        btnEtc = findViewById(R.id.btn_etc);
        btnSelect = findViewById(R.id.btn_select);

        tvToday = btnToday.findViewById(R.id.btn_content);
        tvEtc = btnEtc.findViewById(R.id.btn_content);
        tvSelect = btnSelect.findViewById(R.id.btn_content);
        tvToday.setText("오늘의 운세");
        tvEtc.setText("고민 타로");
        tvSelect.setText("양자택일 타로");

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SelectCardTodayActivity.class);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });

        btnEtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SelectCardEtcMainActivity.class);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SelectCardYesNoActivity.class);
                intent.putExtra("cardData", allDataList);
                startActivity(intent);
            }
        });
    }

}

