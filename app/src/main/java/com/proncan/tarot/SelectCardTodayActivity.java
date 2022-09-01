package com.proncan.tarot;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.imageId;

public class SelectCardTodayActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView card1, card2, card3, selectCard, selectCard2;
    ImageView res_card1, res_card2, res_card3, event_cardImg;
    TextView comment, select_card_text, today_card_comment_title;
    Button nextBtn;
    LinearLayout comment_linearlayout, def_linearlayout, select_card_layout, select_card_layout_res;
    RelativeLayout event_relativeLayout;

    private float centerX;
    private float centerY;
    private int DURATION = 500;
    int index = 0;

    int cnt = 3, idx = 0, tidx = 0;
    int[] idxNum = new int[3];
    int[] check = new int[3];

    Intent intent;
    ArrayList<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card_today);

        setFindViewById();

        comment.setMovementMethod(new ScrollingMovementMethod());

        intent = new Intent();

        // 초기 visible 설정
        nextBtn.setVisibility(View.GONE);
        comment.setText("카드를 세 장 뽑아주세요");
        for (int i = 0; i < idxNum.length; i++) {
            idxNum[i] = (int) (Math.random() * 21);
            for (int j = 0; j < i; j++) {
                if (idxNum[i] == idxNum[j]) {
                    i--;
                    break;
                }
            }
        }
        getTouchRotation();

        nextBtn.setOnClickListener(this);
        selectCard.setOnClickListener(this);
        selectCard2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.today_card_btn:
            case R.id.today_card_btn2:
                cnt--;
                switch (cnt) {
                    case 3:
                        comment.setText("카드를 세 장 뽑아주세요");
                        break;
                    case 2:
                        // 누른 위치에 이미지 추가
                        eventCardSelect();
                        comment.setText("카드를 한장 더 뽑아주세요");
                        card1.setImageResource(imageId[idxNum[0]]);
                        res_card1.setImageResource(imageId[idxNum[0]]);
                        check[0] = (int) (Math.random() * 2);
                        if (check[0] == 1) {
                            card1.setRotation(180);
                        }
                        tidx++;
                        break;
                    case 1:
                        eventCardSelect();
                        comment.setText("카드를 한장 더 뽑아주세요");
                        card2.setImageResource(imageId[idxNum[1]]);
                        res_card2.setImageResource(imageId[idxNum[1]]);
                        if (check[1] == 1) {
                            card2.setRotation(180);
                        }
                        tidx++;
                        break;
                    case 0:
                        eventCardSelect();
                        today_card_comment_title.setText("카드를 다 뽑았습니다. \n다음을 눌러 결과를 보세요.");
                        card3.setImageResource(imageId[idxNum[2]]);
                        res_card3.setImageResource(imageId[idxNum[2]]);
                        if (check[2] == 1) {
                            card3.setRotation(180);
                        }
                        tidx++;
                        selectCard.setVisibility(View.GONE);
                        selectCard2.setVisibility(View.GONE);
                        select_card_text.setText("카드 선택 완료");
                        def_linearlayout.setBackgroundResource(R.mipmap.bg_zoom);
                        select_card_layout.setVisibility(View.GONE);
                        select_card_layout_res.setVisibility(View.VISIBLE);
                        comment.setVisibility(View.VISIBLE);
                        comment_linearlayout.setVisibility(View.VISIBLE);
                        comment.setText("");
                        nextBtn.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.btn_today_next:
                String str;
                switch (idx) {
                    case 0:
                        res_card1.setBackgroundResource(R.drawable.image_border);
                        today_card_comment_title.setText(dataList.get(idxNum[0]).getCardName());
                        str = dataList.get(idxNum[0]).getComment5();
                        if (check[0] == 1) {
                            str += " 하지만 카드가 뒤집어져 있습니다. " + dataList.get(idxNum[0]).getComment5_rev();
                        }
                        comment.setText(str);
                        break;
                    case 1:
                        res_card1.setBackground(null);
                        res_card2.setBackgroundResource(R.drawable.image_border);
                        today_card_comment_title.setText(dataList.get(idxNum[1]).getCardName());
                        str = dataList.get(idxNum[1]).getComment5();
                        if (check[1] == 1) {
                            str += " 하지만 카드가 뒤집어져 있습니다. " + dataList.get(idxNum[1]).getComment5_rev();
                        }
                        comment.setText(str);
                        break;
                    case 2:
                        res_card2.setBackground(null);
                        res_card3.setBackgroundResource(R.drawable.image_border);
                        today_card_comment_title.setText(dataList.get(idxNum[2]).getCardName());
                        str = dataList.get(idxNum[2]).getComment5();
                        if (check[2] == 1) {
                            str += " 하지만 카드가 뒤집어져 있습니다. " + dataList.get(idxNum[2]).getComment5_rev();
                        }
                        comment.setText(str);
                        nextBtn.setText("결과보기");
                        break;
                    case 3:
                        res_card3.setBackground(null);
                        intent = new Intent(SelectCardTodayActivity.this, ResultTodayActivity.class);
                        intent.putExtra("cardData", dataList);
                        intent.putExtra("idxNum", idxNum);
                        intent.putExtra("check", check);
                        startActivity(intent);
                        finish();
                        break;
                }
                idx++;
                break;
        }
    }

    private void applyRotation(int i, float start, float mid, float end, float depth, ImageView card) {
        this.centerX = card1.getWidth() / 2.0f;
        this.centerY = card1.getHeight() / 2.0f;

        Rotate3dAnimation rot = new Rotate3dAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new DisplayNextView(mid, end, depth, i));
        card.startAnimation(rot);
    }

    public class DisplayNextView implements Animation.AnimationListener {
        private float mid;
        private float end;
        private float depth;
        private int pos;

        public DisplayNextView(float mid, float end, float depth, int pos) {
            this.mid = mid;
            this.end = end;
            this.depth = depth;
            this.pos = pos;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Rotate3dAnimation rot;
            rot = new Rotate3dAnimation(mid, end, centerX, centerY, depth, false);
            rot.setDuration(500);
            rot.setInterpolator(new AccelerateInterpolator());
            event_cardImg.startAnimation(rot);
            event_cardImg.setImageResource(imageId[idxNum[pos]]);  // 카드 이미지 id
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    void getTouchRotation() {
        View view = new GetRotation(this);
        centerX = view.getX();
        centerY = view.getY();
    }

    void setFindViewById() {
        card1 = findViewById(R.id.img_today_card1);
        card2 = findViewById(R.id.img_today_card2);
        card3 = findViewById(R.id.img_today_card3);
        res_card1 = findViewById(R.id.img_today_card1_res);
        res_card2 = findViewById(R.id.img_today_card2_res);
        res_card3 = findViewById(R.id.img_today_card3_res);
        today_card_comment_title = findViewById(R.id.today_card_comment_title);
        comment = findViewById(R.id.today_card_comment);
        nextBtn = findViewById(R.id.btn_today_next);
        selectCard = findViewById(R.id.today_card_btn);
        selectCard2 = findViewById(R.id.today_card_btn2);
        select_card_text = findViewById(R.id.select_card_text);
        comment_linearlayout = findViewById(R.id.comment_linearlayout);
        def_linearlayout = findViewById(R.id.def_linearlayout);
        select_card_layout = findViewById(R.id.select_card_layout);
        select_card_layout_res = findViewById(R.id.select_card_layout_res);
        event_relativeLayout = findViewById(R.id.event_card_layout);
        event_cardImg = findViewById(R.id.event_card);
        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("cardData");
    }

    void eventCardSelect() {
        event_relativeLayout.setVisibility(View.VISIBLE);
        applyRotation(tidx, 180f, 280f, 360f, 0f, event_cardImg);
    }
}
