package brfv4.android;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraUtils {

	public Camera camera;
	public int width;
	public int height;
	public static File pictureFile;

	public boolean init() {

		// Find the front-facing camera
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();

		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {

			Camera.getCameraInfo(i, cameraInfo);

			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {

				camera = Camera.open(i);

				Camera.Parameters parameters = camera.getParameters();
				List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();

				for(int k = 0; k < previewSizes.size(); k++) {

                    Camera.Size size = previewSizes.get(k);

				    if((size.width == 640 || size.width == 480) && (size.height == 640 || size.height == 480)) {

                        Log.d("CameraUtils", "setting camera preview size: w: " + previewSizes.get(k).width + " h: " + previewSizes.get(k).height);

                        parameters.setPreviewSize(size.width , size.height);
                    }

                }

				List<int[]> previewFpsRanges = parameters.getSupportedPreviewFpsRange();
				parameters.setPreviewFpsRange(previewFpsRanges.get(0)[0],previewFpsRanges.get(0)[1]);

				camera.setParameters(parameters);
				camera.setDisplayOrientation(360 - cameraInfo.orientation);

				Camera.Parameters parameters2 = camera.getParameters();
				Camera.Size previewSize = parameters2.getPreviewSize();

				this.width = previewSize.width;
				this.height = previewSize.height;

                Log.d("CameraUtils", "camera preview size: w: " + previewSize.width + " h: " + previewSize.height);

				return true;
			}
		}

		return false;
	}

	public void getimage(Camera mcamera) {
		Camera.PictureCallback mPicture = new Camera.PictureCallback() {

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				pictureFile = getOutputMediaFile();
				Log.e("path file 11111111", ""+pictureFile);
				if (pictureFile == null) {
					return;
				}
				try {
					FileOutputStream fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		try {

			mcamera.startPreview();
			mcamera.takePicture(null, null, mPicture);
			Thread.sleep(2000);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private File getOutputMediaFile() {
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Sunil3");
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {

				return null;
			}
		}


		File mediaFile = new File(String.format(mediaStorageDir+ File.separator+"%d.jpg", System.currentTimeMillis()));

		return mediaFile;
	}

	public void startStream(SurfaceTexture surfaceTexture) {

		try {

			camera.setPreviewTexture(surfaceTexture);
			camera.startPreview();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopStream() {

		if (camera != null) {
			camera.stopPreview();
			camera.release();
			camera = null;
		}
	}
}
