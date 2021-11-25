package brfv4.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.tastenkunst.brfv4.brfv4_android_examples.R;

import java.util.Vector;

import brfv4.geom.Point;
import brfv4.geom.Rectangle;

public class DrawingUtils {

	protected Paint params;
	protected Canvas graphics;

	public DrawingUtils() {
		params = new Paint();
		graphics = null;
	}

	public Canvas getCanvas() {
		return graphics;
	}

	public void setCanvas(Canvas canvas) {
		graphics = canvas;
	}

	public void updateLayout(int width, int height) {}

	public void clear() {}

	public int getColor(int color, double alpha) {
		return ((((int)(alpha * 255)) & 0xff) << 24) + color;
	}

	public void drawVertices(float[] vertices, double radius, boolean clear,
			int fillColor, double fillAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(fillColor, fillAlpha));
		params.setStyle(Paint.Style.FILL);

		graphics.drawPoints(vertices, params);
	}

	public void drawTriangles(float[] vertices, int[] triangles, boolean clear,
			double lineThickness, int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)lineThickness);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.STROKE);
		params.setAntiAlias(true);

		Canvas g = graphics;

		int i = 0;
		int l = triangles.length;

		while(i < l) {
			int ti0 = triangles[i];
			int ti1 = triangles[i + 1];
			int ti2 = triangles[i + 2];

			float x0 = vertices[ti0 * 2];
			float y0 = vertices[ti0 * 2 + 1];
			float x1 = vertices[ti1 * 2];
			float y1 = vertices[ti1 * 2 + 1];
			float x2 = vertices[ti2 * 2];
			float y2 = vertices[ti2 * 2 + 1];

			g.drawLine(x0, y0, x1, y1, params);
			g.drawLine(x1, y1, x2, y2, params);
			g.drawLine(x2, y2, x0, y0, params);

			i+=3;
		}
	}

	public void fillTriangles(float[] vertices, int[] triangles, boolean clear,
			int fillColor, double fillAlpha) {

		params.reset();
		params.setColor(getColor(fillColor, fillAlpha));
		params.setStyle(Paint.Style.FILL);
		params.setAntiAlias(true);

		Canvas g = graphics;

		int i = 0;
		int l = triangles.length;

		while(i < l) {
			int ti0 = triangles[i];
			int ti1 = triangles[i + 1];
			int ti2 = triangles[i + 2];

			float x0 = vertices[ti0 * 2];
			float y0 = vertices[ti0 * 2 + 1];
			float x1 = vertices[ti1 * 2];
			float y1 = vertices[ti1 * 2 + 1];
			float x2 = vertices[ti2 * 2];
			float y2 = vertices[ti2 * 2 + 1];


			Path path = new Path();
			path.moveTo(x0, y0);
			path.lineTo(x1, y1);
			path.lineTo(x2, y2);
			path.lineTo(x0, y0);
			path.close();

			g.drawPath(path, params);

			i+=3;
		}
	}

	public void drawTexture(float[] vertices, int[] triangles, float[] uvData, Bitmap texture) {
		//TODO: implement
	}

	public void drawRect(Rectangle rect, boolean clear,
			double lineThickness, int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)lineThickness);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.STROKE);

		graphics.drawRect((float)rect.x, (float)rect.y, (float)(rect.x + rect.width), (float)(rect.y + rect.height), params);
	}

	public void drawRects(Vector<Rectangle> rects, boolean clear,
			double lineThickness, int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)lineThickness);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.STROKE);

		int i = 0;
		int l = rects.size();
		Rectangle rect = null;

		for(; i < l; i++) {
			rect = rects.get(i);
			graphics.drawRect((float)rect.x, (float)rect.y, (float)(rect.x + rect.width), (float)(rect.y + rect.height), params);
		}
	}

	public void drawPoint(Point point, double radius, boolean clear,
			int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.FILL);

		graphics.drawCircle((float)point.x, (float)point.y, (float)radius, params);
	}

	public void drawPoints(float[] points, double radius, boolean clear,
			int lineColor, double lineAlpha) {
		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.FILL);

		graphics.drawPoints(points, params);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void drawImage(Context context, float left, float top, float right, float bottom, double radius, boolean clear,
						  int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.FILL);
		params.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
		params.setAntiAlias(true);
		params.setAlpha(120);

		graphics.drawOval(left, top, right, bottom, params);
	}

	public void drawLine(Vector<Point> points, double radius, boolean clear,
						   int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setStyle(Paint.Style.STROKE);
		params.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
		params.setAntiAlias(true);
		params.setAlpha(120);

		int l = points.size();

		for (int j = 0; j < l; j++) {
			if (j == (points.size()-1)) {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j).x * 1);
				int pointY1 = (int) ((float)points.get(j).y * 1);

				graphics.drawLine(pointX, pointY + 4, pointX1, pointY1 + 4, params);
			} else {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j + 1).x * 1);
				int pointY1 = (int) ((float)points.get(j + 1).y * 1);

				graphics.drawLine(pointX, pointY + 4, pointX1, pointY1 + 4, params);
			}
		}
		/*fillPath.moveTo((float)points.get(0).x * 1, (float)points.get(0).y * 1);
		for (int j = 0; j < l; j++) {
			fillPath.lineTo((float)points.get(j).x * 1, (float)points.get(j).y * 1);
			graphics.drawPath(fillPath, params);
		}*/

	}

	public void drawLine1(Vector<Point> points, double radius, boolean clear,
						 int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setMaskFilter(new BlurMaskFilter(4, BlurMaskFilter.Blur.NORMAL));
		params.setAntiAlias(true);
		params.setAlpha(150);

		int l = points.size();

		for (int j = 0; j < l; j++) {
			if (j == (points.size()-1)) {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j).x * 1);
				int pointY1 = (int) ((float)points.get(j).y * 1);

				graphics.drawLine(pointX, pointY + 4, pointX1, pointY1 + 4, params);
			} else {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j + 1).x * 1);
				int pointY1 = (int) ((float)points.get(j + 1).y * 1);

				graphics.drawLine(pointX, pointY + 4, pointX1, pointY1 + 4, params);
			}
		}
		/*fillPath.moveTo((float)points.get(0).x * 1, (float)points.get(0).y * 1);
		for (int j = 0; j < l; j++) {
			fillPath.lineTo((float)points.get(j).x * 1, (float)points.get(j).y * 1);
			graphics.drawPath(fillPath, params);
		}*/

	}

	public void drawLine2(Vector<Point> points, double radius, boolean clear,
						  int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
		params.setAntiAlias(true);
		params.setAlpha(150);

		int l = points.size();

		for (int j = 0; j < l; j++) {
			if (j == (points.size()-1)) {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j).x * 1);
				int pointY1 = (int) ((float)points.get(j).y * 1);

				graphics.drawLine(pointX - 20, pointY - 25, pointX1, pointY1 - 25, params);
			} else {
				int pointX = (int) ((float)points.get(j).x * 1);
				int pointY = (int) ((float)points.get(j).y * 1);
				int pointX1 = (int) ((float)points.get(j + 1).x * 1);
				int pointY1 = (int) ((float)points.get(j + 1).y * 1);

				graphics.drawLine(pointX - 20, pointY - 25, pointX1, pointY1 - 25, params);
			}
		}
	}

	public void drawLine3(Vector<Point> points, double radius, boolean clear,
						  int lineColor, double lineAlpha) {

		params.reset();
		params.setStrokeWidth((float)radius);
		params.setColor(getColor(lineColor, lineAlpha));
		params.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL));
		params.setAntiAlias(true);
		params.setAlpha(150);

		int l = points.size();
		Log.e("point", "szie - " + l);

		for (int j = 0; j < l; j++) {
			int pointX = (int) ((float)points.get(j).x * 1);
			int pointY = (int) ((float)points.get(j).y * 1);
			int pointX1 = (int) ((float)points.get(j + 1).x * 1);
			int pointY1 = (int) ((float)points.get(j + 1).y * 1);

			graphics.drawLine(pointX, pointY - 25, pointX1 + 5, pointY1 - 25, params);
		}
	}
}
