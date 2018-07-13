package ru.weblokos.mvpcryproportfolio.Model;


import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.vsa.paperknife.CellElement;

import ru.weblokos.mvpcryproportfolio.DBHelper;

public class Currency implements CellElement {

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("price_usd")
    private double price_usd;

    @SerializedName("percent_change_24h")
    private double percent_change_24h;

    public static String CREATE_TABLE_CURRENCY(String table_name) {
        return "CREATE TABLE "+table_name+" ("
                + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "NAME" + " TEXT, "
                + "SYMBOL" + " TEXT, "
                + "PRICE_USD" + " DOUBLE, "
                + "PERCENT_CHANGE_24H" + " DOUBLE "
                + ")";
    }

    public Currency(Cursor cursor) {
        this.name = cursor.getString(cursor.getColumnIndex("NAME"));
        this.symbol = cursor.getString(cursor.getColumnIndex("SYMBOL"));
        this.price_usd = cursor.getDouble(cursor.getColumnIndex("PRICE_USD"));
        this.percent_change_24h = cursor.getDouble(cursor.getColumnIndex("PERCENT_CHANGE_24H"));
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public double getPercent_change_24h() {
        return percent_change_24h;
    }
}
