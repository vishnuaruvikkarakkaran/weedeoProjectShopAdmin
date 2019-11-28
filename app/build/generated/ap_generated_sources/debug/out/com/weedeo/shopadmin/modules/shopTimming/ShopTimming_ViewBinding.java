// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.shopTimming;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShopTimming_ViewBinding implements Unbinder {
  private ShopTimming target;

  @UiThread
  public ShopTimming_ViewBinding(ShopTimming target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ShopTimming_ViewBinding(ShopTimming target, View source) {
    this.target = target;

    target.checkboxMonday = Utils.findRequiredViewAsType(source, R.id.checkboxMonday, "field 'checkboxMonday'", CheckBox.class);
    target.checkboxTuesday = Utils.findRequiredViewAsType(source, R.id.checkboxTuesday, "field 'checkboxTuesday'", CheckBox.class);
    target.checkboxWednesday = Utils.findRequiredViewAsType(source, R.id.checkboxWednesday, "field 'checkboxWednesday'", CheckBox.class);
    target.checkboxThursday = Utils.findRequiredViewAsType(source, R.id.checkboxThursday, "field 'checkboxThursday'", CheckBox.class);
    target.checkboxFriday = Utils.findRequiredViewAsType(source, R.id.checkboxFriday, "field 'checkboxFriday'", CheckBox.class);
    target.checkboxSaturday = Utils.findRequiredViewAsType(source, R.id.checkboxSaturday, "field 'checkboxSaturday'", CheckBox.class);
    target.checkboxSunday = Utils.findRequiredViewAsType(source, R.id.checkboxSunday, "field 'checkboxSunday'", CheckBox.class);
    target.tvMondayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvMondayOpenTime, "field 'tvMondayOpenTime'", TextView.class);
    target.tvTuesdayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvTuesdayOpenTime, "field 'tvTuesdayOpenTime'", TextView.class);
    target.tvWednesdayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvWednesdayOpenTime, "field 'tvWednesdayOpenTime'", TextView.class);
    target.tvThursdayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvThursdayOpenTime, "field 'tvThursdayOpenTime'", TextView.class);
    target.tvFridayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvFridayOpenTime, "field 'tvFridayOpenTime'", TextView.class);
    target.tvSaturdayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvSaturdayOpenTime, "field 'tvSaturdayOpenTime'", TextView.class);
    target.tvSundayOpenTime = Utils.findRequiredViewAsType(source, R.id.tvSundayOpenTime, "field 'tvSundayOpenTime'", TextView.class);
    target.tvMondayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvMondayCloseTime, "field 'tvMondayCloseTime'", TextView.class);
    target.tvTuesdayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvTuesdayCloseTime, "field 'tvTuesdayCloseTime'", TextView.class);
    target.tvWednesdayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvWednesdayCloseTime, "field 'tvWednesdayCloseTime'", TextView.class);
    target.tvThursdayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvThursdayCloseTime, "field 'tvThursdayCloseTime'", TextView.class);
    target.tvFridayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvFridayCloseTime, "field 'tvFridayCloseTime'", TextView.class);
    target.tvSaturdayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvSaturdayCloseTime, "field 'tvSaturdayCloseTime'", TextView.class);
    target.tvSundayCloseTime = Utils.findRequiredViewAsType(source, R.id.tvSundayCloseTime, "field 'tvSundayCloseTime'", TextView.class);
    target.ivTimeEditMonday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditMonday, "field 'ivTimeEditMonday'", ImageView.class);
    target.ivTimeEditTuesday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditTuesday, "field 'ivTimeEditTuesday'", ImageView.class);
    target.ivTimeEditWednesday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditWednesday, "field 'ivTimeEditWednesday'", ImageView.class);
    target.ivTimeEditThursday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditThursday, "field 'ivTimeEditThursday'", ImageView.class);
    target.ivTimeEditFriday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditFriday, "field 'ivTimeEditFriday'", ImageView.class);
    target.ivTimeEditSaturday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditSaturday, "field 'ivTimeEditSaturday'", ImageView.class);
    target.ivTimeEditSunday = Utils.findRequiredViewAsType(source, R.id.ivTimeEditSunday, "field 'ivTimeEditSunday'", ImageView.class);
    target.llMondaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llMondaySecondTiming, "field 'llMondaySecondTiming'", LinearLayout.class);
    target.llTuesdaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llTuesdaySecondTiming, "field 'llTuesdaySecondTiming'", LinearLayout.class);
    target.llWednesdaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llWednesdaySecondTiming, "field 'llWednesdaySecondTiming'", LinearLayout.class);
    target.llThursdaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llThursdaySecondTiming, "field 'llThursdaySecondTiming'", LinearLayout.class);
    target.llFridaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llFridaySecondTiming, "field 'llFridaySecondTiming'", LinearLayout.class);
    target.llSaturdaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llSaturdaySecondTiming, "field 'llSaturdaySecondTiming'", LinearLayout.class);
    target.llSundaySecondTiming = Utils.findRequiredViewAsType(source, R.id.llSundaySecondTiming, "field 'llSundaySecondTiming'", LinearLayout.class);
    target.tvMondayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvMondayOpenSecondTime, "field 'tvMondayOpenSecondTime'", TextView.class);
    target.tvTuesdayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvTuesdayOpenSecondTime, "field 'tvTuesdayOpenSecondTime'", TextView.class);
    target.tvWednesdayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvWednesdayOpenSecondTime, "field 'tvWednesdayOpenSecondTime'", TextView.class);
    target.tvThursdayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvThursdayOpenSecondTime, "field 'tvThursdayOpenSecondTime'", TextView.class);
    target.tvFridayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvFridayOpenSecondTime, "field 'tvFridayOpenSecondTime'", TextView.class);
    target.tvSaturdayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvSaturdayOpenSecondTime, "field 'tvSaturdayOpenSecondTime'", TextView.class);
    target.tvSundayOpenSecondTime = Utils.findRequiredViewAsType(source, R.id.tvSundayOpenSecondTime, "field 'tvSundayOpenSecondTime'", TextView.class);
    target.tvMondayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvMondayCloseSecondTime, "field 'tvMondayCloseSecondTime'", TextView.class);
    target.tvTuesdayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvTuesdayCloseSecondTime, "field 'tvTuesdayCloseSecondTime'", TextView.class);
    target.tvWednesdaycloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvWednesdaycloseSecondTime, "field 'tvWednesdaycloseSecondTime'", TextView.class);
    target.tvThursdayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvThursdayCloseSecondTime, "field 'tvThursdayCloseSecondTime'", TextView.class);
    target.tvFridayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvFridayCloseSecondTime, "field 'tvFridayCloseSecondTime'", TextView.class);
    target.tvSaturdayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvSaturdayCloseSecondTime, "field 'tvSaturdayCloseSecondTime'", TextView.class);
    target.tvSundayCloseSecondTime = Utils.findRequiredViewAsType(source, R.id.tvSundayCloseSecondTime, "field 'tvSundayCloseSecondTime'", TextView.class);
    target.updateTiming = Utils.findRequiredViewAsType(source, R.id.updateTiming, "field 'updateTiming'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ShopTimming target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.checkboxMonday = null;
    target.checkboxTuesday = null;
    target.checkboxWednesday = null;
    target.checkboxThursday = null;
    target.checkboxFriday = null;
    target.checkboxSaturday = null;
    target.checkboxSunday = null;
    target.tvMondayOpenTime = null;
    target.tvTuesdayOpenTime = null;
    target.tvWednesdayOpenTime = null;
    target.tvThursdayOpenTime = null;
    target.tvFridayOpenTime = null;
    target.tvSaturdayOpenTime = null;
    target.tvSundayOpenTime = null;
    target.tvMondayCloseTime = null;
    target.tvTuesdayCloseTime = null;
    target.tvWednesdayCloseTime = null;
    target.tvThursdayCloseTime = null;
    target.tvFridayCloseTime = null;
    target.tvSaturdayCloseTime = null;
    target.tvSundayCloseTime = null;
    target.ivTimeEditMonday = null;
    target.ivTimeEditTuesday = null;
    target.ivTimeEditWednesday = null;
    target.ivTimeEditThursday = null;
    target.ivTimeEditFriday = null;
    target.ivTimeEditSaturday = null;
    target.ivTimeEditSunday = null;
    target.llMondaySecondTiming = null;
    target.llTuesdaySecondTiming = null;
    target.llWednesdaySecondTiming = null;
    target.llThursdaySecondTiming = null;
    target.llFridaySecondTiming = null;
    target.llSaturdaySecondTiming = null;
    target.llSundaySecondTiming = null;
    target.tvMondayOpenSecondTime = null;
    target.tvTuesdayOpenSecondTime = null;
    target.tvWednesdayOpenSecondTime = null;
    target.tvThursdayOpenSecondTime = null;
    target.tvFridayOpenSecondTime = null;
    target.tvSaturdayOpenSecondTime = null;
    target.tvSundayOpenSecondTime = null;
    target.tvMondayCloseSecondTime = null;
    target.tvTuesdayCloseSecondTime = null;
    target.tvWednesdaycloseSecondTime = null;
    target.tvThursdayCloseSecondTime = null;
    target.tvFridayCloseSecondTime = null;
    target.tvSaturdayCloseSecondTime = null;
    target.tvSundayCloseSecondTime = null;
    target.updateTiming = null;
  }
}
