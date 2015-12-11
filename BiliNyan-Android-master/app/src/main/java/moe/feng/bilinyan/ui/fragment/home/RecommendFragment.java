package moe.feng.bilinyan.ui.fragment.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import moe.feng.bilinyan.R;
import moe.feng.bilinyan.api.BangumiApi;
import moe.feng.bilinyan.api.RecommendApi;
import moe.feng.bilinyan.model.Bangumi;
import moe.feng.bilinyan.model.BasicMessage;
import moe.feng.bilinyan.model.Index;
import moe.feng.bilinyan.model.RecommendList;
import moe.feng.bilinyan.model.VideoItemInfo;
import moe.feng.bilinyan.ui.VideoViewActivity;
import moe.feng.bilinyan.ui.adapter.list.BangumiHorizontalRecyclerAdapter;
import moe.feng.bilinyan.ui.common.AbsRecyclerViewAdapter;
import moe.feng.bilinyan.util.AsyncTask;
import moe.feng.bilinyan.view.CircleProgressView;

@SuppressLint("ValidFragment")
public class RecommendFragment extends BaseHomeFragment {

	private ScrollView mScrollView;
	private LinearLayout mContainer;
	private CircleProgressView mCircleProgress;
	private RecyclerView mBangumiList;
	private BangumiHorizontalRecyclerAdapter mBangumiAdapter;

	private List<FrameLayout> cards;

	public static RecommendFragment newInstance() {
		RecommendFragment fragment = new RecommendFragment();
		return fragment;
	}

	@Override
	public int getLayoutResId() {
		return R.layout.fragment_tab_recommend;
	}

	@Override
	public void finishCreateView(Bundle state) {
		mScrollView = $(R.id.scrollable);
		mCircleProgress = $(R.id.circle_progress);
		mContainer = $(R.id.container);
		mBangumiList = $(R.id.bangumi_list);

		$(R.id.btn_bangumi_more).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO
			}
		});

		cards = new ArrayList<>();
		cards.add((FrameLayout) $(R.id.card_frame_0));
		cards.add((FrameLayout) $(R.id.card_frame_1));
		cards.add((FrameLayout) $(R.id.card_frame_2));
		cards.add((FrameLayout) $(R.id.card_frame_3));
		cards.add((FrameLayout) $(R.id.card_frame_4));
		cards.add((FrameLayout) $(R.id.card_frame_5));

		mBangumiList.setHasFixedSize(true);
		mBangumiList.setLayoutManager(
				new LinearLayoutManager(
						getApplicationContext(),
						LinearLayoutManager.HORIZONTAL,
						false
				)
		);
		mBangumiAdapter = new BangumiHorizontalRecyclerAdapter(mBangumiList, new ArrayList<Bangumi>());
		setBangumiAdapter(mBangumiAdapter);

		startGetTask();
	}

	private void setBangumiAdapter(BangumiHorizontalRecyclerAdapter adapter) {
		adapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(int position, AbsRecyclerViewAdapter.ClickableViewHolder holder) {

			}
		});
		mBangumiList.setAdapter(adapter);
	}

	private void startGetTask() {
		mCircleProgress.setVisibility(View.VISIBLE);
		mCircleProgress.spin();
		mContainer.setVisibility(View.GONE);

		new GetRecommendTask().execute();
	}

	private void finishGetTask() {
		mCircleProgress.setVisibility(View.GONE);
		mCircleProgress.stopSpinning();
		mContainer.setVisibility(View.VISIBLE);
	}

	@Override
	public void scrollToTop() {
		mScrollView.smoothScrollTo(mScrollView.getScrollX(), 0);
	}

	@Override
	public boolean canScrollVertically(int direction) {
		return mScrollView != null && mScrollView.canScrollVertically(direction);
	}

	@Override
	public void notifyIndexDataUpdate(Index data) {

	}

	private class GetBangumiTask extends AsyncTask<Void, Void, BasicMessage<ArrayList<Bangumi>>> {

		@Override
		protected BasicMessage<ArrayList<Bangumi>> doInBackground(Void... params) {
			return BangumiApi.getBangumi();
		}

		@Override
		protected void onPostExecute(BasicMessage<ArrayList<Bangumi>> msg) {
			finishGetTask();
			if (msg != null && msg.getCode() == BasicMessage.CODE_SUCCEED) {
				mBangumiAdapter = new BangumiHorizontalRecyclerAdapter(mBangumiList, msg.getObject());
				setBangumiAdapter(mBangumiAdapter);
			}
		}

	}

	private class GetRecommendTask extends AsyncTask<Void, Void, BasicMessage<RecommendList>> {

		@Override
		protected BasicMessage<RecommendList> doInBackground(Void... params) {
			return RecommendApi.getList(null, null, "6", RecommendApi.ORDER_HOT);
		}

		@Override
		protected void onPostExecute(BasicMessage<RecommendList> result) {
			if (result != null) {
				if (result.getCode() == BasicMessage.CODE_SUCCEED) {
					for (int i = 0; i < 6; i++) {
						FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
								FrameLayout.LayoutParams.MATCH_PARENT,
								FrameLayout.LayoutParams.MATCH_PARENT
						);

						cards.get(i).addView(createVideoCard(result.getObject().lists.get(i)), lp);
					}
					new GetBangumiTask().execute();
				}
			}
		}

	}

	private CardView createVideoCard(VideoItemInfo info) {
		CardView view = (CardView) getLayoutInflater().inflate(R.layout.card_item_home_recommend, null);

		((TextView) view.findViewById(R.id.video_title)).setText(info.title);
		Picasso.with(getApplicationContext())
				.load(info.pic)
				.into((ImageView) view.findViewById(R.id.video_preview));

		view.setTag(info);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (view.getTag() instanceof VideoItemInfo) {
					VideoItemInfo info = (VideoItemInfo) view.getTag();
					VideoViewActivity.launch(getSupportActivity(), info);
				}
			}
		});

		return view;
	}

}
