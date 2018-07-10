package com.codificador.stopwatch;

import android.os.Handler;
import android.os.SystemClock;

public class StopWatchUtil {

    long lMillisecondTime, lStartTime, lTimeBuff, lUpdateTime = 0L ;
    Handler handler;
    int iSeconds, iMinutes, iMilliSeconds, iHour;
    TimerValue timerValue;

    public StopWatchUtil(TimerValue timerValue){
        this.timerValue = timerValue;
        handler = new Handler();
    }

    public Runnable runnable = new Runnable() {
        public void run() {
            lMillisecondTime = SystemClock.uptimeMillis() - lStartTime;
            lUpdateTime = lTimeBuff + lMillisecondTime;
            iSeconds = (int) (lUpdateTime / 1000);

            iMinutes = iSeconds / 60;
            iHour = iMinutes / 60;
            iMinutes = iMinutes % 60;
            iSeconds = iSeconds % 60;
            iMilliSeconds = (int) (lUpdateTime % 1000);
            String time = String.format("%02d", iHour)+":" + String.format("%02d", iMinutes) + ":"
                    + String.format("%02d", iSeconds) + ":"
                    + String.format("%03d", iMilliSeconds) ;
            timerValue.setValue(time);
            handler.postDelayed(this, 0);
        }
    };

    public void start(){
        lStartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    public void pause(){
        lTimeBuff += lMillisecondTime;
        handler.removeCallbacks(runnable);
    }

    public void reset(){
        lMillisecondTime = 0L ;
        lStartTime = 0L ;
        lTimeBuff = 0L ;
        lUpdateTime = 0L ;
        iSeconds = 0 ;
        iMinutes = 0 ;
        iMilliSeconds = 0 ;
        timerValue.setValue("00:00:00:00");
    }
}