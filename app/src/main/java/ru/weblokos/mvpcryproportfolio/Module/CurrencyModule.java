package ru.weblokos.mvpcryproportfolio.Module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.weblokos.mvpcryproportfolio.DBHelper;
import ru.weblokos.mvpcryproportfolio.Presenter.CurrencyPresenter;
import ru.weblokos.mvpcryproportfolio.View.CurrencyActivity;
import ru.weblokos.mvpcryproportfolio.View.CurrencyView;

/**
 * Created by gravitas on 13.07.2018.
 */

@Module
public class CurrencyModule {

    CurrencyView contractor;

    public CurrencyModule(CurrencyView contractor) {
        this.contractor = contractor;
    }

    @Provides
    CurrencyPresenter provideCurrencyMobule(DBHelper dbHelper) {
        return new CurrencyPresenter(contractor, dbHelper);
    }
}