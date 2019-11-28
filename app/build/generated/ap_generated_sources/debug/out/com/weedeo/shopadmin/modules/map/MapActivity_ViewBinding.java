// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.map;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapActivity_ViewBinding implements Unbinder {
  private MapActivity target;

  private View view7f090064;

  private View view7f090078;

  @UiThread
  public MapActivity_ViewBinding(MapActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MapActivity_ViewBinding(final MapActivity target, View source) {
    this.target = target;

    View view;
    target.displayAddres = Utils.findRequiredViewAsType(source, R.id.displayAddres, "field 'displayAddres'", TextView.class);
    target.selectLocationTextView = Utils.findRequiredViewAsType(source, R.id.selectLocationText, "field 'selectLocationTextView'", TextView.class);
    target.locationProgress = Utils.findRequiredViewAsType(source, R.id.locationProgressBar, "field 'locationProgress'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.changeLocation, "field 'changeLocation' and method 'changeLocation'");
    target.changeLocation = Utils.castView(view, R.id.changeLocation, "field 'changeLocation'", TextView.class);
    view7f090064 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changeLocation();
      }
    });
    view = Utils.findRequiredView(source, R.id.confirmLocation, "field 'confirmLocation' and method 'confirmLocationClick'");
    target.confirmLocation = Utils.castView(view, R.id.confirmLocation, "field 'confirmLocation'", Button.class);
    view7f090078 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.confirmLocationClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MapActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.displayAddres = null;
    target.selectLocationTextView = null;
    target.locationProgress = null;
    target.changeLocation = null;
    target.confirmLocation = null;

    view7f090064.setOnClickListener(null);
    view7f090064 = null;
    view7f090078.setOnClickListener(null);
    view7f090078 = null;
  }
}
