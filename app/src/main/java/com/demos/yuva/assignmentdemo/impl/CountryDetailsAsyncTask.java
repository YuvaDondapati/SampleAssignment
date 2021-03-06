package com.demos.yuva.assignmentdemo.impl;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.demos.yuva.assignmentdemo.model.CountryModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yuva_Dondapati on 1/24/2017.
 * CountryDetailsAsyncTask is a asynctask which runs in background and fetch the data
 */


public abstract class CountryDetailsAsyncTask extends AsyncTask<Void, Void, CountryModel> implements OnTaskCompletedListener
{
    private String json_url;
    HttpURLConnection connection;
    Context mcontext = null;

    RecyclerView mRecyclerview;
    URL url;
    private OnTaskCompletedListener listener;

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    public CountryDetailsAsyncTask(Context context)
    {
        mcontext = context;
    }
    @Override
    protected void onPostExecute(CountryModel result) {
        super.onPostExecute(result);
        postCountryResponseData(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected CountryModel doInBackground(Void... params) {

        String response  = getResponse();
        //parsing the json response
        CountryModel countryModel = JSONParser.parseJsonResponse(response);
        return countryModel;
    }

    /**
     * getting the response from json url
     * Setup HttpURLConnection class to send and receive data from php and mysql
     * @return
     */

    public String getResponse() {
        json_url ="https://dl.dropboxusercontent.com/u/746330/facts.json";

        try {
            url = new URL(json_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

        } catch (IOException e1) {
            e1.printStackTrace();
            return e1.toString();
        }
        try
        {
            int response_code = connection.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return (result.toString());
            }
            else
            {
                return("unsuccessfull conenction");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return  e.toString();
        }finally {
            connection.disconnect();
        }

    }


}
