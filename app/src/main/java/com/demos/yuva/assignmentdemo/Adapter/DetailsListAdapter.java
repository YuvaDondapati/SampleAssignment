package com.demos.yuva.assignmentdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.yuva.assignmentdemo.R;
import com.demos.yuva.assignmentdemo.model.CountryDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuva_Dondapati on 1/24/2017.
 * DetailsListAdapter is a recyclerview adapter for managing the data of recyclerview
 */

public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ViewHolder> {

    private static final String EMPTY_STRING = "";
    private ArrayList<CountryDetailsModel> list = new ArrayList<CountryDetailsModel>();
    private Context mcontext;
    private LayoutInflater layoutInflater;

    public DetailsListAdapter(Context mcontext)
    {
        this.mcontext = mcontext;
        layoutInflater = LayoutInflater.from(mcontext);
    }

    public void setCategoriesInfo(ArrayList<CountryDetailsModel> categories){
        this.list = categories;
        notifyDataSetChanged();
    }

    //Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Replace the contents of a view
     *Set item views based on your views and data model
     */

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CountryDetailsModel details = list.get(position);

        if(details != null) {

            String title = details.getTitle();
            String description = details.getDescription();
            String imageHREF = details.getImageHref();

            if(!TextUtils.isEmpty(title))
                holder.titletv.setText(title);
            else
                holder.titletv.setText(EMPTY_STRING);

            if(!TextUtils.isEmpty(description))
                holder.descriptiontv.setText(description);
            else
                holder.descriptiontv.setText(EMPTY_STRING);


            if(!TextUtils.isEmpty(imageHREF)){
                Picasso
                        .with(mcontext)
                        .load(imageHREF)
                        .into(holder.imageView);

            }
        }
    }
    // Return the size of dataset
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titletv, descriptiontv;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titletv = (TextView)itemView.findViewById(R.id.title);
            descriptiontv = (TextView)itemView.findViewById(R.id.description);
            imageView = (ImageView)itemView.findViewById(R.id.category_image);

        }
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

}
