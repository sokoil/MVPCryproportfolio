package ru.weblokos.mvpcryproportfolio.Component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.weblokos.mvpcryproportfolio.DBHelper;
import ru.weblokos.mvpcryproportfolio.Module.AppModule;
import ru.weblokos.mvpcryproportfolio.Module.CurrencyModule;
import ru.weblokos.mvpcryproportfolio.Module.DBModule;
import ru.weblokos.mvpcryproportfolio.Presenter.CurrencyPresenter;
import ru.weblokos.mvpcryproportfolio.View.CurrencyActivity;

/**
 * Created by gravitas on 13.07.2018.
 */

@Component(modules={AppModule.class, DBModule.class, CurrencyModule.class})
@Singleton
public interface CurrencyActivityComponent {
    Context getContext();
    DBHelper getDBHelper();
    CurrencyPresenter getCurrencyPresenter();

    void injectCurrencyActivity(CurrencyActivity currencyActivity);
}