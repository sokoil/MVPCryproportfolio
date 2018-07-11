package ru.weblokos.mvpcryproportfolio.View;

import java.util.List;

import ru.weblokos.mvpcryproportfolio.Model.Currency;

/**
 * Created by gravitas on 11.07.2018.
 */

public interface CurrencyView {
    void currenciesReady(List<Currency> currencies);
}