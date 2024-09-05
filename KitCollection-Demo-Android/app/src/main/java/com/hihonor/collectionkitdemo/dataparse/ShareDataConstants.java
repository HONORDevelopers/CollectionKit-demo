/*
 * Copyright (c) Honor Device Co., Ltd. 2024-2024. All rights reserved.
 */

package com.hihonor.collectionkitdemo.dataparse;

/**
 * Share Data Constants
 *
 * @since 2024-08-30
 */
public class ShareDataConstants {
    /**
     * 素材顺序
     */
    public static final String INDEX = "index";

    /**
     * 素材类型
     */
    public static final String TYPE = "type";

    /**
     * 素材文本内容
     */
    public static final String TEXTCONTENT = "textContent";

    /**
     * 素材uri
     */
    public static final String URI = "uri";

    /**
     * 是否作为附件
     */
    public static final String INSERTTYPE = "insertType";

    /**
     * 素材关联关系
     */
    public static final String FORMATION = "formation";

    /**
     * 批注素材类型
     */
    public static final String ANNOTYPE = "annoType";

    /**
     * 批注素材类型-手写
     */
    public static final String HANDWRITING = "handwriting";

    /**
     * 批注素材类型-原图
     */
    public static final String ORIPIC = "OriPic";

    /**
     * 批注素材类型-合成图
     */
    public static final String GRRITFPIC = "GrritfPic";

    /**
     * 批注app
     */
    public static final String APPNAME = "appName";

    /**
     * 批注素材
     */
    public static final String ANNOTATION = "annotation";

    /**
     * 视频摘录素材
     */
    public static final String VIDEOEXTRACT = "videoExtract";

    /**
     * UnitType
     */
    public interface UnitType {
        String SINGLE_MATERIAL = "singleMaterial";
        String ANNOTATION = "annotation";
        String VIDEO_EXTRACT_UNIT = "videoExtracts";
        String MULTI_MATERIALS = "multiMaterials";
    }

    /**
     * ContentType
     */
    public interface ContentType {
        String TEXT = "text";
        String VIDEO_AND_SHOT = "videoShot";
        String VIDEO = "video";
    }

    /**
     * SourceType
     */
    public interface SourceType {
        String BACK_IMG = "backImg";
        String WRITING_TRACE = "writingTrace";
    }
}
