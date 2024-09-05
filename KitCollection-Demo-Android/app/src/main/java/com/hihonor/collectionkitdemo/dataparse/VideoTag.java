/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;

/**
 * 视频摘录素材中的 Tag
 *
 * @since 2024-08-30
 */
public class VideoTag implements Serializable {
    private String tagType = "";
    private long timeOffset = 0;
    private String fileUri = "";
    private String textContent = "";

    /**
     * 视频摘录素材的Tag Type
     *
     */
    public interface VideoTagType {
        String SHOT_AND_TEXT = "shotAndText";
        String TEXT = "text";
        String VIDEO_AND_SHOT = "videoShot";
        String EMPTY_TAG = "emptyTag";
    }

    /**
     * Get tag type
     *
     * @return tag type
     */
    public String getTagType() {
        return tagType;
    }

    /**
     * Set tag type
     *
     * @param tagType tag type
     */
    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    /**
     * Get time office set
     *
     * @return timeOffset
     */
    public Long getTimeOffset() {
        return timeOffset;
    }

    /**
     * Set time offset
     *
     * @param timeOffset timeOffset
     */
    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * Get file uri
     *
     * @return file uri
     */
    public String getFileUri() {
        return fileUri;
    }

    /**
     * Set file uri
     *
     * @param fileUri file uri
     */
    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    /**
     * Get text content
     *
     * @return text content
     */
    public String getTextContent() {
        return textContent;
    }

    /**
     * Set text content
     *
     * @param textContent text content
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}