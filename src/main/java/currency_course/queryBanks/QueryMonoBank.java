package currency_course.queryBanks;

import currency_course.dbmodels.DBBankModel;
import currency_course.models.MonoBankModel;
import currency_course.query.QueryBank;
import currency_course.query.QueryJson;
import currency_course.service.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
public class QueryMonoBank implements QueryBank {


    private ArrayList<MonoBankModel> models;
    private static List<DBBankModel> list;

    private BanksService banksService;

    @Autowired
    public QueryMonoBank(BanksService banksService){
        this.banksService = banksService;
    }

    @Override
    public void query() {
        QueryJson.getInstance("https://api.monobank.ua/")
                .getJSONApi()
                .getMonoBankJsonCurrency()
                .enqueue(new Callback<List<MonoBankModel>>() {
                    @Override
                    public void onResponse(Call<List<MonoBankModel>> call, Response<List<MonoBankModel>> response) {
                        if(!response.isSuccessful()){return;}
                        List<MonoBankModel> magazine =  response.body();
                        models = (ArrayList<MonoBankModel>) magazine;
                        sorting();
                    }

                    @Override
                    public void onFailure(Call<List<MonoBankModel>> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
    }
    private void sorting(){
        Decryption decryption = new Decryption();
        list = new ArrayList<>();
        list.add(new DBBankModel( 1, decryption.nameCurrency(models.get(0).getCurrencyCodeA())
                , models.get(0).getRateSell(), models.get(0).getRateBuy(),new Date() ));
        list.add(new DBBankModel( 2, decryption.nameCurrency(models.get(1).getCurrencyCodeA())
                , models.get(1).getRateSell(), models.get(1).getRateBuy(), new Date()));
        dataBase();
    }
    @Override
    public void dataBase(){
        System.out.println("Quantity number ux: "+banksService.findByDate(1,new Date()));
        if((banksService.findAll(1).size()>0)&&(banksService.findByDate(1,new Date()).size()>0)){
            System.out.println("Here"+banksService.findAll(1).size());
                for (DBBankModel dbBankModel : list) {
                    banksService.updateDBBankModel(1, dbBankModel);
                    List<DBBankModel> list1 = banksService.findByDate(1, new Date());
                    list.get(0).setId(list1.get(0).getId());
                    list.get(1).setId(list1.get(1).getId());
                }
        }else{
            System.out.println("or here"+list.get(0).getDay());
            for(DBBankModel dbBankModel:list){
                banksService.addDBBankModel( 1,dbBankModel);
            }
        }
    }

}
