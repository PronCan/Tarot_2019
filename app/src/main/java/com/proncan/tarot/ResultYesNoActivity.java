package com.proncan.tarot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.allImageId;


public class ResultYesNoActivity extends AppCompatActivity {
    ImageView cardImg1, cardImg2;
    TextView cardName1, cardName2, cardComment, cardTopResult;
    Button endBtn;

    ArrayList<AllData> dataList;
    int index1, index2;
    int check1, check2;
    String questions = "";

    String tstr = "";
    String str = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_or);

        cardImg1 = findViewById(R.id.result_card_img1);
        cardImg2 = findViewById(R.id.result_card_img2);
        cardName1 = findViewById(R.id.result_card_name1);
        cardName2 = findViewById(R.id.result_card_name2);
        cardComment = findViewById(R.id.result_card_comment);
        cardTopResult = findViewById(R.id.result_or_text1);
        endBtn = findViewById(R.id.btn_result);

        cardComment.setMovementMethod(new ScrollingMovementMethod());

        // 카드 정보 가져오기
        dataList = (ArrayList<AllData>) getIntent().getSerializableExtra("cardData");
        index1 = getIntent().getIntExtra("index1", 1);
        index2 = getIntent().getIntExtra("index2", 2);
        check1 = getIntent().getIntExtra("check1", 0);
        check2 = getIntent().getIntExtra("check2", 0);
        questions = getIntent().getStringExtra("questions");

        if (check1 == 1) {
            cardImg1.setRotation(180);
        }
        if (check2 == 1) {
            cardImg2.setRotation(180);
        }
        cardImg1.setImageResource(allImageId[index1]);
        cardImg2.setImageResource(allImageId[index2]);
        if (check1 == 0) {
            cardName1.setText(dataList.get(index1).getCardName() + " (" + dataList.get(index1).getCardEngName() + ")\n" + dataList.get(index1).getKeyword());
        } else {
            cardName1.setText(dataList.get(index1).getCardName() + " (" + dataList.get(index1).getCardEngName() + ")\n" + dataList.get(index1).getKeyword_rev());
        }
        if (check2 == 0) {
            cardName2.setText(dataList.get(index2).getCardName() + " (" + dataList.get(index2).getCardEngName() + ")\n" + dataList.get(index2).getKeyword());
        } else {
            cardName2.setText(dataList.get(index2).getCardName() + " (" + dataList.get(index2).getCardEngName() + ")\n" + dataList.get(index2).getKeyword_rev());
        }

        tstr += "질문 : [" + questions + "]\n";
        if (check1 > check2) {   //  0긍정, 1부정
            tstr += "전자보다 후자를 택하는 것이 나을 것 같습니다. ";
        } else if (check1 < check2) {
            tstr += "전자를 선택하는 것이 후자를 택하는 것 보다 나을 것 같습니다. ";
        } else if (check1 == 0 && check2 == 0) {
            tstr += "전자와 후자 모두 문제가 없을 것 입니다. 질문자님이 더 마음에 드는 선택을 하시길 바랍니다. ";
        } else if (check1 == 1 && check2 == 1) {
            tstr += "전자와 후자 모두 선택하기는 어려움이 있을 것 같습니다. ";
            int c3 = getIntent().getIntExtra("check3", 0), c4 = getIntent().getIntExtra("check4", 0);
            if (c3 > c4) {   //  0긍정, 1부정
                tstr += "하지만 이후 상황으로 보아 전자의 선택을 하는 것이 조금이나마 더 도움이 될 것 같아 보입니다. ";
            } else if (c3 < c4) {
                tstr += "하지만 이후 상황으로 보아 전자보단 후자의 선택을 하는 것이 조금이나마 더 도움이 될 것 같아 보입니다. ";
            }
        }
        if (check1 == 0) {
            str += "\n전자의 선택으로는 " + dataList.get(index1).getComment() + " ";
        } else {
            str += "\n전자의 선택으로는 " + dataList.get(index1).getComment_rev() + " ";
        }
        if (check2 == 0) {
            str += "\n후자의 선택으로는 " + dataList.get(index2).getComment() + " ";
        } else {
            str += "\n후자의 선택으로는 " + dataList.get(index2).getComment_rev() + " ";
        }
        cardTopResult.setText(tstr);
        cardComment.setText(str);

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

