// Generated code from Butter Knife. Do not modify!
package com.boostcamp.eunjilee.innerbeauty.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boostcamp.eunjilee.innerbeauty.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExhibitionFragment_ViewBinding<T extends ExhibitionFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ExhibitionFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mExhibitionRecyclerView = Utils.findRequiredViewAsType(source, R.id.rv_exhibition, "field 'mExhibitionRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mExhibitionRecyclerView = null;

    this.target = null;
  }
}
