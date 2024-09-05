/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SubContents
 *
 * @since 2024-08-30
 */
public class Content implements Serializable {
    private String contentType = "";
    private String mimeType = "";
    private String fileUri = "";
    private String textContent = "";
    private ArrayList<SourceContent> sourceContents = null;
    private String posterUri = "";
    private long duration = 0;
    private ArrayList<VideoTag> videoTag = null;

    /**
     * Get content type
     *
     * @return content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Set content type
     *
     * @param contentType content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Get mimetype
     *
     * @return mimetype
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set mimetype
     *
     * @param mimeType mimetype
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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

    /**
     * Get resource content
     *
     * @return array list
     */
    public ArrayList<SourceContent> getSourceContents() {
        return sourceContents;
    }

    /**
     * Set resource content
     *
     * @param sourceContents array list
     */
    public void setSourceContents(ArrayList<SourceContent> sourceContents) {
        this.sourceContents = sourceContents;
    }

    /**
     * Get poster uri
     *
     * @return poster uri
     */
    public String getPosterUri() {
        return posterUri;
    }

    /**
     * Set poster uri
     *
     * @param posterUri poster uri
     */
    public void setPosterUri(String posterUri) {
        this.posterUri = posterUri;
    }

    /**
     * Get duration
     *
     * @return duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Set duration
     *
     * @param duration duration
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Get video tag
     *
     * @return array list
     */
    public ArrayList<VideoTag> getVideoTag() {
        return videoTag;
    }

    /**
     * Set vide tag
     *
     * @param videoTag tag
     */
    public void setVideoTag(ArrayList<VideoTag> videoTag) {
        this.videoTag = videoTag;
    }
}
