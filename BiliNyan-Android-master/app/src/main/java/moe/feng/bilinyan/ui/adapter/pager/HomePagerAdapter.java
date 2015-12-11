package moe.feng.bilinyan.ui.adapter.pager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import moe.feng.bilinyan.R;
import moe.feng.bilinyan.model.Index;
import moe.feng.bilinyan.ui.fragment.home.BaseHomeFragment;
import moe.feng.bilinyan.ui.fragment.home.PlaceholderFragment;
import moe.feng.bilinyan.ui.fragment.home.RecommendFragment;
import moe.feng.bilinyan.ui.fragment.home.SimpleListFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

	private final String[] TITLES;
	private BaseHomeFragment[] fragments;

	public HomePagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		TITLES = context.getResources().getStringArray(R.array.sections);
		fragments = new BaseHomeFragment[TITLES.length];
	}

	@Override
	public Fragment getItem(int position) {
		if (fragments[position] == null) {
			switch (position) {
				case 0:
					fragments[position] = RecommendFragment.newInstance();
					break;
				case 1:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_ANIME);
					break;
				case 2:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_CARTOON);
					break;
				case 3:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_MUSIC);
					break;
				case 4:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_DANCE);
					break;
				case 5:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_GAME);
					break;
				case 6:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_TECHNOLOGY);
					break;
				case 7:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_ENTERTAINMENT);
					break;
				case 8:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_FUNNY);
					break;
				case 9:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_MOVIE);
					break;
				case 10:
					fragments[position] = SimpleListFragment.newInstance(SimpleListFragment.TYPE_TV_SERIES);
					break;
				default:
					fragments[position] = PlaceholderFragment.newInstance();
			}
		}
		return fragments[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	public void scrollToTop(int pos) {
		if (fragments[pos] != null) {
			fragments[pos].scrollToTop();
		}
	}

	public boolean canScrollVertically(int position, int direction) {
		return fragments[position] != null && fragments[position].canScrollVertically(direction);
	}

	public void notifyIndexDataUpdateAll(Index data) {
		for (BaseHomeFragment fragment : fragments) {
			if (fragment != null) {
				fragment.notifyIndexDataUpdate(data);
			}
		}
	}

}
