package com.easylife.lrc.lyricsview.di.module;

import android.app.Application;
import android.content.Context;

import com.easylife.lrc.lyricsview.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application app)
    {
        this.application = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext()
    {
        return this.application;
    }

    @Provides
    Application provideApplication()
    {
        return this.application;
    }

}
