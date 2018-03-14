package com.mytaxi.android_demo;

import android.support.test.espresso.IdlingResource;

import com.mytaxi.android_demo.activities.MainActivity;

/**
 * Created by praveenrn on 3/13/2018.
 */

public class IdleResource implements IdlingResource {
    private MainActivity activity;
    private ResourceCallback callback;

    public IdleResource(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "MainActivityIdleName";
    }

    @Override
    public boolean isIdleNow() {
        Boolean idle = isIdle();
        if (idle) callback.onTransitionToIdle();
        return idle;
    }

    public boolean isIdle() {
        return activity != null && callback != null && activity.isFinishing();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}
