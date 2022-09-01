package com.proncan.tarot;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class PermissionsActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    View mLayout;
    Intent intent;
    Snackbar snackbar;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLayout = findViewById(R.id.loading_activity);
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            // code이고 퍼미션 개수만큼 수신
            boolean checkResult = true;
            // 모든 퍼미션 허용 체크
            for (int res : grantResults) {
                if (res != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false;
                    break;
                }
            }
            // 모든 퍼미션 허용
            Log.d("checkResult", "onRequestPermissionsResult: " + checkResult);
            if (checkResult) {
                intent = new Intent(PermissionsActivity.this, DownloadActivity.class);
                startActivity(intent);
                finish();
            } else {
                // 거부한 퍼미션 있을 경우
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    // 사용자가 거부만 할 경우 앱 다시 실행하여 사용
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }).show();
                } else {
                    // 다시 묻지 않음 일 경우
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다.", Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    }).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mLayout = findViewById(R.id.loading_activity);

        // 퍼미션을 가지고 있는지 체크
        int permission_write_external_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission_read_external_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission_write_external_storage == PackageManager.PERMISSION_GRANTED
                && permission_read_external_storage == PackageManager.PERMISSION_GRANTED) {
            // 퍼미션 가지고 있음
            intent = new Intent(this, DownloadActivity.class);
            startActivity(intent);
            finish();
        } else {
            // 퍼미션 거부1 -> 이유설명
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                // 이유 설명
                snackbar = Snackbar.make(mLayout, "앱을 실행하려면 저장소 접근 권한이 필요합니다.", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 퍼미션 요청
                        ActivityCompat.requestPermissions(PermissionsActivity.this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            } else {
                // 거부 2 -> 요청
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        }
    }
}
