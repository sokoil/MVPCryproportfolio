package ru.weblokos.mvpcryproportfolio.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.vsa.paperknife.CellListenerProvider;

import java.util.List;

import javax.inject.Inject;

import ru.weblokos.mvpcryproportfolio.Component.CurrencyActivityComponent;
import ru.weblokos.mvpcryproportfolio.Component.DaggerCurrencyActivityComponent;
import ru.weblokos.mvpcryproportfolio.DBHelper;
import ru.weblokos.mvpcryproportfolio.Model.Currency;
import ru.weblokos.mvpcryproportfolio.Module.AppModule;
import ru.weblokos.mvpcryproportfolio.Module.CurrencyModule;
import ru.weblokos.mvpcryproportfolio.Module.DBModule;
import ru.weblokos.mvpcryproportfolio.Presenter.CurrencyPresenter;
import ru.weblokos.mvpcryproportfolio.R;

public class CurrencyActivity extends AppCompatActivity implements CurrencyView, CellListenerProvider {

    private ListView mListView;

    private CurrencyActivityComponent component;

    @Inject
    DBHelper dbHelper;
    @Inject
    CurrencyPresenter currencyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        mListView = findViewById(R.id.listView);

        createComponent();

        currenciesReady();
        currencyPresenter.getCurrencies();
    }

    private void createComponent() {
        component = DaggerCurrencyActivityComponent.builder()
                .appModule(new AppModule(this))
                .dBModule(new DBModule())
                .currencyModule(new CurrencyModule(this))
                .build();
        component.injectCurrencyActivity(this);
    }

    @Override
    public void currenciesReady() {
        CurrencyAdapter adapter = new CurrencyAdapter(this, dbHelper.getCurrencies(), currencyPresenter, this);
        mListView.setAdapter(adapter);
    }
}
