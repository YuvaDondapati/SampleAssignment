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

import com.demos.yuva.assignmentdemo.Adapter.DetailsListAdapter;
import com.demos.yuva.assignmentdemo.R;
import com.demos.yuva.assignmentdemo.impl.CountryDetailsAsyncTask;
import com.demos.yuva.assignmentdemo.impl.OnTaskCompletedListener;
import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;
import com.demos.yuva.assignmentdemo.model.CountryModel;

import java.util.ArrayList;
import java.util.List;


public class ListDataFragment extends Fragment {

    private Context mcontext;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private DetailsListAdapter adapter;

    SwipeRefreshLayout mSwipeRefreshLayout;

    ArrayList<CountryDetailsModel> details = new ArrayList<CountryDetailsModel>();

    public ListDataFragment() {
        // Required empty public constructor
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_data, container, false);

        mcontext = getActivity();
        // Inflate the layout for this fragment

        mRecyclerview = (RecyclerView)layout.findViewById(R.id.recycler);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mcontext);
        mRecyclerview.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipeContainer);

        adapter = new DetailsListAdapter(mcontext);

        return layout;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerview.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        fetchCountryDetails();

    }

    public void fetchCountryDetails(){

        CountryDetailsAsyncTask fetchData = new CountryDetailsAsyncTask(mcontext) {
            @Override
            public void postCountryResponseData(CountryModel countryInfo) {

                updateTitle(countryInfo.getTitle());
                adapter.setCategoriesInfo(countryInfo.getRows());

                if(mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        fetchData.execute();
    }

    public void refreshItems(){
        adapter.clear();

        fetchCountryDetails();
    }


    private void updateTitle(String title){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


}
