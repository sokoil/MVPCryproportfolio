package ru.weblokos.mvpcryproportfolio.Module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import ru.weblokos.mvpcryproportfolio.DBHelper;
import ru.weblokos.mvpcryproportfolio.View.CurrencyActivity;

/**
 * Created by gravitas on 13.07.2018.
 */

@Module
public class DBModule {

    @Provides
    DBHelper provideDBMobule(Context context) {
        return DBHelper.getInstance(context);
    }

}