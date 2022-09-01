package com.proncan.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.allImageId;

public class SelectCardYesNoActivity extends AppCompatActivity {
    View first, second;
    ImageView card1, card2, card3, card4, card5, selectCard;
    TextView comment, question, top;
    EditText etQuestion;
    Button firstNextBtn;
    int cnt = 5, idx = 0;
    int[] idxNum = new int[5];
    int[] check = new int[5];
    Intent intent;
    ArrayList<AllData> dataList;
    String questions = "";
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card_or);

        dataList = (ArrayList<AllData>) getIntent().getSerializableExtra("cardData");
        first = findViewById(R.id.select_card_first);
        second = findViewById(R.id.select_card_second);
        firstNextBtn = findViewById(R.id.btn_doSelectCard);
        etQuestion = findViewById(R.id.et_yesno_question);
        top = findViewById(R.id.select_card_or_top);
        selectCard = findViewById(R.id.or_card_btn);
        card1 = findViewById(R.id.cardor1);
        card2 = findViewById(R.id.cardor2);
        card3 = findViewById(R.id.cardor3);
        card4 = findViewById(R.id.cardor4);
        card5 = findViewById(R.id.cardor5);
        comment = findViewById(R.id.or_card_comment);

        comment.setMovementMethod(new ScrollingMovementMethod());
        etQuestion.setMovementMethod(new ScrollingMovementMethod());

        intent = new Intent();

        // 초기 visible 설정
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.GONE);
        for (int i = 0; i < check.length; i++) {
            check[i] = (int) (Math.random() * 2);
        }

        firstNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etQuestion.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "고민을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                first.setVisibility(View.GONE);
                second.setVisibility(View.VISIBLE);
                questions = etQuestion.getText().toString();
                top.setText("카드를 5장 선택하세요");
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) top.getLayoutParams();
                lp.topMargin = 300;
                top.setLayoutParams(lp);
                Toast.makeText(getApplicationContext(), "카드를 눌러 카드를 뽑아주세요", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < idxNum.length; i++) {
                    idxNum[i] = (int) (Math.random() * 77);
                    for (int j = 0; j < i; j++) {
                        if (idxNum[i] == idxNum[j]) {
                            i--;
                            break;
                        }
                    }
                }
            }
        });

        selectCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt--;
                switch (cnt) {
                    case 4:
                        top.setText(cnt + "장 더 뽑아주세요");
                        if (check[0] == 1) {
                            card1.setRotation(180);
                        }
                        card1.setImageResource(allImageId[idxNum[0]]);
                        break;
                    case 3:
                        top.setText(cnt + "장 더 뽑아주세요");
                        if (check[1] == 1) {
                            card2.setRotation(180);
                        }
                        card2.setImageResource(allImageId[idxNum[1]]);
                        break;
                    case 2:
                        top.setText(cnt + "장 더 뽑아주세요");
                        if (check[2] == 1) {
                            card3.setRotation(180);
                        }
                        card3.setImageResource(allImageId[idxNum[2]]);
                        break;
                    case 1:
                        top.setText(cnt + "장 더 뽑아주세요");
                        if (check[3] == 1) {
                            card4.setRotation(180);
                        }
                        card4.setImageResource(allImageId[idxNum[3]]);
                        break;
                    case 0:
                        top.setText("");
                        comment.setText("카드를 다 뽑았습니다.\n화면의 위쪽 부분을 눌러 결과를 보세요.");
                        if (check[4] == 1) {
                            card5.setRotation(180);
                        }
                        card5.setImageResource(allImageId[idxNum[4]]);
                        next = true;
                        selectCard.setVisibility(View.GONE);
                        comment.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (next) {
                    String str = "";
                    switch (idx) {
                        case 0:
                            card1.setBackgroundResource(R.drawable.image_border);
                            top.setText("첫번째 카드");
                            str += "첫번째 카드는 한가지를 선택해야하는 현재 상황을 보여줍니다. ";
                            str += dataList.get(idxNum[idx]).getDesc() + " ";
                            if (check[idx] == 0) {
                                str += dataList.get(idxNum[idx]).getComment();
                            } else {
                                str += dataList.get(idxNum[idx]).getComment_rev();
                            }
                            comment.setText(str);
                            break;
                        case 1:
                            card1.setBackground(null);
                            card2.setBackgroundResource(R.drawable.image_border);
                            top.setText("두번째 카드");
                            str += "두번째 카드는 전자 선택에 대한 현재 상황을 보여줍니다. ";
                            str += dataList.get(idxNum[idx]).getDesc();
                            if (check[idx] == 0) {
                                str += dataList.get(idxNum[idx]).getComment();
                            } else {
                                str += dataList.get(idxNum[idx]).getComment_rev();
                            }
                            comment.setText(str);
                            break;
                        case 2:
                            card2.setBackground(null);
                            card3.setBackgroundResource(R.drawable.image_border);
                            top.setText("세번째 카드");
                            str += "세번째 카드는 후자 선택에 대한 현재 상황을 보여줍니다. ";
                            str += dataList.get(idxNum[idx]).getDesc();
                            if (check[idx] == 0) {
                                str += dataList.get(idxNum[idx]).getComment();
                            } else {
                                str += dataList.get(idxNum[idx]).getComment_rev();
                            }
                            comment.setText(str);
                            break;
                        case 3:
                            card3.setBackground(null);
                            card4.setBackgroundResource(R.drawable.image_border);
                            top.setText("네번째 카드");
                            str += "네번째 카드는 전자를 선택했을 때 이후 상황을 보여줍니다. ";
                            str += dataList.get(idxNum[idx]).getDesc();
                            if (check[idx] == 0) {
                                str += dataList.get(idxNum[idx]).getComment();
                            } else {
                                str += dataList.get(idxNum[idx]).getComment_rev();
                            }
                            comment.setText(str);
                            break;
                        case 4:
                            card4.setBackground(null);
                            card5.setBackgroundResource(R.drawable.image_border);
                            top.setText("다섯번째 카드");
                            str += "다섯번째 카드는 후자를 선택했을 때 이후 상황을 보여줍니다. ";
                            str += dataList.get(idxNum[idx]).getDesc();
                            if (check[idx] == 0) {
                                str += dataList.get(idxNum[idx]).getComment();
                            } else {
                                str += dataList.get(idxNum[idx]).getComment_rev();
                            }
                            comment.setText(str);

                            intent = new Intent(SelectCardYesNoActivity.this, ResultYesNoActivity.class);
                            intent.putExtra("cardData", dataList);
                            intent.putExtra("questions", etQuestion.getText().toString());
                            intent.putExtra("index1", idxNum[1]);
                            intent.putExtra("check1", check[1]);
                            intent.putExtra("index2", idxNum[2]);
                            intent.putExtra("check2", check[2]);
                            intent.putExtra("check3", check[3]);
                            intent.putExtra("check4", check[4]);
                            break;
                        case 5:
                            startActivity(intent);
                            finish();
                    }
                    idx++;
                }
            }
        });
    }
}
