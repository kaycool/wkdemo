package moe.feng.bilinyan.ui.adapter.list;

import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import moe.feng.bilinyan.R;
import moe.feng.bilinyan.model.Bangumi;
import moe.feng.bilinyan.ui.common.AbsRecyclerViewAdapter;

public class BangumiHorizontalRecyclerAdapter extends AbsRecyclerViewAdapter {

	private ArrayList<Bangumi> mList;

	public BangumiHorizontalRecyclerAdapter(RecyclerView recyclerView, ArrayList<Bangumi> list) {
		super(recyclerView);
		this.mList = list;
	}

	@Override
	public AbsRecyclerViewAdapter.ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		bindContext(parent.getContext());
		View v = LayoutInflater.from(getContext())
				.inflate(R.layout.card_item_bangumi_horizontal, parent, false);
		return new CardHolder(v);
	}

	@Override
	public void onBindViewHolder(AbsRecyclerViewAdapter.ClickableViewHolder cvh, int position) {
		super.onBindViewHolder(cvh, position);
		if (cvh instanceof CardHolder) {
			CardHolder holder = (CardHolder) cvh;

			holder.mTitleView.setText(getItem(position).title);
			holder.mWeekdayView.setText(
					String.format(
							getContext().getString(R.string.weekday_update),
							getContext().getResources()
									.getStringArray(R.array.weekdays)[getItem(position).weekday]
					)
			);

			Picasso.with(getContext())
					.load(Uri.parse(getItem(position).cover))
					.into(holder.mPreviewImage);
		}
	}

	public Bangumi getItem(int pos) {
		return mList.get(pos);
	}

	@Override
	public int getItemCount() {
		return Math.min(10, mList != null ? mList.size() : 0);
	}

	public class CardHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

		public ImageView mPreviewImage;
		public AppCompatTextView mTitleView, mWeekdayView;

		public CardHolder(View itemView) {
			super(itemView);
			mPreviewImage = $(R.id.bangumi_preview);
			mTitleView = $(R.id.bangumi_title);
			mWeekdayView = $(R.id.bangumi_weekday);
		}

	}

}
