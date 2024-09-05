/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MaterialsWithRel
 *
 * @since 2024-08-30
 */
public class MaterialWithRel implements Serializable {
    private int unitId = 0;
    private String unitType = "";
    private String unitName = "";
    private String desc = "";

    private ArrayList<Content> contents = new ArrayList<>();

    private ArrayList<MaterialWithRel> contentsGroup = new ArrayList<>();

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
     * Get unit name
     *
     * @return unit name
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Set unit name
     *
     * @param unitName unit name
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
     * Get sub contents
     *
     * @return string
     */
    public ArrayList<Content> getContents() {
        return contents;
    }

    /**
     * Set subContents
     *
     * @param contents string
     */
    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    /**
     * Get Group Content
     *
     * @return group contents
     */
    public ArrayList<MaterialWithRel> getContentsGroup() {
        return contentsGroup;
    }

    /**
     * Set Group Content
     *
     * @param contentsGroup group contents
     */
    public void setContentsGroup(ArrayList<MaterialWithRel> contentsGroup) {
        this.contentsGroup = contentsGroup;
    }
}
