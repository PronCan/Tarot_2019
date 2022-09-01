package com.proncan.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.proncan.tarot.LoadingActivity.imageId;

public class SelectCardEtc extends AppCompatActivity {
    ImageView[] card;
    int[] cardId;
    int type;
    boolean isClick = false;
    int[] idxCnt = new int[22];
    int index = 0;

    private float centerX;
    private float centerY;
    private int DURATION = 500;

    Intent intent;

    ArrayList<Data> dataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card_etc);
        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("cardData");

        card = new ImageView[22];
        cardId = new int[]{R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6,
                R.id.card7, R.id.card8, R.id.card9, R.id.card10, R.id.card11, R.id.card12,
                R.id.card13, R.id.card14, R.id.card15, R.id.card16, R.id.card17, R.id.card18,
                R.id.card19, R.id.card20, R.id.card21, R.id.card22};
        for (int i = 0; i < cardId.length; i++) {
            idxCnt[i] = (int) (Math.random() * 22);
            for (int j = 0; j < i; j++) {
                if (idxCnt[i] == idxCnt[j]) {
                    i--;
                    break;
                }
            }
        }

        for (int i = 0; i < cardId.length; i++) {
            card[idxCnt[i]] = findViewById(cardId[idxCnt[i]]);
        }

        type = getIntent().getIntExtra("type", 1);

        ImageView.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick == false) {
                    intent = new Intent(SelectCardEtc.this, ResultEtcActivity.class);
                    for (int i = 0; i < 22; i++) {
                        if (v.getId() == cardId[i]) {
                            index = i;
                            Log.d("putExtra%%%", imageId[idxCnt[i]] + " / " + idxCnt[i] + " / " + type);
                            intent.putExtra("index", idxCnt[i]);   // 카드 idx
                            intent.putExtra("cardData", dataList);
                            intent.putExtra("type", type);
                            card[i].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_alpha));
                            applyRotation(i, 180f, 280f, 360f, 0f);
                        }
                    }
                    isClick = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                finish();
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        };
        for (int i = 0; i < cardId.length; i++) {
            card[i].setOnClickListener(onClickListener);
        }
    }

    private void applyRotation(int i, float start, float mid, float end, float depth) {
        this.centerX = card[i].getWidth() / 2.0f;
        this.centerY = card[i].getHeight() / 2.0f;

        Rotate3dAnimation rot = new Rotate3dAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new DisplayNextView(mid, end, depth));
        card[i].startAnimation(rot);

    }

    public class DisplayNextView implements Animation.AnimationListener {
        private float mid;
        private float end;
        private float depth;

        public DisplayNextView(float mid, float end, float depth) {
            this.mid = mid;
            this.end = end;
            this.depth = depth;
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
            card[index].startAnimation(rot);
            card[index].setImageResource(imageId[idxCnt[index]]);  // 카드 이미지 id
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
