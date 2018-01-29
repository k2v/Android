package org.example.tetrisandroid;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * ...
 * @author Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MainThread extends Thread {


 	private final static int 	MAX_FPS = 60;
 	private final static int	FRAME_PERIOD = 1000 / MAX_FPS;

 	private SurfaceHolder surfaceHolder;
 	private GameView gameView;
 	private boolean running;
 
 	public void setRunning(boolean running) {
  this.running = running;
 }

 	public MainThread( GameView gameView ) {
  		super();
		this.gameView = gameView;
		surfaceHolder = gameView.getHolder();
 	}

	@SuppressLint("WrongCall")
	@Override
	public void run() {

		Canvas canvas;

		while (running) {
	
			canvas = null;

			try {

				canvas = surfaceHolder.lockCanvas();

				synchronized (surfaceHolder) {

					gameView.onDraw(canvas);
				}
			} finally {

				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	//end finally
		}

  	}
}