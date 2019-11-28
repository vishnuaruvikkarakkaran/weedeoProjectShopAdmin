// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileActivity_ViewBinding implements Unbinder {
  private ProfileActivity target;

  private View view7f090072;

  private View view7f090042;

  private View view7f09004f;

  private View view7f090185;

  private View view7f090064;

  private View view7f090187;

  private View view7f090184;

  private View view7f0900ed;

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActivity_ViewBinding(final ProfileActivity target, View source) {
    this.target = target;

    View view;
    target.changeNumber = Utils.findRequiredViewAsType(source, R.id.changePhonenumber, "field 'changeNumber'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.chosePrimaryImage, "field 'image_pickup' and method 'onClick'");
    target.image_pickup = Utils.castView(view, R.id.chosePrimaryImage, "field 'image_pickup'", ImageView.class);
    view7f090072 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.addImageTobucket, "field 'addImageTobucket' and method 'onClick'");
    target.addImageTobucket = Utils.castView(view, R.id.addImageTobucket, "field 'addImageTobucket'", ImageView.class);
    view7f090042 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.loadPrimaryImage = Utils.findRequiredViewAsType(source, R.id.loadPrimaryImage, "field 'loadPrimaryImage'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.backToDash, "field 'backToDash' and method 'backToDash'");
    target.backToDash = Utils.castView(view, R.id.backToDash, "field 'backToDash'", ImageView.class);
    view7f09004f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.backToDash();
      }
    });
    view = Utils.findRequiredView(source, R.id.shopLogout, "field 'log_out' and method 'log_out'");
    target.log_out = Utils.castView(view, R.id.shopLogout, "field 'log_out'", TextView.class);
    view7f090185 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.log_out();
      }
    });
    view = Utils.findRequiredView(source, R.id.changeLocation, "field 'manage_address' and method 'manageAddress'");
    target.manage_address = Utils.castView(view, R.id.changeLocation, "field 'manage_address'", CircularImageView.class);
    view7f090064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.manageAddress();
      }
    });
    target.shop_name_field = Utils.findRequiredViewAsType(source, R.id.shopName, "field 'shop_name_field'", TextView.class);
    view = Utils.findRequiredView(source, R.id.shopPhone, "field 'shop_number_field' and method 'toggleBottomSheet'");
    target.shop_number_field = Utils.castView(view, R.id.shopPhone, "field 'shop_number_field'", TextView.class);
    view7f090187 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toggleBottomSheet();
      }
    });
    view = Utils.findRequiredView(source, R.id.shopLocation, "field 'shopLocation' and method 'onClick'");
    target.shopLocation = Utils.castView(view, R.id.shopLocation, "field 'shopLocation'", TextView.class);
    view7f090184 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyc, "field 'recyclerView'", RecyclerView.class);
    target.primaryImageStatus = Utils.findRequiredViewAsType(source, R.id.primaryImageStatus, "field 'primaryImageStatus'", TextView.class);
    view = Utils.findRequiredView(source, R.id.llShopTiming, "method 'shopTiming'");
    view7f0900ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shopTiming();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.changeNumber = null;
    target.image_pickup = null;
    target.addImageTobucket = null;
    target.loadPrimaryImage = null;
    target.backToDash = null;
    target.log_out = null;
    target.manage_address = null;
    target.shop_name_field = null;
    target.shop_number_field = null;
    target.shopLocation = null;
    target.recyclerView = null;
    target.primaryImageStatus = null;

    view7f090072.setOnClickListener(null);
    view7f090072 = null;
    view7f090042.setOnClickListener(null);
    view7f090042 = null;
    view7f09004f.setOnClickListener(null);
    view7f09004f = null;
    view7f090185.setOnClickListener(null);
    view7f090185 = null;
    view7f090064.setOnClickListener(null);
    view7f090064 = null;
    view7f090187.setOnClickListener(null);
    view7f090187 = null;
    view7f090184.setOnClickListener(null);
    view7f090184 = null;
    view7f0900ed.setOnClickListener(null);
    view7f0900ed = null;
  }
}
