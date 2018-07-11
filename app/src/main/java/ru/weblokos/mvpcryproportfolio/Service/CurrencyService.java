package ru.weblokos.mvpcryproportfolio.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gravitas on 11.07.2018.
 */

public class CurrencyService {
    private Retrofit retrofit = null;

    public CurrencyAPI getAPI() {
        String BASE_URL = "https://api.coinmarketcap.com/v1/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(CurrencyAPI.class);
    }
}
