/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;

/**
 * Materials
 *
 * @since 2024-08-30
 */
public class Material implements Serializable {
    private int id = 0;
    private String mimeType = "";
    private String unitType = "";
    private int unitId = 0;
    private String desc = "";
    private String uri = "";
    private String textContent = "";

    /**
     * Get index
     *
     * @return index
     */
    public int getId() {
        return id;
    }

    /**
     * Set index
     *
     * @param id index
     */
    public void setId(int id) {
        this.id = id;
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
     * Get unit type
     *
     * @return type
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Set unit type
     *
     * @param unitType type
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    /**
     * Get Unit id
     *
     * @return id
     */
    public int getUnitId() {
        return unitId;
    }

    /**
     * Set unit id
     *
     * @param unitId id
     */
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    /**
     * Get desc
     *
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set desc
     *
     * @param desc desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Get uri
     *
     * @return uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set uri
     *
     * @param uri uri
     */
    public void setUri(String uri) {
        this.uri = uri;
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