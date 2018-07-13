package ru.weblokos.mvpcryproportfolio;

/**
 * Created by gravitas on 24.02.2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.weblokos.mvpcryproportfolio.Model.Currency;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper mInstance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mvpcryptoportfolio.db";
    private static final String TABLE_CURRENCY = "CURRENCY";

    public static synchronized DBHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBHelper(context);
        }
        return mInstance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Currency.CREATE_TABLE_CURRENCY(TABLE_CURRENCY));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void addOrUpdateCurrency(Currency currency) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("PRICE_USD", currency.getPrice_usd());
            values.put("PERCENT_CHANGE_24H", currency.getPercent_change_24h());
            int rows = db.update(TABLE_CURRENCY, values, "SYMBOL = '"+currency.getSymbol()+"'", null);
            if (rows != 1) {
                values.put("SYMBOL", currency.getSymbol());
                values.put("NAME", currency.getName());
                db.insertOrThrow(TABLE_CURRENCY, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.w("DBHELPER", "Error while trying to add or update Currency");
        } finally {
            db.endTransaction();
        }
    }

    public List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        String CURRENCY_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_CURRENCY);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CURRENCY_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    currencies.add(new Currency(cursor));
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.w("DBHELPER", "Error while trying to get Currencies from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return currencies;
    }
}