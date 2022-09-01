package com.proncan.tarot;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GetRotation extends View {
    String TAG = "GetRotationLog%%%";
    float x;
    float y;

    public GetRotation(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.x = event.getX();
            this.y = event.getY();

            String msg = x + " / " + y;
            Log.d(TAG, "onTouchEvent: " + msg);
            return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

}
