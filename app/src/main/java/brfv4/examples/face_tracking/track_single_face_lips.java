 package brfv4.examples.face_tracking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import brfv4.BRFFace;
import brfv4.BRFManager;
import brfv4.android.DrawingUtils;
import brfv4.examples.BRFBasicJavaExample;
import brfv4.geom.Point;
import brfv4.geom.Rectangle;

public class track_single_face_lips extends BRFBasicJavaExample {

	ArrayList<String> type;

	public track_single_face_lips(Context context, ArrayList<String> type) {
		super(context);

		this.type = type;
	}

	@Override
	public void initCurrentExample(BRFManager brfManager, Rectangle resolution) {

		Log.d("BRFv4", "BRFv4 - basic - face tracking - track single face\n" +
			"Detect and track one face and draw the 68 facial landmarks.");

		// By default everything necessary for a single face tracking app
		// is set up for you in brfManager.init. There is actually no
		// need to configure much more for a jump start.

		brfManager.init(resolution, resolution, _appId);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void updateCurrentExample(BRFManager brfManager, Bitmap imageData, DrawingUtils draw) {

		// In a webcam example imageData is the mirrored webcam video feed.
		// In an image example imageData is the (not mirrored) image content.

		brfManager.update(imageData);

		// Drawing the results:

		draw.clear();

		// Face detection results: a rough rectangle used to start the face tracking.

		//draw.drawRects(brfManager.getAllDetectedFaces(),	false, 1.0, 0x00a1ff, 0.5);
		//draw.drawRects(brfManager.getMergedDetectedFaces(), false, 2.0, 0xffd200, 1.0);

		// Get all faces. The default setup only tracks one face.
		if (type.size() > 0) {
			for (int i = 0; i < type.size(); i++) {
				if (type.get(i).equalsIgnoreCase("lips"))
					lips(brfManager, draw);
				else if (type.get(i).equalsIgnoreCase("eyebrow"))
					eyebrow(brfManager, draw);
				else if (type.get(i).equalsIgnoreCase("eyeshadow"))
					eyeshadow(brfManager, draw);
				else if (type.get(i).equalsIgnoreCase("eyeliner"))
					eyeliner(brfManager, draw);
				else if (type.get(i).equalsIgnoreCase("face"))
					face(brfManager, draw);
				else if (type.get(i).equalsIgnoreCase("goggle"))
					eyes(brfManager, draw);
			}
		}
	}

	private void face(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> faces = brfManager.getFaces();

		for(int i = 0; i < faces.size(); i++) {

			BRFFace face = faces.get(i);

			if(face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				// Face tracking results: 68 facial feature points.

				//draw.drawTriangles(face.vertices, face.triangles, false, 1.0, 0x00a0ff, 0.4);
				draw.fillTriangles(face.vertices, face.triangles, false, 0x00a0ff, 0.2);
			}
		}
	}

	public void lips(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> faces = brfManager.getFaces();

		for(int i = 0; i < faces.size(); i++) {

			BRFFace face = faces.get(i);

			if(face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				// Face tracking results: 68 facial feature points.

				//draw.drawTriangles(	face.vertices, face.triangles, false, 1.0, 0x00a0ff, 0.4);
				//Log.e("Face", "Vertices - " + face.points.size());

				// LIPS
				/*Vector<Point> lips = new Vector<>();
				for (int j = 48; j < 68; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					lips.add(point);
				}
				Log.e("Face", "Vertices - " + lips.size() + ", " + lips.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawPoints(lips, 6.0, false, 0xff7900, 0.4);*/

				draw.fillTriangles(	face.vertices, libTriangles, false, 0xff7900, 0.8);
			}
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void eyes(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> faces = brfManager.getFaces();

		for(int i = 0; i < faces.size(); i++) {

			BRFFace face = faces.get(i);

			if (face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				//draw.drawPoints(face.vertices, 6.0, false, 0xff7900, 0.4);

				Vector<Point> eyeleft = new Vector<>();

				Point point = new Point();
				point.setTo(0,36);
				eyeleft.add(point);

				Point point1 = new Point();
				point1.setTo(36,0);
				eyeleft.add(point1);

				Vector<Point> eyeright = new Vector<>();

				Point point2 = new Point();
				point2.setTo(45,16);
				eyeright.add(point2);

				Point point3 = new Point();
				point3.setTo(16,45);
				eyeright.add(point3);

				draw.drawLine3(eyeleft, 3, false, 0x00a0ff, 0.4);
				draw.fillTriangles(face.vertices, eyeTriangles, false, 0xff7900, 0.8);
				draw.drawLine3(eyeright, 3, false, 0x00a0ff, 0.4);
			}
		}
	}

	public void eyebrow(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> eyebrows = brfManager.getFaces();

		for(int i = 0; i < eyebrows.size(); i++) {

			BRFFace face = eyebrows.get(i);

			if(face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				// Face tracking results: 68 facial feature points.

				//draw.drawTriangles(	face.vertices, face.triangles, false, 1.0, 0x00a0ff, 0.4);
				//Log.e("Face", "Vertices - " + face.points.size());

				//EYE BROW
				Vector<Point> eyebrow = new Vector<>();
				for (int j = 18; j < 22; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyebrow.add(point);
				}
				Log.e("Face", "Vertices - " + eyebrow.size() + ", " + eyebrow.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine(eyebrow, 10, false, 0x00a0ff, 0.4);

				Vector<Point> eyebrow1 = new Vector<>();
				for (int j = 22; j < 26; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyebrow1.add(point);
				}
				Log.e("Face", "Vertices - " + eyebrow1.size() + ", " + eyebrow1.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine(eyebrow1, 10, false, 0x00a0ff, 0.4);
			}
		}
	}

	public void eyeliner(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> faces = brfManager.getFaces();

		for(int i = 0; i < faces.size(); i++) {

			BRFFace face = faces.get(i);

			if(face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				// Face tracking results: 68 facial feature points.

				//draw.drawTriangles(	face.vertices, face.triangles, false, 1.0, 0x00a0ff, 0.4);
				//Log.e("Face", "Vertices - " + face.points.size());

				//EYE LINER
				Vector<Point> eyeliner = new Vector<>();
				for (int j = 36; j < 39; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyeliner.add(point);
				}
				Log.e("Face", "Vertices - " + eyeliner.size() + ", " + eyeliner.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine1(eyeliner, 3, false, 0x00a0ff, 0.4);

				Vector<Point> eyeliner1 = new Vector<>();
				for (int j = 42; j < 46; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyeliner1.add(point);
				}
				Log.e("Face", "Vertices - " + eyeliner1.size() + ", " + eyeliner1.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine1(eyeliner1, 3, false, 0x00a0ff, 0.4);
			}
		}
	}

	public void eyeshadow(BRFManager brfManager, DrawingUtils draw) {
		Vector<BRFFace> faces = brfManager.getFaces();

		for(int i = 0; i < faces.size(); i++) {

			BRFFace face = faces.get(i);

			if(face.state.equals(brfv4.BRFState.FACE_TRACKING_START) ||
					face.state.equals(brfv4.BRFState.FACE_TRACKING)) {

				// Face tracking results: 68 facial feature points.

				//draw.drawTriangles(	face.vertices, face.triangles, false, 1.0, 0x00a0ff, 0.4);
				//Log.e("Face", "Vertices - " + face.points.size());

				//EYE SHADOW
				Vector<Point> eyeliner = new Vector<>();
				for (int j = 36; j < 40; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyeliner.add(point);
				}
				Log.e("Face", "Vertices - " + eyeliner.size() + ", " + eyeliner.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine2(eyeliner, 10, false, 0x00a0ff, 0.4);

				Vector<Point> eyeliner1 = new Vector<>();
				for (int j = 42; j < 46; j++) {
					Point point = new Point();
					point.setTo(face.points.get(j).x,face.points.get(j).y);

					eyeliner1.add(point);
				}
				Log.e("Face", "Vertices - " + eyeliner1.size() + ", " + eyeliner1.get(0).toString() + ", " + face.points.get(0).x);
				draw.drawLine2(eyeliner1, 10, false, 0x00a0ff, 0.4);
			}
		}
	}

	private int[] libTriangles	= {
			48, 49, 60,
			48, 59, 60,
			49, 50, 61,
			49, 60, 61,
			50, 51, 62,
			50, 61, 62,
			51, 52, 62,
			52, 53, 63,
			52, 62, 63,
			53, 54, 64,
			53, 63, 64,
			54, 55, 64,
			55, 56, 65,
			55, 64, 65,
			56, 57, 66,
			56, 65, 66,
			57, 58, 66,
			58, 59, 67,
			58, 66, 67,
			59, 60, 67
			,					// mouth whole
			 60, 61, 67,
			 61, 62, 66,
			 61, 66, 67,
			 62, 63, 66,
			 63, 64, 65,
			 63, 65, 66
	};

	private int[] eyeTriangles	= {
			36, 37, 41,
			37, 38, 40,
			37, 41, 40,
			38, 40, 41,
			38, 39, 40,
			42, 43, 47,
			43, 44, 46,
			43, 47, 46,
			44, 46, 47,
			44, 45, 46
	};
}
