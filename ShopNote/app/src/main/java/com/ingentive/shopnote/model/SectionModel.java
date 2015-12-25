package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class SectionModel {

    int sectionId;
    int sectionOrderNo;
    String sectionName;
    String sectionImage;

    public int getSectionOrderNo() {
        return sectionOrderNo;
    }

    public int getDefaultSection() {
        return defaultSection;
    }

    int defaultSection;

    public SectionModel() {

    }

    public SectionModel(int id, int sectionOrderNo, String name,String sectionImage,int defaultSection) {
        this.sectionId = id;
        this.sectionOrderNo = sectionOrderNo;
        this.sectionName = name;
        this.sectionImage = sectionImage;
        this.defaultSection = defaultSection;
    }
    public SectionModel(int sectionOrderNo,String name,String sectionImage,int defaultSection) {
        this.sectionOrderNo = sectionOrderNo;
        this.sectionName = name;
        this.sectionImage = sectionImage;
        this.defaultSection = defaultSection;
    }

    public int getSectionId() {
        return sectionId;
    }
    public String getSectionName() {
        return sectionName;
    }
    public String getSectionImage() {
        return sectionImage;
    }

}
