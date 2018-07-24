package com.easylife.lrc.lyricsview;

import android.content.Intent;
import android.os.Bundle;

import com.easylife.lrc.lyricsview.application.DemoApplication;
import com.easylife.lrc.lyricsview.base.AbstractBaseActivity;
import com.easylife.lrc.lyricsview.di.component.ActivityComponent;
import com.easylife.lrc.lyricsview.di.component.DaggerActivityComponent;
import com.easylife.lrc.lyricsview.di.module.ActivityModule;

public class MainActivity extends AbstractBaseActivity {
    private ActivityComponent activityComponent;

    private ActivityComponent getActivityComponent()
    {
        if(activityComponent == null )
        {
            activityComponent = DaggerActivityComponent.
                    builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(DemoApplication.get(this).getApplicationComponent())
                    .build();

        }
        return activityComponent;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstance, Intent intent) {
        getActivityComponent().inject(this);
    }
}
