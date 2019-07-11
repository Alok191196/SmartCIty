package com.example.map.smartcity.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.map.smartcity.R;

import java.util.ArrayList;
import java.util.List;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    List<ArrayList> mGps = new ArrayList<>();

//
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.gps_data_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mGps.size();
    }

    public void setGpsList(ArrayList<String > gpsList) {
        mGps.add(gpsList);
        notifyDataSetChanged();
    }

    public void setGpsList1(   List<ArrayList> gpsList) {
        mGps = gpsList;
        notifyDataSetChanged();
    }

    public void setRGpsList(int id) {
        mGps.remove(id);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder   {

        TextView name;

        ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
            name.setMovementMethod(LinkMovementMethod.getInstance());

        }

        void bind(final int listIndex) {
            ArrayList l = mGps.get(listIndex);
            name.setText(Html.fromHtml(l.get(0).toString()));
            name.setMovementMethod(LinkMovementMethod.getInstance());
        }


    }
}
