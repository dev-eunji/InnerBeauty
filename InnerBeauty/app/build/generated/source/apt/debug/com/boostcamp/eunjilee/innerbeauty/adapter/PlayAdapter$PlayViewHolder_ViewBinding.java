// Generated code from Butter Knife. Do not modify!
package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boostcamp.eunjilee.innerbeauty.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlayAdapter$PlayViewHolder_ViewBinding<T extends PlayAdapter.PlayViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public PlayAdapter$PlayViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mPlayImageView = Utils.findRequiredViewAsType(source, R.id.imgv_list_play, "field 'mPlayImageView'", ImageView.class);
    target.mPlaytDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_play_date, "field 'mPlaytDateTextView'", TextView.class);
    target.mPlayPlaceTextView = Utils.findRequiredViewAsType(source, R.id.tv_play_place, "field 'mPlayPlaceTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mPlayImageView = null;
    target.mPlaytDateTextView = null;
    target.mPlayPlaceTextView = null;

    this.target = null;
  }
}
