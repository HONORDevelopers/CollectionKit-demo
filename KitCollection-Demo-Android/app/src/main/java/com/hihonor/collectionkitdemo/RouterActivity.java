/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.hihonor.collectionkitdemo.dataparse.ShareDataConstants;
import com.hihonor.collectionkitdemo.dataparse.Content;
import com.hihonor.collectionkitdemo.dataparse.MaterialWithRel;
import com.hihonor.collectionkitdemo.dataparse.SharedMaterials;
import com.hihonor.collectionkitdemo.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于临时接收数据的activity
 *
 * @since 2024-08-29
 */
public class RouterActivity extends AppCompatActivity {
    private static final String TAG = "RouterActivity";

    /**
     * SP name
     */
    public static final String SP_NAME = "collectcenter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null && "com.hihonor.android.intent.action.ATOMIC_CAPABILITY".equals(intent.getAction())) {
            String jsonData = intent.getStringExtra("exportContent");
            if (!TextUtils.isEmpty(jsonData)) {
                // 启动服务处理数据

                JSONObject jsonObject = null;
                SharedMaterials content = null;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    jsonObject = new JSONObject(jsonData);
                } catch (JSONException e) {
                    Log.e(TAG, "[getVersion] data is not array.");
                }

                // 解析json数据,应用可根据自身需要增加其他逻辑
                content = Utils.getSharedMaterials(jsonObject);
                List<MaterialWithRel> materialsWithRels = content.getMaterialsWithRel();
                handleStructData(materialsWithRels, stringBuilder);
                ArrayList<String> uriList = Utils.extractUriBetweenSemicolons(stringBuilder.toString());

                // 此处为应用内uri授权处理逻辑，应用可根据自身需要使用

                for (String uri : uriList) {
                    if (TextUtils.isEmpty(uri)) {
                        continue;
                    }
                    try {
                        grantUriPermission(getPackageName(), Uri.parse(uri), Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } catch (SecurityException e) {
                        Log.e(TAG, "SecurityException has expection");
                    }
                }

                // 传入的exportContent仅为界面显示处理结果使用，实际处理时可根据业务实际情况传值

                Intent serviceIntent = new Intent(this, DataProcessingService.class);
                serviceIntent.putExtra("exportContent", jsonData);

                serviceIntent.putStringArrayListExtra("uriList", uriList);
                startService(serviceIntent);
            }
        }

        // 结束当前Activity

        Utils.finishActivitySafely(TAG, this);
    }

    private void handleStructData(List<MaterialWithRel> materialsWithRels, StringBuilder stringBuilder) {
        if (materialsWithRels == null || materialsWithRels.isEmpty()) {
            Log.i(TAG, "handleAllStructData materialsWithRels is null or empty.");
            return;
        }
        for (int i = 0; i < materialsWithRels.size(); i++) {
            MaterialWithRel materialsWithRel = materialsWithRels.get(i);
            ArrayList<Content> contents = materialsWithRel.getContents();
            ArrayList<MaterialWithRel> contentsGroup = materialsWithRel.getContentsGroup();
            switch (materialsWithRel.getUnitType()) {
                case ShareDataConstants.UnitType.SINGLE_MATERIAL:
                case ShareDataConstants.UnitType.VIDEO_EXTRACT_UNIT:
                    handleSingleData(contents, stringBuilder);
                    break;
                case ShareDataConstants.UnitType.ANNOTATION:
                    handleAnnotationData(contents, stringBuilder);
                    break;
                case ShareDataConstants.UnitType.MULTI_MATERIALS:
                    handleStructData(contentsGroup, stringBuilder);
                    break;
                default:
                    break;
            }
        }
    }

    private void handleAnnotationData(ArrayList<Content> contents, StringBuilder stringBuilder) {
        if (contents == null || contents.isEmpty()) {
            return;
        }
        for (int i = 0; i < contents.size(); i++) {
            Content content = contents.get(i);
            String uri = content.getFileUri();
            stringBuilder.append(uri);
            stringBuilder.append(";");
        }
    }

    private void handleSingleData(ArrayList<Content> subContents, StringBuilder stringBuilder) {
        for (int i = 0; i < subContents.size(); i++) {
            Content content = subContents.get(i);
            if (!TextUtils.isEmpty(content.getFileUri())) {
                stringBuilder.append(content.getFileUri());
                stringBuilder.append(";");
            }
        }
    }

}
