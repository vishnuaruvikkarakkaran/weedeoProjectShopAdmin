// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileAdapterClass$ViewHolder_ViewBinding implements Unbinder {
  private ProfileAdapterClass.ViewHolder target;

  @UiThread
  public ProfileAdapterClass$ViewHolder_ViewBinding(ProfileAdapterClass.ViewHolder target,
      View source) {
    this.target = target;

    target.img_data = Utils.findRequiredViewAsType(source, R.id.imagesdata, "field 'img_data'", ImageView.class);
    target.add_new_iteam = Utils.findRequiredViewAsType(source, R.id.add_new_iteam, "field 'add_new_iteam'", ImageView.class);
    target.card_view = Utils.findRequiredViewAsType(source, R.id.card_view, "field 'card_view'", CardView.class);
    target.statusIcon = Utils.findRequiredViewAsType(source, R.id.status, "field 'statusIcon'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileAdapterClass.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_data = null;
    target.add_new_iteam = null;
    target.card_view = null;
    target.statusIcon = null;
  }
}
