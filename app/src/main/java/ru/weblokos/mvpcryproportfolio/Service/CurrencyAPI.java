package ru.weblokos.mvpcryproportfolio.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.weblokos.mvpcryproportfolio.Model.Currency;

/**
 * Created by gravitas on 11.07.2018.
 */

public interface CurrencyAPI {
    @GET("ticker")
    Call<List<Currency>> getResults();
}
