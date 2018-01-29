package org.example.tetrisandroid;

import java.util.Timer;

/**
 * ...
 * @author Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MainTimer
{
    private Timer _timer;

    private static boolean _played;
    public MainTimer()
    {
        _timer = new Timer();
        _timer.schedule(new MyTimeProcess(), 3000, 500);
    }

    public void setPlay( boolean value)
    {_played = value;}


    public boolean getPlay()
    {return _played;}
}



