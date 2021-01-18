package currency_course.queryBanks;

import currency_course.dbmodels.DBBankModel;
import currency_course.models.NationalBankOfUkraineModel;
import currency_course.query.QueryBank;
import currency_course.query.QueryJsonTwo;
import currency_course.service.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QueryNationalBank implements QueryBank {

    private ArrayList<NationalBankOfUkraineModel> models;
    private List<DBBankModel> list = new ArrayList<>();
    private BanksService banksService;
    private LocalDateTime ldt ;
    private DateTimeFormatter formmat1 ;
    private String formatter ;
    @Autowired
    public QueryNationalBank(BanksService banksService){
        System.out.println("back");
        this.banksService = banksService;
        ldt = LocalDateTime.now().plusDays(1);
        formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK);
        formatter = formmat1.format(ldt);
    }

    @Override
    public void query() {
        QueryJsonTwo.getInstance("https://api.minfin.com.ua/mb/")
                .getJSONApi()
                .getNationalBankOfUkraineJsonCurrency(formatter)
                .enqueue(new Callback<List<NationalBankOfUkraineModel>>() {
                    @Override
                    public void onResponse(Call<List<NationalBankOfUkraineModel>> call, Response<List<NationalBankOfUkraineModel>> response) {
                        if(!response.isSuccessful()){
                            System.out.println("here beach, only beach");
                            return;}
                        List<NationalBankOfUkraineModel> magazine =  response.body();
                        models = (ArrayList<NationalBankOfUkraineModel>) magazine;
                        sorting();
                    }

                    @Override
                    public void onFailure(Call<List<NationalBankOfUkraineModel>> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
    }
    private void sorting(){
        System.out.println("This is beach:"+models.size());
        list = new ArrayList<>();
        list.add(new DBBankModel( 1, models.get(0).getCurrency()
                , models.get(0).getAsk(), models.get(0).getBid(),models.get(0).getPointDate() ));
        list.add(new DBBankModel( 2, models.get(1).getCurrency()
                , models.get(1).getAsk(), models.get(1).getBid(),models.get(1).getPointDate() ));
        dataBase();
    }
    @Override
    public void dataBase(){
        if((banksService.findAll(2).size()>0)&&(banksService.findByDate(2,new Date()).size()>0)){
            System.out.println("Here"+banksService.findAll(2).size());
            List<DBBankModel> list1 = banksService.findByDate(2,new Date());
            list.get(0).setId(list1.get(0).getId());
            list.get(1).setId(list1.get(1).getId());
            for (DBBankModel dbBankModel : list) {
                banksService.updateDBBankModel(2, dbBankModel);
            }
        }else{
            for(DBBankModel dbBankModel:list){
                banksService.addDBBankModel( 2,dbBankModel);
            }
        }
    }
}
