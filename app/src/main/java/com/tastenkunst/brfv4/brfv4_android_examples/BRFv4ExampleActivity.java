package com.tastenkunst.brfv4.brfv4_android_examples;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tastenkunst.brfv4.brfv4_android_examples.camera.CameraPermissionActivity;

public class BRFv4ExampleActivity extends CameraPermissionActivity implements View.OnClickListener {

	private static final String TAG = "BRFv4ExampleActivity";

	private BRFv4Fragment _brfFragment;
	Bundle b;
	private Toolbar toolbar;
	private String options = "";

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brfv4_example);

		options = getIntent().getStringExtra("option");

		toolbar = findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
		setSupportActionBar(toolbar);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	@Override
	protected void onCameraAccess() {

		Log.d(TAG, "onCameraAccess");

		if (_brfFragment == null) {
			_brfFragment = new BRFv4Fragment();
			Bundle b = new Bundle();
			b.putString("type", options);
			_brfFragment.setArguments(b);

			getFragmentManager().beginTransaction()
					.add(R.id._root, _brfFragment)
					.commit();
		}
	}

	@Override
	protected void onCameraAwaitingPermission() {
		Log.d(TAG, "onCameraAwaitingPermission");
	}

	@Override
	public void onClick(View v) {

	}
}