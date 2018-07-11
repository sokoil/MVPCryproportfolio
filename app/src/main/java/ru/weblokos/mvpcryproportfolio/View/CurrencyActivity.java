package ru.weblokos.mvpcryproportfolio.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.vsa.paperknife.CellListenerProvider;

import java.util.List;

import ru.weblokos.mvpcryproportfolio.Model.Currency;
import ru.weblokos.mvpcryproportfolio.Presenter.CurrencyPresenter;
import ru.weblokos.mvpcryproportfolio.R;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView, CellListenerProvider {

    private ListView mListView;
    private CurrencyPresenter currencyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        mListView = (ListView) findViewById(R.id.listView);
        currencyPresenter = new CurrencyPresenter(this);

        // Maybe it's best to call it on onResume()
        currencyPresenter.getCurrencies();
    }

    @Override
    public void currenciesReady(List<Currency> currencies) {
        for (Currency currency : currencies) {
            Log.w("RETROFIT", currency.getSymbol() + " = " + currency.getPrice_usd()+"$");
        }
        CurrencyAdapter adapter = new CurrencyAdapter(this, currencies, currencyPresenter, this);
        mListView.setAdapter(adapter);
    }
}
