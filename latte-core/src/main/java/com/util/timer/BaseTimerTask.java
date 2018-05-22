package com.util.timer;

import java.util.TimerTask;

/**
 * 项目名 FestEC2 on 2018/5/22.
 * 包名   com.util.timer
 * 创建者   82354
 * 创建时间   2018/5/22 16:38
 * 描述  TODO
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mTimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mTimerListener = iTimerListener;
    }

    @Override
    public void run() {
      if (mTimerListener != null){
          mTimerListener.onTimer();
      }
    }
}
