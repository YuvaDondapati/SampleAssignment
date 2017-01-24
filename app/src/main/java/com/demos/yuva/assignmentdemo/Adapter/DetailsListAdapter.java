package com.demos.yuva.assignmentdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demos.yuva.assignmentdemo.ListDataFragment;
import com.demos.yuva.assignmentdemo.R;
import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;

import java.util.List;

/**
 * Created by Yuva_Dondapati on 1/24/2017.
 */

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ViewHolder> {

    private List<CountryDetailsModel> mdetails;
    private Context mcontext;

    public DetailsListAdapter(Context context, List<CountryDetailsModel> details)
    {
     mcontext = context;
        mdetails = details;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.list_item, parent, false);

        //View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        CountryDetailsModel details = mdetails.get(position);

        // Set item views based on your views and data model
        TextView textView1 = holder.titletv;
        System.out.println("******in adapter *****"+details.getTitle());
        textView1.setText(details.getTitle());

        TextView textView2 = holder.descriptiontv;
        textView2.setText(details.getDescription());

        TextView textView3 = holder.imagetv;
        textView3.setText(details.getImageHref());

    }

    @Override
    public int getItemCount() {
        return mdetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titletv, descriptiontv, imagetv;

        public ViewHolder(View itemView) {
            super(itemView);
            titletv = (TextView)itemView.findViewById(R.id.title);
            descriptiontv = (TextView)itemView.findViewById(R.id.description);
            imagetv = (TextView)itemView.findViewById(R.id.imageee);

        }
    }
}
