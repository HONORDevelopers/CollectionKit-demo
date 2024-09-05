/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * MaterialsContent
 *
 * @since 2024-08-30
 */
public class SharedMaterials implements Serializable {
    private String version = "";
    private long startTime = 0;
    private String fromPackage = "";
    private ArrayList<MaterialWithRel> materialsWithRel = null;
    private ArrayList<Material> materials = null;
    private boolean mResPromptSupport = false;

    /**
     * get ResPromptSupport
     *
     * @return resPromptSupport
     */
    public boolean getResPromptSupport() {
        return mResPromptSupport;
    }

    /**
     * set ResPromptSupport
     *
     * @param resPromptSupport resPromptSupport
     */
    public void setResPromptSupport(boolean resPromptSupport) {
        this.mResPromptSupport = resPromptSupport;
    }

    /**
     * Get version
     *
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set version
     *
     * @param version version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Get start time
     *
     * @return start time
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Set start time
     *
     * @param startTime start time
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Get from package
     *
     * @return from package
     */
    public String getFromPackage() {
        return fromPackage;
    }

    /**
     * Set from package
     *
     * @param fromPackage from package
     */
    public void setFromPackage(String fromPackage) {
        this.fromPackage = fromPackage;
    }

    /**
     * Get Materials with real
     *
     * @return array list
     */
    public ArrayList<MaterialWithRel> getMaterialsWithRel() {
        return materialsWithRel;
    }

    /**
     * Set Materials with real
     *
     * @param materialsWithRel array list
     */
    public void setMaterialsWithRel(ArrayList<MaterialWithRel> materialsWithRel) {
        this.materialsWithRel = materialsWithRel;
    }

    /**
     * Get Materials
     *
     * @return Materials
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }

    /**
     * SetMaterials
     *
     * @param materials Materials
     */
    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }
}