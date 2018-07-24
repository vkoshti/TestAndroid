package com.easylife.lrc.lyricsview.di.component;


import com.easylife.lrc.lyricsview.MainActivity;
import com.easylife.lrc.lyricsview.di.PerActivity;
import com.easylife.lrc.lyricsview.di.module.ActivityModule;
import com.easylife.lrc.lyricsview.di.module.ApplicationModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class , modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
