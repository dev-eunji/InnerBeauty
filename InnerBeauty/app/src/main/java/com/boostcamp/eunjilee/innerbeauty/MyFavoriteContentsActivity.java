package com.boostcamp.eunjilee.innerbeauty;

import static android.view.View.GONE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.adapter.ExhibitionAdapter;
import com.boostcamp.eunjilee.innerbeauty.adapter.MyFavoriteContentsAdapter;
import com.boostcamp.eunjilee.innerbeauty.adapter.PlayAdapter;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.module.ContentsModule;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteContentsActivity extends AppCompatActivity {
    @BindView(R.id.tv_my_favorite_contents_empty)
    protected TextView mContentsEmptyTextView;
    @BindView(R.id.rv_my_favorite_contents)
    protected RecyclerView mMyFavoriteRecyclerView;
    private List<FavoriteContentsModel> mMyFavoriteContentsList;
    private MyFavoriteContentsAdapter mMyFavoriteContentsAdapter;
    private UserSharedPreference mUserPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite_contents);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mUserPreference = new UserSharedPreference(this);
        initRecyclerView();
        loadMyFavoriteContents();
        showAddFavoriteTextIfMyFavoriteListEmpty();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mMyFavoriteRecyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(){
        mMyFavoriteContentsList = new ArrayList<>();
        mMyFavoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMyFavoriteContentsAdapter = new MyFavoriteContentsAdapter(this, mMyFavoriteContentsList);
        mMyFavoriteRecyclerView.setAdapter(mMyFavoriteContentsAdapter);
    }
    private void loadMyFavoriteContents(){
        ContentsModule contentsLoadModule = new ContentsModule();
        //noinspection AccessStaticViaInstance,AccessStaticViaInstance,AccessStaticViaInstance
        contentsLoadModule.getFavoriteContentsList(mUserPreference.getUserId(), new ContentsService.getFavoriteContentsListCallback() {
            @Override
            public void success(List<FavoriteContentsModel> favoriteContentList) {
                mMyFavoriteContentsList.clear();
                mMyFavoriteContentsList.addAll(favoriteContentList);
                mMyFavoriteContentsAdapter.notifyDataSetChanged();
                showAddFavoriteTextIfMyFavoriteListEmpty();
            }
            @Override
            public void error(Throwable throwable) {
                Log.v("daisy", "error!!");
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyFavoriteContentsActivity.this);
            alertDialog.setMessage("정말로 지우시겠습니까?").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteMyFavoriteContents(viewHolder.getAdapterPosition());
                        }
                    }).setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mMyFavoriteContentsAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                            return;
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
    };

    private void deleteMyFavoriteContents(final int position){
        ContentsModule.deleteFavoriteContents(mUserPreference.getUserId(),
                mMyFavoriteContentsList.get(position).getContentsId(),
                mMyFavoriteContentsList.get(position).getContentsType(),
                new ContentsService.deleteFavoriteContentsCallback() {
                    @Override
                    public void success() {
                        mMyFavoriteContentsList.remove(position);
                        mMyFavoriteContentsAdapter.notifyItemRemoved(position);

                        showAddFavoriteTextIfMyFavoriteListEmpty();
                        Snackbar.make(mMyFavoriteRecyclerView, "관심 컨텐츠에서 삭제되었습니다.",  Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void error(Throwable throwable) {
                        Snackbar.make(mMyFavoriteRecyclerView, "삭제하지 못했습니다ㅠㅠ 재시도 해주세요",  Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void showAddFavoriteTextIfMyFavoriteListEmpty(){
        if(mMyFavoriteContentsAdapter.getItemCount()==0){
            mContentsEmptyTextView.setVisibility(View.VISIBLE);
            mContentsEmptyTextView.setText("컨텐츠 위에 '하트' 버튼을 클릭하면,\n나만의 관심 컨텐츠를 추가할 수 있어요~");
        } else{
            mContentsEmptyTextView.setVisibility(GONE);
        }
    }
}
