/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;

/**
 * Source Content
 *
 * @since 2024-08-30
 */
public class SourceContent implements Serializable {
    private String sourceType = "";
    private String mimeType = "";
    private String fileUri = "";
    private String textContent = "";

    /**
     * Get source type
     *
     * @return source type
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Set source type
     *
     * @param sourceType source type
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Get mime type
     *
     * @return mime type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set mime type
     *
     * @param mimeType mime type
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
     * Set text
     *
     * @param textContent text
     */
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
