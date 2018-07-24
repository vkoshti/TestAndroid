package com.easylife.lrc.lyricsview.di.module;

import android.app.Activity;
import android.content.Context;

import com.easylife.lrc.lyricsview.di.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;
    public ActivityModule(Activity activity)
    {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context getContext()
    {
        return this.activity;
    }

    @Provides
    Activity getActivity()
    {
        return this.activity;
    }

}
