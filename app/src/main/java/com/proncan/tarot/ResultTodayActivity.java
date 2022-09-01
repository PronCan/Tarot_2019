package com.proncan.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.imageId;

public class ResultTodayActivity extends AppCompatActivity {
    ImageView card1Img, card2Img, card3Img;
    TextView card1Name, card1Comment;
    TextView card2Name, card2Comment;
    TextView card3Name, card3Comment;
    Intent intent;
    Button endBtn;

    View resCard1, resCard2, resCard3;

    ArrayList<Data> dataList;
    int[] idxNum, check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_today_view);
        resCard1 = findViewById(R.id.result_card1);
        resCard2 = findViewById(R.id.result_card2);
        resCard3 = findViewById(R.id.result_card3);
        card1Img = resCard1.findViewById(R.id.result_card_img);
        card2Img = resCard2.findViewById(R.id.result_card_img);
        card3Img = resCard3.findViewById(R.id.result_card_img);
        card1Name = resCard1.findViewById(R.id.result_card_name);
        card2Name = resCard2.findViewById(R.id.result_card_name);
        card3Name = resCard3.findViewById(R.id.result_card_name);
        card1Comment = resCard1.findViewById(R.id.result_card_comment);
        card2Comment = resCard2.findViewById(R.id.result_card_comment);
        card3Comment = resCard3.findViewById(R.id.result_card_comment);

        endBtn = findViewById(R.id.btn_result);

        // 카드 정보 가져오기
        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("cardData");
        idxNum = getIntent().getIntArrayExtra("idxNum");
        check = getIntent().getIntArrayExtra("check");

        card1Img.setImageResource(imageId[idxNum[0]]);
        card2Img.setImageResource(imageId[idxNum[1]]);
        card3Img.setImageResource(imageId[idxNum[2]]);

        card1Name.setText(dataList.get(idxNum[0]).getCardName() + "(" + dataList.get(idxNum[0]).getCardEngName() + ")");
        card2Name.setText(dataList.get(idxNum[1]).getCardName() + "(" + dataList.get(idxNum[1]).getCardEngName() + ")");
        card3Name.setText(dataList.get(idxNum[2]).getCardName() + "(" + dataList.get(idxNum[2]).getCardEngName() + ")");

        String str1 = dataList.get(idxNum[0]).getComment5();
        String str2 = dataList.get(idxNum[1]).getComment5();
        String str3 = dataList.get(idxNum[2]).getComment5();
        if (check[0] == 1) {
            card1Img.setRotation(180);
            str1 += " 하지만 카드가 뒤집어져 있습니다." + dataList.get(idxNum[0]).getComment5_rev();
        }
        if (check[1] == 1) {
            card2Img.setRotation(180);
            str2 += " 하지만 카드가 뒤집어져 있습니다." + dataList.get(idxNum[1]).getComment5_rev();
        }
        if (check[2] == 1) {
            card3Img.setRotation(180);
            str3 += " 하지만 카드가 뒤집어져 있습니다." + dataList.get(idxNum[2]).getComment5_rev();
        }
        card1Comment.setText(str1);
        card2Comment.setText(str2);
        card3Comment.setText(str3);

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
