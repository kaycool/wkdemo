package moe.feng.bilinyan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

import moe.feng.bilinyan.R;
import moe.feng.bilinyan.api.SpApi;
import moe.feng.bilinyan.api.UserInfoApi;
import moe.feng.bilinyan.model.BasicMessage;
import moe.feng.bilinyan.model.Sp;
import moe.feng.bilinyan.ui.common.AbsActivity;
import moe.feng.bilinyan.util.AsyncTask;

public class SpViewActivity extends AbsActivity {

	private ImageView mPreviewImage;
	private AppCompatTextView mTitleText, mLastUpdateText, mDescText, mPlayTimeText, mVideoCountText;
	private SwipeRefreshLayout mRefreshLayout;
	private RecyclerView mVideoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();

		setContentView(R.layout.activity_sp_view);
	}

	@Override
	protected void setUpViews() {
		mActionBar.setDisplayHomeAsUpEnabled(true);

		mPreviewImage = $(R.id.sp_preview);
		mTitleText = $(R.id.sp_title);
		mLastUpdateText = $(R.id.sp_last_update_at);
		mDescText = $(R.id.sp_desc);
		mPlayTimeText = $(R.id.tv_play_time);
		mVideoCountText = $(R.id.tv_video_count);
		mRefreshLayout = $(R.id.refresh_layout);
		mVideoList = $(R.id.sp_video_list);

		mRefreshLayout.setColorSchemeResources(
				R.color.deep_purple_500, R.color.pink_500, R.color.orange_500, R.color.brown_500,
				R.color.indigo_500, R.color.blue_500, R.color.teal_500, R.color.green_500
		);
		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if (!mRefreshLayout.isRefreshing()) {
					mRefreshLayout.setRefreshing(true);
				}

			}
		});
	}

//	public class SpInfoGetTask extends AsyncTask<Void, Void, BasicMessage<Sp>> {
//
//		@Override
//		protected BasicMessage<Sp> doInBackground(Void... params) {
//			return SpApi.getSpInfo();
//		}
//
//		@Override
//		protected void onPostExecute(BasicMessage<Sp> msg) {
//			if (msg != null) {
//				if (msg.getCode() == BasicMessage.CODE_SUCCEED) {
//					userInfo = msg.getObject();
//					finishBasicInfoGetTask();
//				} else {
//
//				}
//			}
//		}
//
//	}
//
//	public class SpVideoGetTask extends AsyncTask<Integer, Void, BasicMessage<ArrayList<Sp.Item>>> {
//
//		@Override
//		protected BasicMessage<ArrayList<Sp.Item>> doInBackground(Integer... params) {
//			return UserInfoApi.getUserVideoList(mid, params[0]);
//		}
//
//		@Override
//		protected void onPostExecute(BasicMessage<ArrayList<Sp.Item>> msg) {
//			mRefreshLayout.setRefreshing(false);
//			if (msg != null) {
//				if (msg.getCode() == BasicMessage.CODE_SUCCEED) {
//					userVideos = msg.getObject();
//					list.addAll(userVideos.lists);
//					mAdapter.notifyDataSetChanged();
//				} else {
//
//				}
//			}
//		}
//
//	}

}
