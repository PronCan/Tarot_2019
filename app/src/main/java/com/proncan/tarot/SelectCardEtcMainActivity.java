package com.proncan.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectCardEtcMainActivity extends AppCompatActivity {
    LinearLayout btn1, btn2, btn3, btn4, btn5;    // 애정/직업/건강/취미/기타
    TextView tv_btn1, tv_btn2, tv_btn3, tv_btn4, tv_btn5;    // 애정/직업/건강/취미/기타
    Intent intent;
    ArrayList<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc_main);

        btn1 = findViewById(R.id.etc_main_btn1);
        btn2 = findViewById(R.id.etc_main_btn2);
        btn3 = findViewById(R.id.etc_main_btn3);
        btn4 = findViewById(R.id.etc_main_btn4);
        btn5 = findViewById(R.id.etc_main_btn5);

        tv_btn1 = btn1.findViewById(R.id.custom_button_detail_text);
        tv_btn2 = btn2.findViewById(R.id.custom_button_detail_text);
        tv_btn3 = btn3.findViewById(R.id.custom_button_detail_text);
        tv_btn4 = btn4.findViewById(R.id.custom_button_detail_text);
        tv_btn5 = btn5.findViewById(R.id.custom_button_detail_text);

        tv_btn1.setText("애정");
        tv_btn2.setText("직업");
        tv_btn3.setText("건강");
        tv_btn4.setText("취미");
        tv_btn5.setText("기타");

        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("cardData");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SelectCardEtcMainActivity.this, SelectCardEtc.class);
                intent.putExtra("type", 1);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SelectCardEtcMainActivity.this, SelectCardEtc.class);
                intent.putExtra("type", 2);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SelectCardEtcMainActivity.this, SelectCardEtc.class);
                intent.putExtra("type", 3);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SelectCardEtcMainActivity.this, SelectCardEtc.class);
                intent.putExtra("type", 4);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SelectCardEtcMainActivity.this, SelectCardEtc.class);
                intent.putExtra("type", 5);
                intent.putExtra("cardData", dataList);
                startActivity(intent);
            }
        });

    }
}
