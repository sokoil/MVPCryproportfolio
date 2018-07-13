package ru.weblokos.mvpcryproportfolio.Module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gravitas on 13.07.2018.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(@NonNull Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

}