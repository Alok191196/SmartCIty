package com.example.map.smartcity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.map.smartcity.R;

import java.util.ArrayList;
import java.util.List;


public class RouteAdapter1 extends RecyclerView.Adapter<RouteAdapter1.ViewHolder> {

    List<ArrayList> mGps = new ArrayList<>();


//
    public RouteAdapter1(ListItemClickListener OnClickListener) {
        this.mOnClickListener = OnClickListener;
    }

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

    public interface ListItemClickListener {

        void onListItemClick(int id);
    }
//
    private ListItemClickListener mOnClickListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
//        TextView address;
//        TextView phone;
//        TextView OrderId;
//        Button b;
        ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Name);
//            address = (TextView) itemView.findViewById(R.id.Address);
//            phone = (TextView) itemView.findViewById(R.id.phone);
//            OrderId = (TextView) itemView.findViewById(R.id.OrderId);
////            b = (Button) itemView.findViewById(R.id.close);
//
            itemView.setOnClickListener(this);
        }

        void bind(final int listIndex) {
            ArrayList l = mGps.get(listIndex);
            name.setText(l.get(0).toString());
//            address.setText(l.get(1).toString());
//            phone.setText(l.get(2).toString());
//            OrderId.setText(l.get(3).toString());



        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            mOnClickListener.onListItemClick(pos);

        }

    }
}
