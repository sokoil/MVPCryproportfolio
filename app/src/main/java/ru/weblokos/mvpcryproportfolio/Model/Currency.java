package ru.weblokos.mvpcryproportfolio.Model;


import com.google.gson.annotations.SerializedName;
import com.vsa.paperknife.CellElement;

public class Currency implements CellElement {

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("price_usd")
    private double price_usd;

    @SerializedName("percent_change_24h")
    private double percent_change_24h;

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
