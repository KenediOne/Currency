package currency_course.service;

import currency_course.dbmodels.DBBankModel;

import java.util.Date;
import java.util.List;

public interface BanksService {

    public List<DBBankModel> findAll(int idBank);
    public List<DBBankModel> findByDate(int idBank, Date day);
    public DBBankModel findById(int idBank,int id);
    public void addDBBankModel(int idBank,DBBankModel dbBankModel);
    public void deleteDBBankModel(int idBank,int id);
    public void updateDBBankModel(int idBank,DBBankModel dbBankModel);
}
