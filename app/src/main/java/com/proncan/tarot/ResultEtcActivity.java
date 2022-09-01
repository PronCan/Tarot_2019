package com.proncan.tarot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.imageId;


public class ResultEtcActivity extends AppCompatActivity {
    ImageView cardImg;
    TextView cardName, cardComment;
    Button endBtn;

    ArrayList<Data> dataList;
    int type;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        cardImg = findViewById(R.id.result_card_img);
        cardName = findViewById(R.id.result_card_name);
        cardComment = findViewById(R.id.result_card_comment);
        endBtn = findViewById(R.id.btn_result);

        // 카드 정보 가져오기
        dataList = (ArrayList<Data>)getIntent().getSerializableExtra("cardData");
        type = getIntent().getIntExtra("type", 1);
        index = getIntent().getIntExtra("index", 0);

//        Log.d("datalist%%%", "onCreate: " + dataList.get(0).toString());

        cardImg.setImageResource(imageId[index]);
        switch(type) {
            case 1:
                cardName.setText(dataList.get(index).getCardName() + "(" + dataList.get(index).getCardEngName() + ")");
                cardComment.setText(dataList.get(index).getComment1());
                break;
            case 2:
                cardName.setText(dataList.get(index).getCardName() + "(" + dataList.get(index).getCardEngName() + ")");
                cardComment.setText(dataList.get(index).getComment2());
                break;
            case 3:
                cardName.setText(dataList.get(index).getCardName() + "(" + dataList.get(index).getCardEngName() + ")");
                cardComment.setText(dataList.get(index).getComment3());
                break;
            case 4:
                cardName.setText(dataList.get(index).getCardName() + "(" + dataList.get(index).getCardEngName() + ")");
                cardComment.setText(dataList.get(index).getComment4());
                break;
            case 5:
                cardName.setText(dataList.get(index).getCardName() + "(" + dataList.get(index).getCardEngName() + ")");
                cardComment.setText(dataList.get(index).getComment5());
                break;
        }

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
