// Generated code from Butter Knife. Do not modify!
package com.boostcamp.eunjilee.innerbeauty;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
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

  private View view2131624084;

  private View view2131624086;

  private View view2131624087;

  private View view2131624085;

  private View view2131624077;

  private View view2131624083;

  @UiThread
  public DetailExhibitionActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.fab, "field 'mFab' and method 'showShareButtons'");
    target.mFab = Utils.castView(view, R.id.fab, "field 'mFab'", FloatingActionButton.class);
    view2131624084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showShareButtons(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fab_naver, "field 'mNaverFab' and method 'shareNaver'");
    target.mNaverFab = Utils.castView(view, R.id.fab_naver, "field 'mNaverFab'", FloatingActionButton.class);
    view2131624086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareNaver(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fab_kakao, "field 'mKakaoFab' and method 'shareKakao'");
    target.mKakaoFab = Utils.castView(view, R.id.fab_kakao, "field 'mKakaoFab'", FloatingActionButton.class);
    view2131624087 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareKakao(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fab_facebook, "field 'mFaceBookFab' and method 'shareFacebook'");
    target.mFaceBookFab = Utils.castView(view, R.id.fab_facebook, "field 'mFaceBookFab'", FloatingActionButton.class);
    view2131624085 = view;
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
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar_detail, "field 'mToolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.tv_real_map, "method 'shoqRealMapAction'");
    view2131624077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shoqRealMapAction();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_real_call, "method 'checkPermissionForRealCall'");
    view2131624083 = view;
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
    target.mToolbar = null;

    view2131624084.setOnClickListener(null);
    view2131624084 = null;
    view2131624086.setOnClickListener(null);
    view2131624086 = null;
    view2131624087.setOnClickListener(null);
    view2131624087 = null;
    view2131624085.setOnClickListener(null);
    view2131624085 = null;
    view2131624077.setOnClickListener(null);
    view2131624077 = null;
    view2131624083.setOnClickListener(null);
    view2131624083 = null;

    this.target = null;
  }
}
