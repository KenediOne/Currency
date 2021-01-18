package currency_course.models;

import javax.xml.crypto.Data;
import java.util.Date;

public class NationalBankOfUkraineModel {

    private int id;
    private Date pointDate;
    private Date date;
    private double ask;
    private double bid;
    private double trendAsk;
    private double trendBid;
    private String currency;

    public NationalBankOfUkraineModel(int id, Date pointDate, Date date, double ask, double bid, double trendAsk, double trendBid, String currency) {
        this.id = id;
        this.pointDate = pointDate;
        this.date = date;
        this.ask = ask;
        this.bid = bid;
        this.trendAsk = trendAsk;
        this.trendBid = trendBid;
        this.currency = currency;
    }

    public NationalBankOfUkraineModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPointDate() {
        return pointDate;
    }

    public void setPointDate(Date pointDate) {
        this.pointDate = pointDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getTrendAsk() {
        return trendAsk;
    }

    public void setTrendAsk(double trendAsk) {
        this.trendAsk = trendAsk;
    }

    public double getTrendBid() {
        return trendBid;
    }

    public void setTrendBid(double trendBid) {
        this.trendBid = trendBid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
