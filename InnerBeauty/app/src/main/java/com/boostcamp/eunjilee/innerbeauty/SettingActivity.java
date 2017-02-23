package com.boostcamp.eunjilee.innerbeauty;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {
//    @BindView(R.id.rv_setting)
//    protected RecyclerView mSettingRecyclerView;
//
//    private SettingAdapter mSettingAdapter;
//
//    private UserSharedPreference mUserPreference;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//        ButterKnife.bind(this);
//        initRecyclerView();
//        loadMyFavoriteContents();
//    }
//
//    private void initRecyclerView(){
//        mMyFavoriteContentsList = new ArrayList<>();
//        mMyFavoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mMyFavoriteContentsAdapter = new MyFavoriteContentsAdapter(this, mMyFavoriteContentsList);
//        mMyFavoriteRecyclerView.setAdapter(mMyFavoriteContentsAdapter);
//    }
//    private void loadMyFavoriteContents(){
//        ContentsModule contentsLoadModule = new ContentsModule();
//        //noinspection AccessStaticViaInstance,AccessStaticViaInstance,AccessStaticViaInstance
//        contentsLoadModule.getFavoriteContentsList(mUserPreference.getUserId(), new ContentsService.getFavoriteContentsListCallback() {
//            @Override
//            public void success(List<FavoriteContentsModel> favoriteContentList) {
//                mMyFavoriteContentsList.clear();
//                mMyFavoriteContentsList.addAll(favoriteContentList);
//                mMyFavoriteContentsAdapter.notifyDataSetChanged();
//                showAddFavoriteTextIfMyFavoriteListEmpty();
//            }
//            @Override
//            public void error(Throwable throwable) {
//                Log.v("daisy", "error!!");
//            }
//        });
//    }
//
//    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//        @Override
//        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyFavoriteContentsActivity.this);
//            alertDialog.setMessage("정말로 지우시겠습니까?").setCancelable(false).setPositiveButton("확인",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            deleteMyFavoriteContents(viewHolder.getAdapterPosition());
//                        }
//                    }).setNegativeButton("취소",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            mMyFavoriteContentsAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
//                            return;
//                        }
//                    });
//            AlertDialog alert = alertDialog.create();
//            alert.show();
//        }
//    };
//
//    private void deleteMyFavoriteContents(final int position){
//        ContentsModule.deleteFavoriteContents(mUserPreference.getUserId(),
//                mMyFavoriteContentsList.get(position).getContentsId(),
//                mMyFavoriteContentsList.get(position).getContentsType(),
//                new ContentsService.deleteFavoriteContentsCallback() {
//                    @Override
//                    public void success() {
//                        mMyFavoriteContentsList.remove(position);
//                        mMyFavoriteContentsAdapter.notifyItemRemoved(position);
//
//                        showAddFavoriteTextIfMyFavoriteListEmpty();
//                        Snackbar.make(mMyFavoriteRecyclerView, "관심 컨텐츠에서 삭제되었습니다.",  Snackbar.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void error(Throwable throwable) {
//                        Snackbar.make(mMyFavoriteRecyclerView, "삭제하지 못했습니다ㅠㅠ 재시도 해주세요",  Snackbar.LENGTH_LONG).show();
//                    }
//                });
//    }
//
//    private void showAddFavoriteTextIfMyFavoriteListEmpty(){
//        if(mMyFavoriteContentsAdapter.getItemCount()==0){
//            mContentsEmptyTextView.setVisibility(View.VISIBLE);
//            mContentsEmptyTextView.setText("컨텐츠 위에 '하트' 버튼을 클릭하면,\n나만의 관심 컨텐츠를 추가할 수 있어요~");
//        } else{
//            mContentsEmptyTextView.setVisibility(GONE);
//        }
//    }
}
