 package brfv4.examples.face_tracking;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Vector;

import brfv4.BRFFace;
import brfv4.BRFManager;
import brfv4.android.DrawingUtils;
import brfv4.examples.BRFBasicJavaExample;
import brfv4.geom.Point;
import brfv4.geom.Rectangle;

 public class track_single_face_eyebrow extends BRFBasicJavaExample {

     public track_single_face_eyebrow(Context context) {
         super(context);
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

         Vector<BRFFace> faces = brfManager.getFaces();

         for(int i = 0; i < faces.size(); i++) {

             BRFFace face = faces.get(i);

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
                 for (int j = 22; j < 27; j++) {
                     Point point = new Point();
                     point.setTo(face.points.get(j).x,face.points.get(j).y);

                     eyebrow1.add(point);
                 }
                 Log.e("Face", "Vertices - " + eyebrow1.size() + ", " + eyebrow1.get(0).toString() + ", " + face.points.get(0).x);
                 draw.drawLine(eyebrow1, 10, false, 0x00a0ff, 0.4);
             }
         }
     }
 }
