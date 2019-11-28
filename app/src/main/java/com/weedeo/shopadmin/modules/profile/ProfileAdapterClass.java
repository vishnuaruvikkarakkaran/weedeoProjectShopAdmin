package com.weedeo.shopadmin.modules.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.weedeo.shopadmin.R;
import com.weedeo.shopadmin.Utils.Constants;
import com.weedeo.shopadmin.Utils.Utils;
import com.weedeo.shopadmin.model.response.Profile_info_responds;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileAdapterClass extends RecyclerView.Adapter<ProfileAdapterClass.ViewHolder>  {

    private Context mContext;
    private ProfileActivity profileActivity;
    public List<Profile_info_responds.DataBean.ShopProfilesBean> imageList = new ArrayList<>();
    String ShopId;
    Profile_info_responds data;
    private int iteampsition;
    AlertDialog alert1;
    ViewPager mPager;
    private static String TAG = ProfileAdapterClass.class.getSimpleName();

    public ProfileAdapterClass(Context context, Profile_info_responds
            response) {
        this.mContext = context;
        this.notifyDataSetChanged();
        this.data = response;
        imageList = data.getData().getShopProfiles();
        imageList.add(data.getData().getShopProfiles().get(0));
        Log.e("ProfileAdapterImages", new Gson().toJson(imageList));
    }

    @NonNull
    @Override
    public ProfileAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        ProfileAdapterClass.ViewHolder viewHolder = null;

        if (viewType == 1) {
            view = View.inflate(mContext, R.layout.profile_last_item, null);
            viewHolder = new ProfileAdapterClass.ViewHolder(view);
            viewHolder.card_view.setVisibility(View.GONE);

        }else{
            view = View.inflate(mContext, R.layout.profile_data_set, null);
            viewHolder = new ProfileAdapterClass.ViewHolder(view);
        }

        ShopId = Utils.LoadPreferences(mContext, Constants.KEY_SHOP_ID);
        ButterKnife.bind(this, view);
        profileActivity = new ProfileActivity();
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (imageList.get(position).getItem_path() != null) {
            String Url = Constants.PRIMARY_IMAGE_URL + ShopId + "/shop_profile/" + imageList.get(position).getItem_path().trim();

            Log.e("type ", "is a video data" + Url);
            Glide.with(mContext)
                    .load(Url)
                    .placeholder(R.drawable.add)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder.img_data);
            Log.e("ewHolder", Url);



            if (imageList.get(position).getApprove_status() == 1) {

                holder.statusIcon.setBackgroundResource(R.drawable.cautionyellow);
            } else if (imageList.get(position).getApprove_status() == 2) {
                holder.statusIcon.setBackgroundResource(R.drawable.tick);
            } else {
                holder.statusIcon.setBackgroundResource(R.drawable.rjttxt);

            }

            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                    View view = li.inflate(R.layout.profile_data_inflator, null);
                    dialog.setView(view);
                    alert1 = dialog.create();
                    alert1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    alert1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    alert1.show();

                    SlidingAdapter slidingAdapter=new SlidingAdapter(mContext,data);
                    mPager = alert1.findViewById(R.id.pager);
                    mPager.setAdapter(slidingAdapter);
                    slidingAdapter.notifyDataSetChanged();
                    mPager.setCurrentItem(position);

                    TextView cancel = view.findViewById(R.id.cancel);
                    Button delete=view.findViewById(R.id.delete);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert1.dismiss();
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int iteam= mPager.getCurrentItem();

                            profileActivity.deleteProfileImage(imageList.get(iteam).getId(), mContext);
                            notifyDataSetChanged();
                            imageList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, getItemCount());
                            alert1.dismiss();


                        }
                    });
                }
            });

            holder.add_new_iteam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int primaryPickUp = 2;

                    if (imageList.size() >= 10) {
                        Log.e("image count", "image Count is 10 ");
                        Utils.Toast(mContext, "Maximum limit reached ");
                    } else {
                        int limit = 10 - imageList.size();

                        profileActivity.imagePickUpMethodFromAdapter(mContext,primaryPickUp, limit);
                    }

                }
            });

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void
    setAadapter(List<Profile_info_responds.DataBean.ShopProfilesBean>
                        shopProfiles) {
        this.imageList = shopProfiles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imagesdata)
        ImageView img_data;

        @BindView(R.id.add_new_iteam)
        ImageView add_new_iteam;


        @BindView(R.id.card_view)
        CardView card_view;

        @BindView(R.id.status)
        TextView statusIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (position == imageList.size() - 1&& position < 9)
            viewType = 1;
        else
            viewType = 0;
        return viewType;
        //return super.getItemViewType(position);
    }
}
