// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.phonesigninpage;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneSignInActivity_ViewBinding implements Unbinder {
  private PhoneSignInActivity target;

  private View view7f09007d;

  @UiThread
  public PhoneSignInActivity_ViewBinding(PhoneSignInActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhoneSignInActivity_ViewBinding(final PhoneSignInActivity target, View source) {
    this.target = target;

    View view;
    target.numberInput = Utils.findRequiredViewAsType(source, R.id.inputnumber, "field 'numberInput'", EditText.class);
    view = Utils.findRequiredView(source, R.id.continu, "field 'submit' and method 'submitButton'");
    target.submit = Utils.castView(view, R.id.continu, "field 'submit'", Button.class);
    view7f09007d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitButton(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneSignInActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.numberInput = null;
    target.submit = null;

    view7f09007d.setOnClickListener(null);
    view7f09007d = null;
  }
}
