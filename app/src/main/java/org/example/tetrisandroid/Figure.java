package org.example.tetrisandroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * ...
 * @author Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class Figure 
{
	private Bitmap[] arrayFigure;
	private Bitmap bitmap;
	private float _size;
	private float[] fig;
	private float[] _figureCurrentCoordinates = new float[8];

 	private float x = 0;
 	private float y = 0;
 	private boolean touched = false;
	//private Speed speed;
	private float xf;
	private float yf;
	private float[] xCoordinate = new float[4];
	private float[] yCoordinate = new float[4];

	public Figure(Bitmap bitmap, float[] figure)
	{
		arrayFigure = new Bitmap[4];
		for (int j = 0; j < 4; j++)
		{
			arrayFigure[j] = bitmap;
		}
		fig = figure;
		this.bitmap = bitmap;
		_size = bitmap.getWidth();
 
 	}

 	public Bitmap getBitmap()
	{
  		return bitmap;
 	}
	
 	public void setBitmap(Bitmap bitmap) {
  		
		this.bitmap = bitmap;
 	}

 	public float getX() {
  	
		return x;
 	}
	
 	public void setX(float x) {
  		
		this.x = x;
 	}
	
 	public float getY() {
  		
		return y;
 	}
	
 	public void setY(float y) {
  		
		this.y = y;
 	}

 	public boolean isTouched() {
  		
		return touched;
 	}

 	public void setTouched(boolean touched) {
  		
		this.touched = touched;
 	}


 	public void draw(Canvas canvas, float x, float y)
	{
		if (!touched)
		{
			if(yf < 20) {

				this.x = x;
				this.y = y;
				figureDrow(canvas);
			}
			else {

				yf = 20;
				figureDrow(canvas);
			}
		}

 	}

	public void figureDrow(Canvas canvas) {

		for (int i = 0; i < fig.length; i += 2 )
		{
			xf = x + fig[i];
			yf = y + fig[i + 1];
			System.out.println("xf = " + xf + " yf = " + yf);
			System.out.println("*******drowFigure*****");
			_figureCurrentCoordinates[i] = xf * _size;
			_figureCurrentCoordinates[i + 1] = yf * _size;
		}

		int num = 0;
		for (int k = 0; k < _figureCurrentCoordinates.length; k += 2)
		{
			xCoordinate[num] = _figureCurrentCoordinates[k];
			yCoordinate[num] = _figureCurrentCoordinates[k + 1];
			num++;
		}
		for (int j= 0; j < arrayFigure.length; j++)
		{
			canvas.drawBitmap(arrayFigure[j], xCoordinate[j], yCoordinate[j], null);
		}
	}

	public void update(Canvas canvas) {
	
		if (!touched) 
		{
			if (yf < 20)
			{

			}
			else {

				yf = 20;
				figureDrow(canvas);
			}
		}
	}

 	public void handleActionDown(int eventX, int eventY)
	{
  		
		if (eventX >= (x - 3 * arrayFigure[0].getWidth()) && (eventX <= (x + 3 * arrayFigure[0].getWidth())))
		{
			if (eventY >= (y - 3 * arrayFigure[0].getHeight()) && (y <= (y + 3 * arrayFigure[0].getHeight())))
			{
    			setTouched(true);

   			} else {
			
    			setTouched(false);
   			}
			
  		} else {
   			
			setTouched(false);
  		}

 	}

	/*
	public Speed getSpeed() {
	
		return speed;
	}
	*/
}