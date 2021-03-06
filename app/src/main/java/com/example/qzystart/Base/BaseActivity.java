package com.example.qzystart.Base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,NetStateChangeObserver {
    private ActivityManager mActivityManager = ActivityManager.getActivityManager();
    private Activity mthis;
    private static final String TAG = "BaseActivity";

    @Override
    public void onNetDisconnected() {
        Log.d(TAG, TAG + "onNetDisconnected");
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
        Log.d(TAG, TAG + "onNetConnected");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mthis=this;
        try {
            setFullScreen();
            setContentView();
            initView();
            initDate();
            NetStateChangeReceiver.registerReceiver(this);
            if(null != mActivityManager) {
                mActivityManager.putActivity(this);
            }
        }catch (Exception e){
            Log.d(TAG, "BaseActivity onCreate: "+e.getMessage());
        }
    }

    public abstract void setContentView();

    public abstract void initView();

    public abstract void initDate();

    @SuppressLint("SourceLockedOrientationActivity")
    private void setFullScreen() {
        View view = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        view.setSystemUiVisibility(options);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor( Color.TRANSPARENT);
        }
        //???????????????????????????,??????activity?????????
        changeStatusBarTextColor( true );
    }

    /**
     * ???????????????????????????????????????(??????true)
     * ???????????????????????????????????????(??????false)
     * @param isBlack
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void changeStatusBarTextColor(@NonNull boolean isBlack){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//???????????????????????????
            }else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//???????????????????????????
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(null != mActivityManager) {
            mActivityManager.removeActivity(this);
        }

        NetStateChangeReceiver.unRegisterReceiver(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume( );
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onStop() {
        super.onStop( );
    }

    @Override
    protected void onPause() {
        super.onPause( );
        NetStateChangeReceiver.unRegisterObserver(this);
    }

    @Override
    protected void onStart() {
        super.onStart( );

    }
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
