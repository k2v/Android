package org.example.tetrisandroid;

import java.util.TimerTask;

/**
 * ...
 * @author Vladislav Kozlov <k2v.akosa@gmail.com>
 */
public class MyTimeProcess extends TimerTask {

    public MyTimeProcess()
    {
        super();
    }
    @Override
    public void run() {

        GameView.tetrisStep();
    }
}