package com.imooc.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.imooc.MainActivity;
import com.imooc.R;
import com.imooc.mode.Constants;
import com.imooc.utils.SPUtils;
import com.imooc.widget.CustomVideoView;

/**
 * Created by xiaobaima on 17-11-17.
 */

public class GuideThreeFragment extends Fragment {
	private CustomVideoView videoview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_guide_three, container, false);
		init(view);
		return view;
	}

	private void init(View view) {
		videoview = (CustomVideoView)view.findViewById(R.id.videoview);
		//设置播放加载路径
		videoview.setVideoURI(Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.guide_3));
		//播放
		videoview.start();
		//循环播放
		videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mediaPlayer) {
				videoview.start();
			}
		});

		view.findViewById(R.id.prompty_view).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SPUtils.put(getActivity().getBaseContext(), Constants.SPLASH_ISFIRST_LAUNCH, false);
				Intent intent = new Intent(getActivity(), MainActivity.class);
				getActivity().startActivity(intent);
				getActivity().finish();
			}
		});

	}
}
