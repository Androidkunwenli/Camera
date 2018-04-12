package com.caah.mppclient.camera;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private CameraSurfaceView mCameraSurfaceView;
    private RectOnCamera mRectOnCamera;
    private Button takePicBtn;

    private boolean isClicked;
    private SurfaceHolder mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        mRectOnCamera = (RectOnCamera) findViewById(R.id.rectOnCamera);
        takePicBtn = (Button) findViewById(R.id.takePic);
        takePicBtn.setOnClickListener(this);
        mCameraSurfaceView.setOnPathListener(new CameraSurfaceView.OnPathListener() {
            @Override
            public void imagePath(String path) {
                //回调图片路径
                Log.i(TAG, "imagePath: " + path);
//                Intent intent = new Intent();
//                intent.putExtra("imagePath", path);
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePic:
                //拍照
                mCameraSurfaceView.takePicture();
                //切换摄像头
//                mCameraSurfaceView.switchCamera();
                break;
            default:
                break;
        }
    }
}