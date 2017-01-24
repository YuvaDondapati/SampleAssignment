package com.demos.yuva.assignmentdemo.model;

/**
 * Created by Yuva_Dondapati on 1/24/2017.
 */

public class CountryDetailsModel {
    private String title;
    private String description;
    private String imageHref;

    public CountryDetailsModel(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
