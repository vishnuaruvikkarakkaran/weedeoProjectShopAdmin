package com.weedeo.shopadmin.modules.profile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.model.Image_model;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();


    public GridViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList data) {
        super(context, resource, data);
        this.context=context;
        this.layoutResourceId=resource;
        this.data=data;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Image_model item = (Image_model) data.get(position);
        holder.imageTitle.setText(item.getName());
        holder.image.setImageBitmap(item.getPath());
        return row;
    }
    static class ViewHolder {
        TextView imageTitle;
        ImageView image;


    }
}

