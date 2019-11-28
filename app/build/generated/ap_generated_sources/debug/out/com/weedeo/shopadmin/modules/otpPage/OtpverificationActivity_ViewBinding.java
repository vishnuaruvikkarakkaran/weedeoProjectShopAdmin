// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.otpPage;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.goodiebag.pinview.Pinview;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OtpverificationActivity_ViewBinding implements Unbinder {
  private OtpverificationActivity target;

  private View view7f090160;

  @UiThread
  public OtpverificationActivity_ViewBinding(OtpverificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OtpverificationActivity_ViewBinding(final OtpverificationActivity target, View source) {
    this.target = target;

    View view;
    target.count_down = Utils.findRequiredViewAsType(source, R.id.autvritext, "field 'count_down'", TextView.class);
    target.didntget = Utils.findRequiredViewAsType(source, R.id.didntget, "field 'didntget'", TextView.class);
    view = Utils.findRequiredView(source, R.id.resend, "field 'resend' and method 'resend'");
    target.resend = Utils.castView(view, R.id.resend, "field 'resend'", TextView.class);
    view7f090160 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.resend();
      }
    });
    target.editTextCode = Utils.findRequiredViewAsType(source, R.id.editTextCode, "field 'editTextCode'", Pinview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OtpverificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.count_down = null;
    target.didntget = null;
    target.resend = null;
    target.editTextCode = null;

    view7f090160.setOnClickListener(null);
    view7f090160 = null;
  }
}
