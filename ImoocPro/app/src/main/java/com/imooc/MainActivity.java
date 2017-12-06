package com.imooc;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.imooc.fragment.NavDownLoadFragment;
import com.imooc.fragment.NavFindFragment;
import com.imooc.fragment.NavMainPageFragment;
import com.imooc.fragment.NavUserFragment;
import com.imooc.help.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		BottomNavigationViewHelper.disableShiftMode(navigation);
		navigation.setOnNavigationItemSelectedListener(this);
		navigation.setSelectedItemId(R.id.navigation_home);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		getSupportFragmentManager()
			.beginTransaction()
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
			.replace(R.id.content,TabFragment.from(item.getItemId()).fragment())
			.commit();
		return true;
	}

	private enum TabFragment {
		nav_homepage(R.id.navigation_home,NavMainPageFragment.class),
		nav_find(R.id.navigation_find,NavFindFragment.class),
		nav_download(R.id.navigation_download,NavDownLoadFragment.class),
		nav_user(R.id.navigation_user, NavUserFragment.class)
		;

		private Fragment fragment;
		private final int menuId;
		private final Class<? extends Fragment> clazz;

		TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
			this.menuId = menuId;
			this.clazz = clazz;
		}

		@NonNull
		public Fragment fragment() {
			if (fragment == null) {
				try {
					fragment = clazz.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					fragment = new Fragment();
				}
			}
			return fragment;
		}

		public static TabFragment from(int itemId) {
			for (TabFragment fragment : values()) {
				if (fragment.menuId == itemId) {
					return fragment;
				}
			}
			return nav_homepage;
		}

		public static void onDestroy() {
			for (TabFragment fragment : values()) {
				fragment.fragment = null;
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		TabFragment.onDestroy();
	}

}
