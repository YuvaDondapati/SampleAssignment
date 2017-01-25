package com.demos.yuva.assignmentdemo.impl;

import android.util.Log;

import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;
import com.demos.yuva.assignmentdemo.model.CountryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Yuva_Dondapati on 1/25/2017.
 */

public class JSONParser {
    private static final String TAG_LIST_TITLE = "title";
    private static final String TAG_ARRAY = "rows";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_IMG_URL = "imageHref";

    public static CountryModel parseJsonResponse(String response)
    {
        CountryModel countryModel = new CountryModel();

        try {
            JSONObject jsonObject = new JSONObject(response);
            String list_title = jsonObject.getString(TAG_LIST_TITLE);
            countryModel.setTitle(list_title);

            JSONArray countryItemsJSON = jsonObject.getJSONArray(TAG_ARRAY);
            String parsedResponse = "";

            ArrayList<CountryDetailsModel> rows = new ArrayList<CountryDetailsModel>();
            //reading the response
            for(int i=0; i<countryItemsJSON.length(); i++)
            {
                JSONObject item = countryItemsJSON.getJSONObject(i);
                CountryDetailsModel countryDetails  = new CountryDetailsModel();

                if (item.isNull(TAG_TITLE))
                    countryDetails.setTitle(null);
                else
                    countryDetails.setTitle(item.getString(TAG_TITLE));

                if (item.isNull(TAG_DESCRIPTION))
                    countryDetails.setDescription(null);
                else
                    countryDetails.setDescription(item.getString(TAG_DESCRIPTION));

                if (item.isNull(TAG_IMG_URL))
                    countryDetails.setImageHref(null);
                else
                    countryDetails.setImageHref(item.getString(TAG_IMG_URL));


                // add the item to the list only data available
                if (countryDetails.getTitle() != null || countryDetails.getDescription() != null
                        || countryDetails.getImageHref() != null) {
                    rows.add(countryDetails);
                    parsedResponse = parsedResponse + countryDetails.getString();
                }

            }
            Log.d("Response", "Response::"+ parsedResponse);
            countryModel.setRows(rows);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countryModel;
    }



}
