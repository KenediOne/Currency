package currency_course.service;

import currency_course.dbmodels.DBBankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyForOutput {

    private BanksService banksService;
    @Autowired
    public CurrencyForOutput(BanksService banksService) {
        this.banksService = banksService;
    }

    public List<DBBankModel> averageCurrency(){
        List<DBBankModel> list = new ArrayList<>();
        //List<DBBankModel> listMB = banksService.findByDate(1,new Date());
        List<DBBankModel> listPB = banksService.findByDate(3,new Date());
        for(int i=0;i<listPB.size();i++){
            list.add(new DBBankModel(listPB.get(i).getId(),listPB.get(i).getCurrency_name(),
                    listPB.get(i).getSell(),//(listMB.get(i).getSell()+listPB.get(i).getSell())/2,
                    listPB.get(i).getBuy(),//(listMB.get(i).getBuy()+listPB.get(i).getBuy())/2,
                    listPB.get(i).getDay()));
        }
        return list;
    }

//    public List<DBBankModel> averageCurrencyDay(){
//        List<DBBankModel> list = new ArrayList<>();
//        //List<DBBankModel> listMB = banksService.findByDate(1,new Date());
//        List<DBBankModel> listPB = banksService.findByDate(3,new Date());
//        for(int i=0;i<listPB.size();i++){
//            list.add(new DBBankModel(listPB.get(i).getId(),listPB.get(i).getCurrency_name(),
//                    (listMB.get(i).getSell()+listPB.get(i).getSell())/2,
//                    (listMB.get(i).getBuy()+listPB.get(i).getBuy())/2,
//                    listPB.get(i).getDay()));
//        }
//        return list;
//    }
}
