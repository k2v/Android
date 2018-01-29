package org.example.tetrisandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.Array;

/**
 * ...
 * @author Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class GameView extends SurfaceView implements
		SurfaceHolder.Callback {


 	private MainThread thread;
	
 	private Figure figure;
	private Canvas canvas;

	private Boolean mThreadStop;

	private static float _x;
	private static float _y;

	private Bitmap[] arrayFigure;

	private static final float[][][] FIGURES = createFigures();
	public static final MainTimer timer = new MainTimer();
	public static int GAME_STATE;


	private static float[][][] createFigures()
	{
		float[][][] array = new float[7][][];
		int type = -1;

		//cube
		//type = 0;
		array[++type] = new float[4][];
		array[type][0] = new float[]{1, 1, 1, 2, 2, 1, 2, 2};
		array[type][1] = array[type][0];
		array[type][2] = array[type][0];
		array[type][3] = array[type][0];

		//palka
		//type = 1;
		array[++type] = new float[4][];
		array[type][0] = new float[]{0, 1, 1, 1, 2, 1, 3, 1};
		array[type][1] = new float[]{1, 0, 1, 1, 1, 2, 1, 3};
		array[type][2] = array[type][0];
		array[type][3] = array[type][1];

		//z left
		//type = 2;
		array[++type] = new float[4][];
		array[type][0] = new float[]{0, 1, 1, 1, 1, 2, 2, 2};
		array[type][1] = new float[]{0, 2, 0, 1, 1, 1, 1, 0};
		array[type][2] = array[type][0];
		array[type][3] = array[type][1];

		//z right
		//type = 3;
		array[++type] = new float[4][];
		array[type][0] = new float[]{0, 2, 1, 2, 1, 1, 2, 1};
		array[type][1] = new float[]{0, 0, 0, 1, 1, 1, 1, 2};
		array[type][2] = array[type][0];
		array[type][3] = array[type][1];

		//T
		//type = 4;
		array[++type] = new float[4][];
		array[type][0] = new float[]{1, 1, 0, 1, 1, 0, 2, 1};
		array[type][1] = new float[]{1, 1, 0, 1, 1, 0, 1, 2};
		array[type][2] = new float[]{1, 1, 0, 1, 2, 1, 1, 2};
		array[type][3] = new float[]{1, 1, 1, 0, 2, 1, 1, 2};

		//L
		//type = 5;
		array[++type] = new float[4][];
		array[type][0] = new float[]{1, 1, 1, 0, 1, 2, 2, 2};
		array[type][1] = new float[]{1, 1, 0, 1, 2, 1, 2, 0};
		array[type][2] = new float[]{1, 1, 0, 0, 1, 0, 1, 2};
		array[type][3] = new float[]{1, 1, 0, 2, 0, 1, 2, 1};

		//Г
		//type = 6;
		array[++type] = new float[4][];
		array[type][0] = new float[]{1, 1, 2, 0, 1, 0, 1, 2};
		array[type][1] = new float[]{1, 1, 0, 0, 0, 1, 2, 1};
		array[type][2] = new float[]{1, 1, 0, 2, 1, 2, 1, 0};
		array[type][3] = new float[]{1, 1, 0, 1, 2, 1, 2, 2};

		return array;
	}

 	public GameView(Context context) {
	
  		super(context);
  		getHolder().addCallback(this);
		mThreadStop = false;

		//current figure
		float[] fig = FIGURES[0][0];
		_x = 3;
		_y = 0;

		figure = new Figure(BitmapFactory.decodeResource(getResources(), R.drawable.shape1), fig );
  		thread = new MainThread(this);
		setFocusable(true);
 	}

 	@Override
 	public void surfaceChanged(SurfaceHolder holder, int format, int width,
   		int height) {
 	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThreadStop = true;
		boolean retry = true;
		thread.setRunning(false);

		while (retry) {
			try {
				thread.join();
				retry = false;

			} catch (InterruptedException e) {

			}
		}
	}

	public void start()
	{
		timer.setPlay(true);
	}


	public static void tetrisStep()
	{
		_y++;
	}


	public boolean onTouchEvent(MotionEvent event) {
	
  		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{
   			figure.handleActionDown((int)event.getX(), (int)event.getY());
   			if (event.getY() > getHeight() - 50) 
			{
				thread.setRunning(false);
    			((Activity)getContext()).finish();
   			} else {

   			}
  		} 
		if (event.getAction() == MotionEvent.ACTION_MOVE) 
		{
   			if (figure.isTouched()) 
			{
    			figure.setX((int)event.getX());
    			figure.setY((int)event.getY());
   			}
  		} 
		if (event.getAction() == MotionEvent.ACTION_UP) 
		{
   			if (figure.isTouched()) 
			{
				figure.setTouched(false);
   			}
  		}
  		return true;
 	}

	@Override
	protected void onDraw(Canvas canvas) {

		if(!mThreadStop)
		{
			canvas.drawColor(Color.WHITE);
			figure.draw(canvas, _x, _y);
		}
 	}

 	public void update(Canvas canvas) {

		if(!mThreadStop)
		{
			figure.update(canvas);
		}
	}
}