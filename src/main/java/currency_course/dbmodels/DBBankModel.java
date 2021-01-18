package currency_course.dbmodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DBBankModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String currency_name;
    double sell;
    double buy;
    Date day;

    public DBBankModel(int id, String currency_name, double sell, double buy, Date day) {
        this.id = id;
        this.currency_name = currency_name;
        this.sell = sell;
        this.buy = buy;
        this.day = day;
    }

    public DBBankModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
