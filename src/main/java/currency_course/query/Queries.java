package currency_course.query;

import currency_course.models.MonoBankModel;
import currency_course.models.NationalBankOfUkraineModel;
import currency_course.models.PrivatBankModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public interface Queries {
     LocalDateTime ldt = LocalDateTime.now().plusDays(1);
     DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK);
     String formatter = formmat1.format(ldt);
     String get = "1a4c5de11381e188c6a6020f7f1c0e133df8ce03/"+formatter;

    @GET("bank/currency")
    public Call<List<MonoBankModel>> getMonoBankJsonCurrency();

    @GET("pubinfo?json&exchange&coursid=5")
    public Call<List<PrivatBankModel>> getPrivatBankJsonCurrency();

    @GET("1a4c5de11381e188c6a6020f7f1c0e133df8ce03/")
    public Call<List<NationalBankOfUkraineModel>> getNationalBankOfUkraineJsonCurrency(@Query("") String date);

}
