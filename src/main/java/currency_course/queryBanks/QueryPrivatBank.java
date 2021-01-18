package currency_course.queryBanks;

import currency_course.dbmodels.DBBankModel;
import currency_course.models.PrivatBankModel;
import currency_course.query.QueryBank;
import currency_course.query.QueryJson;
import currency_course.query.QueryJsonThree;
import currency_course.service.BanksService;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class QueryPrivatBank implements QueryBank {

    private ArrayList<PrivatBankModel> models;
    private List<DBBankModel> list = new ArrayList<>();
    private BanksService banksService;
    @Autowired
    public QueryPrivatBank(BanksService banksService) {
        this.banksService = banksService;
    }


    @Override
    public void query() {
        QueryJsonThree.getInstance("https://api.privatbank.ua/p24api/")
                .getJSONApi()
                .getPrivatBankJsonCurrency()
                .enqueue(new Callback<List<PrivatBankModel>>() {
                    @Override
                    public void onResponse(Call<List<PrivatBankModel>> call, Response<List<PrivatBankModel>> response) {
                        if(!response.isSuccessful()){return;}
                        List<PrivatBankModel> magazine =  response.body();
                        models = (ArrayList<PrivatBankModel>) magazine;
                        sorting();
                    }

                    @Override
                    public void onFailure(Call<List<PrivatBankModel>> call, Throwable t) {

                    }
                });
    }
    private void sorting(){
        System.out.println("This is beach:"+models.size());
        list = new ArrayList<>();
        list.add(new DBBankModel( 1, models.get(0).getCcy()
                , models.get(0).getSale(), models.get(0).getBuy(),new Date() ));
        list.add(new DBBankModel( 2, models.get(1).getCcy()
                , models.get(1).getSale(), models.get(1).getBuy(),new Date() ));
        dataBase();
    }
    @Override
    public void dataBase(){
        if((banksService.findAll(3).size()>0)&&(banksService.findByDate(3,new Date()).size()>0)){
            System.out.println("Here"+banksService.findAll(3).size());
                List<DBBankModel> list1 = banksService.findByDate(3,new Date());
                list.get(0).setId(list1.get(0).getId());
                list.get(1).setId(list1.get(1).getId());
                for (DBBankModel dbBankModel : list) {
                    banksService.updateDBBankModel(3, dbBankModel);
            }
        }else{
            for(DBBankModel dbBankModel:list){
                banksService.addDBBankModel( 3,dbBankModel);
            }
        }
    }
}
