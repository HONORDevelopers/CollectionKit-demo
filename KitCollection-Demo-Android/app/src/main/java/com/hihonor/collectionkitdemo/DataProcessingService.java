/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo;

import static com.hihonor.collectionkitdemo.RouterActivity.SP_NAME;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.hihonor.collectionkitdemo.dataparse.ShareDataConstants;
import com.hihonor.collectionkitdemo.dataparse.Content;
import com.hihonor.collectionkitdemo.dataparse.MaterialWithRel;
import com.hihonor.collectionkitdemo.dataparse.SharedMaterials;
import com.hihonor.collectionkitdemo.dataparse.VideoTag;
import com.hihonor.collectionkitdemo.utils.ThreadPool;
import com.hihonor.collectionkitdemo.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于进行数据处理的service
 *
 * @since 2024-08-29
 */
public class DataProcessingService extends Service {
    private static final String TAG = "DataProcessingService";

    private static final String EXPORT_RESULT_ACTION = "com.hihonor.collectcenter.EXPORT_RESULT";

    private static final String PACKAGE_NAME = "com.hihonor.collectionkitdemo";

    private String mVersion;

    private String mStartTime;

    private final String mLineSeparator = System.lineSeparator();

    private StringBuilder mSbTextview = new StringBuilder();

    private ArrayList<String> mUris;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        if (intent != null) {
            String sharedData = intent.getStringExtra("exportContent");
            mUris = intent.getStringArrayListExtra("uriList");
            if (sharedData != null) {
                // 模拟数据处理
                processData(sharedData);
            }
        }
        // 停止服务
        stopSelf();
        return START_NOT_STICKY;
    }

    private void processData(String jsonData) {
        Log.i(TAG, "Jsondata:" + jsonData);
        JSONObject jsonObject = null;
        String fromPackage = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            jsonObject = new JSONObject(jsonData);
            mVersion = Utils.getStringFromJson(jsonObject, "version");
            fromPackage = Utils.getStringFromJson(jsonObject, "fromPackage");
            mStartTime = Utils.getStringFromJson(jsonObject, "startTime");
        } catch (JSONException e) {
            Log.e(TAG, "[getVersion] data is not array.");
        }

        ThreadPool.getInstance().execute(() -> {  // 实际数据处理参考如下逻辑
            for (String uri : mUris) {
                if (TextUtils.isEmpty(uri)) {
                    continue;
                }
                Utils.copyFile(Uri.parse(uri), this);
            }
        });
        String shareResult = createShareResult(); // 返回处理结果
        sendBroadcastToShare(shareResult, this);

        buildTextToShow(jsonData, jsonObject, stringBuilder, shareResult);  // 此处为demo显示使用
    }

    private void buildTextToShow(String jsonData, JSONObject jsonObject,
        StringBuilder stringBuilder, String shareResult) {
        mSbTextview.append("分享素材时间:");
        mSbTextview.append(Utils.millsToDateTime(Long.parseLong(mStartTime)));
        mSbTextview.append(mLineSeparator);
        mSbTextview.append("版本号:");
        mSbTextview.append(mVersion);
        mSbTextview.append(mLineSeparator);
        mSbTextview.append("分享的应用:");
        mSbTextview.append("收藏空间");
        mSbTextview.append(mLineSeparator);
        mSbTextview.append("接收到的素材json数据:");
        mSbTextview.append(jsonData);
        mSbTextview.append(mLineSeparator);
        mSbTextview.append("------------处理结果---------------");
        mSbTextview.append(mLineSeparator);
        SharedMaterials content = null;
        content = Utils.getSharedMaterials(jsonObject);
        List<MaterialWithRel> materialsWithRels = content.getMaterialsWithRel();
        handleStructData(materialsWithRels, stringBuilder);
        mSbTextview.append("-------------返回处理结果-----------------");
        mSbTextview.append(mLineSeparator);
        mSbTextview.append("fromPackage:");
        mSbTextview.append(PACKAGE_NAME);
        mSbTextview.append(",");
        mSbTextview.append("exportResContent:");
        mSbTextview.append(shareResult);
        Utils.putSP(this, SP_NAME, "showText", mSbTextview.toString());
    }

    private void sendBroadcastToShare(String result, Context context) {
        Intent intent = new Intent(EXPORT_RESULT_ACTION);
        intent.putExtra("fromPackage", PACKAGE_NAME);
        intent.putExtra("exportResContent", result);
        context.sendBroadcast(intent);
    }

    private String createShareResult() {
        JSONObject jsonLevelOne = new JSONObject();
        long endTime = System.currentTimeMillis();
        try {
            jsonLevelOne.put("version", mVersion);
            jsonLevelOne.put("result", 0);
            jsonLevelOne.put("startTime", Long.parseLong(mStartTime));
            jsonLevelOne.put("hasPrompted", true);
            jsonLevelOne.put("endTime", endTime);
            JSONObject failedDesc = new JSONObject();

            // 此处为demo测试传入的值，实际传入以使用方业务为准
            failedDesc.put("failedSumm", "");
            failedDesc.put("failedItem", "");
            jsonLevelOne.put("failedDesc", failedDesc);
        } catch (JSONException e) {
            Log.e(TAG, "createShareResult has JSONException");
        }
        return jsonLevelOne.toString();
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
            mSbTextview.append("文件:");
            mSbTextview.append(uri);
            mSbTextview.append("，文件保存路径:");
            mSbTextview.append(Utils.getDownloadPath(Uri.parse(uri), this));
            mSbTextview.append(",");
        }
    }

    private void handleSingleData(ArrayList<Content> subContents, StringBuilder stringBuilder) {
        for (int i = 0; i < subContents.size(); i++) {
            Content content = subContents.get(i);
            if (!TextUtils.isEmpty(content.getTextContent())) {
                mSbTextview.append("文本:");
                mSbTextview.append(content.getTextContent());
                mSbTextview.append(";");
                mSbTextview.append(mLineSeparator);
            } else if (!TextUtils.isEmpty(content.getFileUri())) {
                stringBuilder.append(content.getFileUri());
                stringBuilder.append(";");
                if (content.getFileUri().contains("videoExcerpt")) {
                    if (content.getFileUri().contains(".mp4")) {
                        mSbTextview.append("视频摘录文件:");
                        mSbTextview.append(content.getFileUri());
                        mSbTextview.append(";视频持续时间:");
                        mSbTextview.append(content.getDuration());
                        mSbTextview.append("s;文件保存路径:");
                        mSbTextview.append(Utils.getDownloadPath(Uri.parse(content.getFileUri()), this));
                        mSbTextview.append(";");
                        mSbTextview.append(mLineSeparator);
                        processVideoExpectData(content, stringBuilder);
                    } else {
                        mSbTextview.append("视频摘录文件:");
                        mSbTextview.append(content.getFileUri());
                        mSbTextview.append(";文件保存路径:");
                        mSbTextview.append(Utils.getDownloadPath(Uri.parse(content.getFileUri()), this));
                        mSbTextview.append(";");
                        mSbTextview.append(mLineSeparator);
                    }
                } else {
                    mSbTextview.append("文件:");
                    mSbTextview.append(content.getFileUri());
                    mSbTextview.append(";");
                    mSbTextview.append("文件保存路径:");
                    mSbTextview.append(Utils.getDownloadPath(Uri.parse(content.getFileUri()), this));
                    mSbTextview.append(";");
                    mSbTextview.append(mLineSeparator);
                }
            } else {
                Log.w(TAG, "do nothing");
            }
        }
    }

    private void processVideoExpectData(Content content, StringBuilder stringBuilder) {
        for (VideoTag videoTag : content.getVideoTag()) {
            String tagType = videoTag.getTagType();
            Long timeOffset = videoTag.getTimeOffset();
            String strTimeOffset = "在视频" + timeOffset + "s处,";
            mSbTextview.append(strTimeOffset);
            switch (tagType) {
                case VideoTag.VideoTagType.VIDEO_AND_SHOT:
                    stringBuilder.append(videoTag.getFileUri());
                    stringBuilder.append(";");
                    mSbTextview.append("有一个截图标记:");
                    mSbTextview.append(videoTag.getFileUri());
                    mSbTextview.append(",截图保存路径:");
                    mSbTextview.append(Utils.getDownloadPath(Uri.parse(videoTag.getFileUri()), this));
                    mSbTextview.append(",");
                    break;
                case VideoTag.VideoTagType.SHOT_AND_TEXT:
                    stringBuilder.append(videoTag.getFileUri());
                    stringBuilder.append(";");
                    mSbTextview.append("有一个截图文本标记:");
                    mSbTextview.append(videoTag.getFileUri());
                    mSbTextview.append(",截图保存路径:");
                    mSbTextview.append(Utils.getDownloadPath(Uri.parse(videoTag.getFileUri()), this));
                    mSbTextview.append(",文本为:");
                    mSbTextview.append(videoTag.getTextContent());
                    mSbTextview.append(",");
                    break;
                case VideoTag.VideoTagType.TEXT:
                    mSbTextview.append("有一个文本标记:");
                    mSbTextview.append(videoTag.getTextContent());
                    mSbTextview.append(",");
                    break;
                case VideoTag.VideoTagType.EMPTY_TAG:
                    mSbTextview.append("有一个空白标记,");
                    break;
            }
        }
        mSbTextview.append(mLineSeparator);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
