// Generated code from Butter Knife. Do not modify!
package com.boostcamp.eunjilee.innerbeauty;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailPlayActivity_ViewBinding<T extends DetailPlayActivity> implements Unbinder {
  protected T target;

  private View view2131558532;

  @UiThread
  public DetailPlayActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.fab, "field 'mFab' and method 'showShareButtons'");
    target.mFab = Utils.castView(view, R.id.fab, "field 'mFab'", FloatingActionButton.class);
    view2131558532 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showShareButtons(p0);
      }
    });
    target.mNaverFab = Utils.findRequiredViewAsType(source, R.id.fab_naver, "field 'mNaverFab'", FloatingActionButton.class);
    target.mKakaoFab = Utils.findRequiredViewAsType(source, R.id.fab_kakao, "field 'mKakaoFab'", FloatingActionButton.class);
    target.mFaceBookFab = Utils.findRequiredViewAsType(source, R.id.fab_facebook, "field 'mFaceBookFab'", FloatingActionButton.class);
    target.mDetailTitleImageView = Utils.findRequiredViewAsType(source, R.id.imgv_content_detail_title, "field 'mDetailTitleImageView'", ImageView.class);
    target.mStartEndDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_start_end_date_value, "field 'mStartEndDateTextView'", TextView.class);
    target.mOpenTimeTextView = Utils.findRequiredViewAsType(source, R.id.tv_open_time_value, "field 'mOpenTimeTextView'", TextView.class);
    target.mCloseDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_close_date_value, "field 'mCloseDateTextView'", TextView.class);
    target.mPlaceTextView = Utils.findRequiredViewAsType(source, R.id.tv_place_value, "field 'mPlaceTextView'", TextView.class);
    target.mPriceTextView = Utils.findRequiredViewAsType(source, R.id.tv_price_value, "field 'mPriceTextView'", TextView.class);
    target.mCallTextView = Utils.findRequiredViewAsType(source, R.id.tv_call_value, "field 'mCallTextView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mFab = null;
    target.mNaverFab = null;
    target.mKakaoFab = null;
    target.mFaceBookFab = null;
    target.mDetailTitleImageView = null;
    target.mStartEndDateTextView = null;
    target.mOpenTimeTextView = null;
    target.mCloseDateTextView = null;
    target.mPlaceTextView = null;
    target.mPriceTextView = null;
    target.mCallTextView = null;

    view2131558532.setOnClickListener(null);
    view2131558532 = null;

    this.target = null;
  }
}
