package com.demos.yuva.assignmentdemo.impl;

import com.demos.yuva.assignmentdemo.model.CountryModel;

/**
 * Created by Yuva_Dondapati on 1/25/2017.
 */

public interface OnTaskCompletedListener {
    public void postCountryResponseData(CountryModel result);
}
