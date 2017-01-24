package com.demos.yuva.assignmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.demos.yuva.assignmentdemo.Adapter.DetailsListAdapter;
import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;

import java.util.ArrayList;
import java.util.List;


public class ListDataFragment extends Fragment {


    private RecyclerView mRecyclerview;

    private RecyclerView.LayoutManager mLayoutManager;
    DetailsListAdapter adapter;
    List<CountryDetailsModel> details = new ArrayList<CountryDetailsModel>();

    public ListDataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_list_data, container, false);

        String title[] = new String[] {
                "Yuva",
                "Sravanth",
                "Bhupal",
                "Srinivas"
        };
        String description[] = new String[] {
                "Yuva",
                "Sravanth",
                "Bhupal",
                "Srinivas"
        };
        String imagehref[] = new String[] {
                "Yuva",
                "Sravanth",
                "Bhupal",
                "Srinivas"
        };

        // Inflate the layout for this fragment

        mRecyclerview = (RecyclerView)layout.findViewById(R.id.recycler);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        for (int i=0; i<title.length; i++) {

            CountryDetailsModel model = new CountryDetailsModel(title[i], description[i], imagehref[i]);
            details.add(i,model);
        }
       /* CountryDetailsModel model = new CountryDetailsModel("yuva", "ravi", "Balaya");
        details.add(model);
*/
        System.out.println("*************title is****" + details.size());
        adapter = new DetailsListAdapter(container.getContext(), details);
        mRecyclerview.setAdapter(adapter);


        return layout;
    }


}
