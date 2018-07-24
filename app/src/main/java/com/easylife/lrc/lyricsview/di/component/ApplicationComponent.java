package com.easylife.lrc.lyricsview.di.component;


import android.app.Application;
import android.content.Context;

import com.easylife.lrc.lyricsview.application.DemoApplication;
import com.easylife.lrc.lyricsview.di.ApplicationContext;
import com.easylife.lrc.lyricsview.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(DemoApplication application);

    @ApplicationContext
    Context getContext();

    Application getApplication();

}
