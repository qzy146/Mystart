package com.example.qzystart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.qzystart.Base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void onClick(View v) {

    }


}
