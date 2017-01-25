package com.demos.yuva.assignmentdemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.demos.yuva.assignmentdemo.Adapter.DetailsListAdapter;
import com.demos.yuva.assignmentdemo.R;
import com.demos.yuva.assignmentdemo.impl.CountryDetailsAsyncTask;

import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;
import com.demos.yuva.assignmentdemo.model.CountryModel;

import java.util.ArrayList;

/**
 * ListDataFragment is a Fragment which is used to show the
 * list of our data using recycler view.
 */


public class ListDataFragment extends Fragment {

    private Context mcontext;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private DetailsListAdapter adapter;

    SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar progressBar;

    ArrayList<CountryDetailsModel> details = new ArrayList<CountryDetailsModel>();

    public ListDataFragment() {
        super();
    }

    /**
     *Creating the View and initialising the adapter for recyclerview
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_data, container, false);
        mcontext = getActivity();
        mRecyclerview = (RecyclerView)layout.findViewById(R.id.recycler);
        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mcontext);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeContainer);
        adapter = new DetailsListAdapter(mcontext);

        return layout;
    }

    /**
     * @param savedInstanceState
     * Setting the adapter to the recyclerview and logic for progressbar for refresh
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerview.setAdapter(adapter);

        showLoadingBar(false);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        fetchCountryDetails();
    }

    /**
     * Fetching data using AsyncTask
     */
    public void fetchCountryDetails(){

        CountryDetailsAsyncTask fetchData = new CountryDetailsAsyncTask(mcontext) {
            @Override
            public void postCountryResponseData(CountryModel countryInfo) {

                updateTitle(countryInfo.getTitle());
                adapter.setCategoriesInfo(countryInfo.getRows());

                showLoadingBar(true);

                if(mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        fetchData.execute();
    }

    //Refresh the adapter when swipedown
    public void refreshItems(){
        adapter.clear();
        fetchCountryDetails();
    }


    private void updateTitle(String title){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    /**
     * Logic to show loading bar
     * @param show
     */
    private void showLoadingBar(boolean show) {

        if(show){
            progressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }

    }

}
