package com.easylife.lrc.lyricsview.application;

import android.app.Application;
import android.content.Context;

import com.easylife.lrc.lyricsview.di.component.ApplicationComponent;
import com.easylife.lrc.lyricsview.di.component.DaggerApplicationComponent;
import com.easylife.lrc.lyricsview.di.module.ApplicationModule;

public class DemoApplication extends Application {
    private ApplicationComponent applicationComponent;

    public static DemoApplication get(Context context)
    {
        return (DemoApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
