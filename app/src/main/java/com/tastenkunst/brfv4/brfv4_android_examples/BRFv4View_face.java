package com.tastenkunst.brfv4.brfv4_android_examples;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;

import brfv4.android.AspectRatioTextureView;
import brfv4.android.CameraUtils;
import brfv4.examples.BRFBasicJavaExample;
import brfv4.examples.face_tracking.track_single_face;
import brfv4.examples.point_tracking.track_points_and_face;

public class BRFv4View_face extends FrameLayout {

	private CameraUtils				_camUtils;
	private AspectRatioTextureView	_preview;
	private Bitmap					_previewImageData;
	private BRFBasicJavaExample		_example;

	public BRFv4View_face(Context context) {
		super(context);
	}

	public BRFv4View_face(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		// Please note: This is a TRIAL version for testing. Contact us for a commercial license.

		_camUtils   = new CameraUtils();

		_preview	= new AspectRatioTextureView(getContext());
		_preview.setSurfaceTextureListener(_surfaceTextureListener);

		// Choose only one of the following examples:

		// "+++ basic - face detection +++"

//		_example = new detect_in_whole_image(getContext()); 	// "basic - face detection - detect in whole image"
//		_example = new detect_in_center(getContext());			// "basic - face detection - detect in center"
//		_example = new detect_smaller_faces(getContext());		// "basic - face detection - detect smaller faces"
//		_example = new detect_larger_faces(getContext());		// "basic - face detection - detect larger faces"
//
		// "+++ basic - face tracking +++"

		_example = new track_single_face(getContext());			// "basic - face tracking - track single face"
//		_example = new track_multiple_faces(getContext());		// "basic - face tracking - track multiple faces"
//		_example = new candide_overlay(getContext());			// "basic - face tracking - candide overlay"

		// "+++ basic - point tracking +++"

//		_example = new track_multiple_points(getContext());		// "basic - point tracking - track multiple points"
//		_example = new track_points_and_face(getContext());		// "basic - point tracking - track points and face"

		// "+++ intermediate - face tracking +++"

//		_example = new restrict_to_center(getContext());		// "intermediate - face tracking - restrict to center"
//		_example = new extended_face_shape(getContext());		// "intermediate - face tracking - extended face"
//		_example = new smile_detection(getContext());			// "intermediate - face tracking - smile detection"
//		_example = new yawn_detection(getContext());			// "intermediate - face tracking - yawn detection"
//		_example = new png_mask_overlay();						// "intermediate - face tracking - png/mask overlay"	- not implemented
//		_example = new color_libs(getContext());				// "intermediate - face tracking - color libs"

		// "+++ advanced - face tracking +++"

//		_example = new blink_detection(getContext());			// "advanced - face tracking - blink detection"
//		_example = new Flare3D_example();						// "advanced - face tracking - Flare3D example"			- not implemented
//		_example = new face_texture_overlay();					// "advanced - face tracking - face texture overlay"	- not implemented
//		_example = new face_swap_two_faces();					// "advanced - face tracking - face swap (two faces)"	- not implemented

		addView(_preview);
		addView(_example);
	}

	private TextureView.SurfaceTextureListener _surfaceTextureListener =
			new TextureView.SurfaceTextureListener() {

		@Override
		public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {

			if(_camUtils.init()) {

				_previewImageData = _preview.getBitmap(_camUtils.height, _camUtils.width);

				updateLayout(_previewImageData.getWidth(), _previewImageData.getHeight());

				_camUtils.startStream(surfaceTexture);
				_camUtils.getimage(_camUtils.camera);
			}
		}

		@Override
		public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {}

		@Override
		public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
			_camUtils.stopStream();
			return true;
		}

		@Override
		public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
			_preview.getBitmap(_previewImageData);
		}
	};

	private void updateLayout(int width, int height) {

		Log.d("updateLayout", "w: " + width + " h: " + height);

		LayoutParams layoutParams = new LayoutParams(width, height);

		layoutParams.width		= LayoutParams.MATCH_PARENT;
		layoutParams.height		= LayoutParams.MATCH_PARENT;
		layoutParams.gravity	= Gravity.CENTER;

		_preview.setAspectRatio(width, height);
		_preview.setLayoutParams(layoutParams);

		_example.init(width, height, _previewImageData);

		layoutParams			= new LayoutParams(width, height);

		layoutParams.width		= width;
		layoutParams.height		= height;
		layoutParams.gravity	= Gravity.CENTER;

		_example.setLayoutParams(layoutParams);

		int viewWidth			= getWidth();
		int viewHeight			= getHeight();

		float viewRatio			= (float)viewWidth / (float)viewHeight;
		float canvasRatio		= (float)width / (float)height;
		float drawScale			= 1.0f;

		if(viewRatio > canvasRatio) {

			drawScale = (float)viewHeight / (float)height;

		} else {

			drawScale = (float)viewWidth / (float)width;
		}

		_example.setScaleX(drawScale);
		_example.setScaleY(drawScale);
	}
}
