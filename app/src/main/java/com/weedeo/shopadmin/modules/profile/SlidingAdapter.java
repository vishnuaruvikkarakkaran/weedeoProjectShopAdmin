package com.weedeo.shopadmin.modules.profile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.model.response.Profile_info_responds;

import java.util.List;

public class SlidingAdapter extends PagerAdapter {


    private Context context;
    String ShopId;
    public List<Profile_info_responds.DataBean.ShopProfilesBean> imageList;
    Profile_info_responds data;
    ProfileActivity profileActivity;


    public SlidingAdapter(Context context, Profile_info_responds datas) {
        this.context = context;
        this.notifyDataSetChanged();
        this.data = datas;
        this.imageList = data.getData().getShopProfiles();
        this.ShopId = Utils.LoadPreferences(context, Constants.KEY_SHOP_ID);
        this.profileActivity = (ProfileActivity) context;

    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        String Url = Constants.PRIMARY_IMAGE_URL + ShopId + "/shop_profile/" + imageList.get(position).getItem_path().trim();
        Log.e("imagePagerVie", "instantiateItem: " + Url);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slidingimages_layout, container, false);

        // assert layout != null;

        ImageView imageView = layout.findViewById(R.id.profieleImage);
        VideoView videoView = layout.findViewById(R.id.profileVideo);
        Button delete = layout.findViewById(R.id.delete);
        TextView statusIcon = layout.findViewById(R.id.statusIcon);
        TextView requestType = layout.findViewById(R.id.reqType);
        ImageView imgPrev = layout.findViewById(R.id.imgPrev);
        ImageView imgNext = layout.findViewById(R.id.imgNext);

        String status;
        if (imageList.get(position).getApprove_status() == 1) {
            status = "Request Pending";
            statusIcon.setBackgroundResource(R.drawable.cautionyellow);
        } else if (imageList.get(position).getApprove_status() == 2) {
            status = "Request Approved";
            statusIcon.setBackgroundResource(R.drawable.tick);
        } else {
            status = "File Rejected";
            statusIcon.setBackgroundResource(R.drawable.rejecticon);
        }

        if (imageList.get(position).getItem_path().trim().endsWith(".mp4")) {
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoPath(Url);
            videoView.start();
        } else {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Url)
                    .placeholder(R.drawable.add)
                    .into(imageView);
        }
        requestType.setText(status);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  profileActivity.deleteProfileImage(imageList.get(position).getId(), context);

            }
        });

        container.addView(layout);
        return layout;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.e("iteam", "getItemPosition: "+object );
        return SlidingAdapter.POSITION_NONE;
    }



}
