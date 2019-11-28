// Generated code from Butter Knife. Do not modify!
package com.weedeo.shopadmin.modules.call;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.weedeo.shopadmin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoChatViewActivity_ViewBinding implements Unbinder {
  private VideoChatViewActivity target;

  @UiThread
  public VideoChatViewActivity_ViewBinding(VideoChatViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoChatViewActivity_ViewBinding(VideoChatViewActivity target, View source) {
    this.target = target;

    target.mRemoteContainer = Utils.findRequiredViewAsType(source, R.id.remote_video_view_container, "field 'mRemoteContainer'", FrameLayout.class);
    target.mLocalContainer = Utils.findRequiredViewAsType(source, R.id.local_video_view_container, "field 'mLocalContainer'", RelativeLayout.class);
    target.ivTakePhoto = Utils.findRequiredViewAsType(source, R.id.ivTakePhoto, "field 'ivTakePhoto'", ImageView.class);
    target.btn_scan = Utils.findRequiredViewAsType(source, R.id.btn_scan, "field 'btn_scan'", ImageView.class);
    target.rlUserMuteVideo = Utils.findRequiredViewAsType(source, R.id.rlUserMuteVideo, "field 'rlUserMuteVideo'", RelativeLayout.class);
    target.tvRemoteUserName = Utils.findRequiredViewAsType(source, R.id.tvRemoteUserName, "field 'tvRemoteUserName'", TextView.class);
    target.ivRemoteUserImage = Utils.findRequiredViewAsType(source, R.id.ivRemoteUserImage, "field 'ivRemoteUserImage'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoChatViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRemoteContainer = null;
    target.mLocalContainer = null;
    target.ivTakePhoto = null;
    target.btn_scan = null;
    target.rlUserMuteVideo = null;
    target.tvRemoteUserName = null;
    target.ivRemoteUserImage = null;
  }
}
