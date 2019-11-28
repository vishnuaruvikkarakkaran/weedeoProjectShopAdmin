package com.weedeo.shopadmin.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.model.request.ShopTimingModel;

import java.util.List;

public class ShopTimingAdapter extends RecyclerView.Adapter<ShopTimingAdapter.MyViewHolder> {

    private Context mContext;
    private List<ShopTimingModel> timingModelList;

    public ShopTimingAdapter(Context mContext, List<ShopTimingModel> timingModelList) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_timing_item_initialview, parent, false);
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
        }
    }
}
