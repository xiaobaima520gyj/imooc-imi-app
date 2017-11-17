package com.imooc.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.imooc.R;
import com.imooc.adapter.GuideViewPageAdapter;
import com.imooc.fragment.GuideOneFragment;
import com.imooc.fragment.GuideThreeFragment;
import com.imooc.fragment.GuideTwoFragment;

/**
 * ViewPager 引导
 */
public class GuideViewPagerActivity extends FragmentActivity implements OnPageChangeListener {
	private ViewPager mVPActivity;
	private GuideOneFragment mFragment1;
	private GuideTwoFragment mFragment2;
	private GuideThreeFragment mFragment3;
	private List<Fragment> mListFragment = new ArrayList<Fragment>();
	private PagerAdapter mPgAdapter;
	// 底部小点图片
	private ImageView[] dots;
	// 记录当前选中位置
	private int currentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_guide_viewpager);
		initView();
		initDots();
	}

	private void initView() {
		mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
		mFragment1 = new GuideOneFragment();
		mFragment2 = new GuideTwoFragment();
		mFragment3 = new GuideThreeFragment();
		mListFragment.add(mFragment1);
		mListFragment.add(mFragment2);
		mListFragment.add(mFragment3);
		mPgAdapter = new GuideViewPageAdapter(getSupportFragmentManager(),
			mListFragment);
		mVPActivity.setAdapter(mPgAdapter);
		mVPActivity.setOnPageChangeListener(this);
	}

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

		dots = new ImageView[mListFragment.size()];

		// 循环取得小点图片
		for (int i = 0; i < mListFragment.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
		}

		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态

	}

	private void setCurrentDot(int position) {
		if (position < 0 || position > mListFragment.size() - 1
			|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		currentIndex = position;
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int position) {
	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
		// 设置底部小点选中状态
		setCurrentDot(arg0);
	}
}