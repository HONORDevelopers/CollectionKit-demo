/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo;

import static com.hihonor.collectionkitdemo.RouterActivity.SP_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import com.hihonor.collectionkitdemo.utils.Utils;


/**
 * demo应用的主界面，用于显示json数据的处理流程
 *
 * @since 2024-08-30
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Collection_Test_App_Main";

    private TextView mTextView;

    private String mShowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.MsgView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        processJsonDataFromShare();
    }

    private void processJsonDataFromShare() {
        mShowText = Utils.getStringSP(this, SP_NAME, "showText");
        mTextView.setText(mShowText);
    }


}