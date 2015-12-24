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

    public void setSectionOrderNo(int sectionOrderNo) {
        this.sectionOrderNo = sectionOrderNo;
    }
    public int getDefaultSection() {
        return defaultSection;
    }

    public void setDefaultSection(int defaultSection) {
        this.defaultSection = defaultSection;
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
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionImage(String sectionImage) {
        this.sectionImage = sectionImage;
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
