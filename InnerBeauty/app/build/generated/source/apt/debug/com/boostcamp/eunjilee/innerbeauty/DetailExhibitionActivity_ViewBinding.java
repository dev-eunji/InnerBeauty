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

public class DetailExhibitionActivity_ViewBinding<T extends DetailExhibitionActivity> implements Unbinder {
  protected T target;

  private View view2131558532;

  private View view2131558533;

  private View view2131558535;

  private View view2131558546;

  private View view2131558552;

  @UiThread
  public DetailExhibitionActivity_ViewBinding(final T target, View source) {
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
    view = Utils.findRequiredView(source, R.id.fab_naver, "field 'mNaverFab' and method 'shareNaver'");
    target.mNaverFab = Utils.castView(view, R.id.fab_naver, "field 'mNaverFab'", FloatingActionButton.class);
    view2131558533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareNaver(p0);
      }
    });
    target.mKakaoFab = Utils.findRequiredViewAsType(source, R.id.fab_kakao, "field 'mKakaoFab'", FloatingActionButton.class);
    view = Utils.findRequiredView(source, R.id.fab_facebook, "field 'mFaceBookFab' and method 'shareFacebook'");
    target.mFaceBookFab = Utils.castView(view, R.id.fab_facebook, "field 'mFaceBookFab'", FloatingActionButton.class);
    view2131558535 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareFacebook();
      }
    });
    target.mDetailTitleImageView = Utils.findRequiredViewAsType(source, R.id.imgv_content_detail_title, "field 'mDetailTitleImageView'", ImageView.class);
    target.mStartEndDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_start_end_date_value, "field 'mStartEndDateTextView'", TextView.class);
    target.mOpenTimeTextView = Utils.findRequiredViewAsType(source, R.id.tv_open_time_value, "field 'mOpenTimeTextView'", TextView.class);
    target.mCloseDateTextView = Utils.findRequiredViewAsType(source, R.id.tv_close_date_value, "field 'mCloseDateTextView'", TextView.class);
    target.mPlaceTextView = Utils.findRequiredViewAsType(source, R.id.tv_place_value, "field 'mPlaceTextView'", TextView.class);
    target.mPriceTextView = Utils.findRequiredViewAsType(source, R.id.tv_price_value, "field 'mPriceTextView'", TextView.class);
    target.mCallTextView = Utils.findRequiredViewAsType(source, R.id.tv_call_value, "field 'mCallTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_real_map, "method 'shoqRealMapAction'");
    view2131558546 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shoqRealMapAction();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_real_call, "method 'checkPermissionForRealCall'");
    view2131558552 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.checkPermissionForRealCall();
      }
    });
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
    view2131558533.setOnClickListener(null);
    view2131558533 = null;
    view2131558535.setOnClickListener(null);
    view2131558535 = null;
    view2131558546.setOnClickListener(null);
    view2131558546 = null;
    view2131558552.setOnClickListener(null);
    view2131558552 = null;

    this.target = null;
  }
}
