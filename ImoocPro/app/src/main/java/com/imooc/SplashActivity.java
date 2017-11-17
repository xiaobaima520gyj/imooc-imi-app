package com.imooc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.imooc.activity.GuideViewPagerActivity;
import com.imooc.mode.Constants;
import com.imooc.utils.SPUtils;

/**
 *
 * @author xiaobaima
 * @date 17-11-17
 */

public class SplashActivity extends AppCompatActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				boolean isFirst = (boolean) SPUtils.get(getBaseContext(), Constants.SPLASH_ISFIRST_LAUNCH, true);

				if (isFirst){
					Intent intent = new Intent(SplashActivity.this, GuideViewPagerActivity.class);
					startActivity(intent);
				}else {
					Intent intent = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intent);
				}

				finish();

			}
		}, 2000);
	}

}
