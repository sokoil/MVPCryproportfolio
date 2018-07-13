package ru.weblokos.mvpcryproportfolio.Presenter;

import com.vsa.paperknife.CellDataProvider;
import com.vsa.paperknife.DataSource;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.weblokos.mvpcryproportfolio.DBHelper;
import ru.weblokos.mvpcryproportfolio.Model.Currency;
import ru.weblokos.mvpcryproportfolio.Service.CurrencyService;
import ru.weblokos.mvpcryproportfolio.View.CurrencyView;

/**
 * Created by gravitas on 11.07.2018.
 */

public class CurrencyPresenter implements CellDataProvider {

    private CurrencyService currencyService;
    private CurrencyView currencyView;
    private DBHelper dbhelper;

    public CurrencyPresenter(CurrencyView view, DBHelper dbhelper) {
        this.currencyView = view;
        this.dbhelper = dbhelper;

        if (this.currencyService == null) {
            this.currencyService = new CurrencyService();
        }
    }

    public void getCurrencies() {
        currencyService
                .getAPI()
                .getResults()
                .enqueue(new Callback<List<Currency>>() {
                    @Override
                    public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                        if(response.body() != null)
                            for(Currency currency : response.body())
                                dbhelper.addOrUpdateCurrency(currency);
                        currencyView.currenciesReady();
                    }

                    @Override
                    public void onFailure(Call<List<Currency>> call, Throwable t) {
                        try {
                            throw new InterruptedException("Something went wrong!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @DataSource("Name")
    public String getName(Currency item) {
        return item.getSymbol();
    }

    @DataSource("Cost")
    public String getCost(Currency item) {
        return String.format("%.2f$", item.getPrice_usd());
    }

    @DataSource("Percent")
    public String getPercent(Currency item) {
        return String.format("%.2f%%", item.getPercent_change_24h());
    }

    @DataSource("PercentColor")
    public int getPercentColor(Currency item) {
        return item.getPercent_change_24h() > 0 ? 0xff3cca23 : 0xffdb864c;
    }
}
