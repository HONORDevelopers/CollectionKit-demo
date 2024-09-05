/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;

import static com.hihonor.android.emcom.LinkTurboKitConstants.NETIFACEID_DATA0;
import static com.hihonor.android.emcom.LinkTurboKitConstants.NETIFACEID_WIFI0;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hihonor.collectionkitdemo.dataparse.SharedMaterials;

/**
 * common utils for all use
 *
 * @since 2022-04-16
 */
public class Utils {
    private static final String TAG = "Utils";

    /**
     * get String From Json
     *
     * @param object JSON Object
     * @param key key
     * @return Json String
     */
    public static String getStringFromJson(@NonNull JSONObject object, @NonNull String key) {
        if (object.has(key)) {
            try {
                return object.getString(key);
            } catch (JSONException e) {
                Log.e(TAG, "[getStringFromJson] JSON occur error");
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * extract Uri Between Semicolons
     *
     * @param input file uri string
     * @return Uri list
     */
    public static ArrayList<String> extractUriBetweenSemicolons(String input) {
        ArrayList<String> contents = new ArrayList<>();

        // 定义正则表达式匹配分号之间的内容

        String regex = "[^;]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // 查找所有匹配的内容

        while (matcher.find()) {
            contents.add(matcher.group().trim());
        }

        return contents;
    }

    /**
     * copy File
     *
     * @param context context
     * @param uri uri
     */
    public static void copyFile(Uri uri, Context context) {
        if (uri == null || context == null) {
            return;
        }

        // 为了demo测试验证，文件都拷贝至公共存储Download下，实际存储目录可自行选择

        String internalStoragePath = Environment.getExternalStorageDirectory().getPath() + File.separator
                + Environment.DIRECTORY_DOWNLOADS;
        String displayName = getFileName(context, uri);
        if (TextUtils.isEmpty(displayName)) {
            displayName = "unKnow";
        }
        File folderFile = new File(internalStoragePath);
        if (!(folderFile.exists() && folderFile.isFile())) {
            folderFile.mkdirs();
        }
        File file = new File(folderFile, displayName);

        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        FileDescriptor fd = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptor != null) {
                fd = parcelFileDescriptor.getFileDescriptor();
            }
            fis = new FileInputStream(fd);
            fos = new FileOutputStream(file);
            writeChannel = fos.getChannel();
            readChannel = fis.getChannel();
            readChannel.transferTo(0, readChannel.size(), writeChannel);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "copyFileToFile FileNotFoundException ");
        } catch (RuntimeException e) {
            Log.e(TAG, "copyFileToFile RuntimeException");
        } catch (IOException e) {
            Log.e(TAG, "copyFileToFile IOException");
        } finally {
            closeQuietly(readChannel);
            closeQuietly(writeChannel);
            closeQuietly(parcelFileDescriptor);
            closeQuietly(fos);
            closeQuietly(fis);
        }
    }

    /**
     * get Download Path
     *
     * @param context context
     * @param uri uri
     * @return Download Path
     */
    public static String getDownloadPath(Uri uri, Context context) {
        String mStoragePath = Environment.getExternalStorageDirectory().getPath() + File.separator
                + Environment.DIRECTORY_DOWNLOADS;
        return mStoragePath + File.separator + getFileName(context, uri);
    }

    /**
     * get File Name
     *
     * @param context context
     * @param uri uri
     * @return fileName
     */
    public static String getFileName(Context context, Uri uri) {
        String fileName = null;
        if (context == null || uri == null) {
            return null;
        }
        if ("content".equals(uri.getScheme())) {
            ContentResolver contentResolver = context.getContentResolver();
            try (Cursor cursor = contentResolver.query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        fileName = cursor.getString(nameIndex);
                    }
                }
            } catch (SecurityException e) {
                Log.e(TAG, "FileName has SecurityException");
            } catch (NullPointerException e) {
                Log.e(TAG, "FileName has NullPointerException");
            }
        }
        if (fileName == null) {
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }

    /**
     * closeQuietly
     *
     * @param closeable closeable
     */
    public static void closeQuietly(java.io.Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (java.io.IOException e) {
                Log.e(TAG, "closing occur io exception");
            }
        }
    }

    /**
     * getSharedMaterials
     *
     * @param jsonObject jsonObject
     * @return SharedMaterials
     */
    public static SharedMaterials getSharedMaterials(JSONObject jsonObject) {
        SharedMaterials content = new SharedMaterials();
        if (jsonObject == null) {
            return content;
        }

        Gson gson = new GsonBuilder().create();
        content = gson.fromJson(String.valueOf(jsonObject), SharedMaterials.class);
        return content;
    }

    /**
     * millsToDateTime
     *
     * @param timestamp mills timestamp
     * @return DateTime
     */
    public static String millsToDateTime(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * put SP
     *
     * @param context context
     * @param name SP name
     * @param key key
     * @param value value
     */
    public static void putSP(Context context, String name, String key, String value) {
        if (context == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * finish Activity Safely
     *
     * @param tag tag
     * @param activity activity
     */
    public static void finishActivitySafely(String tag, Activity activity) {
        if (activity != null) {
            try {
                activity.finish();
            } catch (BadParcelableException var2) {
                Log.e(tag, "finishSafely catch a BadParcelableException ");
            } catch (RuntimeException var3) {
                Log.e(tag, "finishSafely catch a RuntimeException ");
            }
        }
    }

    /**
     * get String SP
     *
     * @param context context
     * @param name sp name
     * @param key key
     * @return StringSP
     */
    public static String getStringSP(Context context, String name, String key) {
        if (context == null) {
            return "";
        }
        SharedPreferences sp = context.getSharedPreferences(
                name, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
