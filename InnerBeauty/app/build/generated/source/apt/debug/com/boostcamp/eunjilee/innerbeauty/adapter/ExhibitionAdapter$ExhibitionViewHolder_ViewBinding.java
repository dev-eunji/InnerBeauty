// Generated code from Butter Knife. Do not modify!
package com.boostcamp.eunjilee.innerbeauty.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boostcamp.eunjilee.innerbeauty.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExhibitionAdapter$ExhibitionViewHolder_ViewBinding<T extends ExhibitionAdapter.ExhibitionViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ExhibitionAdapter$ExhibitionViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mExhibitionImageView = Utils.findRequiredViewAsType(source, R.id.imgv_list_exhibition, "field 'mExhibitionImageView'", ImageView.class);
    target.mExhibitionDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_exhibition_date, "field 'mExhibitionDateTextView'", TextView.class);
    target.mExhibitionPlaceTextView = Utils.findRequiredViewAsType(source, R.id.tv_exhibition_place, "field 'mExhibitionPlaceTextView'", TextView.class);
    target.mLikeBtn = Utils.findRequiredViewAsType(source, R.id.btn_like, "field 'mLikeBtn'", ToggleButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mExhibitionImageView = null;
    target.mExhibitionDateTextView = null;
    target.mExhibitionPlaceTextView = null;
    target.mLikeBtn = null;

    this.target = null;
  }
}
