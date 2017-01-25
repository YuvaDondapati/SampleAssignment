package com.demos.yuva.assignmentdemo.model;

import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;

import java.util.ArrayList;

/**
 * Created by Yuva_Dondapati on 1/24/2017.
 */

public class CountryModel {

    private String title;
    private ArrayList<CountryDetailsModel> rows = new ArrayList<CountryDetailsModel>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<CountryDetailsModel> getRows() {
        return rows;
    }

    public void setRows(ArrayList<CountryDetailsModel> rows) {
        this.rows = rows;
    }
}
