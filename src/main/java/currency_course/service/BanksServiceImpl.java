package currency_course.service;

import currency_course.dao.BanksDAO;
import currency_course.dbmodels.DBBankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BanksServiceImpl implements BanksService{


    public BanksDAO banksDAO;

    @Autowired
    public BanksServiceImpl(BanksDAO banksDAO) {
        this.banksDAO = banksDAO;
    }

    @Override
    public List<DBBankModel> findAll(int idBank) {
        System.out.println("Fack two");
        return banksDAO.findAll(idBank);
    }

    @Override
    public List<DBBankModel> findByDate(int idBank, Date day) {
        return banksDAO.findByDate(idBank,day);
    }

    @Override
    public DBBankModel findById(int idBank, int id) {
        return banksDAO.findById(idBank,id);
    }

    @Override
    public void addDBBankModel(int idBank, DBBankModel dbBankModel) {
        banksDAO.addDBBankModel(idBank,dbBankModel);
    }

    @Override
    public void deleteDBBankModel(int idBank, int id) {
        banksDAO.deleteDBBankModel(idBank,id);
    }

    @Override
    public void updateDBBankModel(int idBank, DBBankModel dbBankModel) {
        banksDAO.updateDBBankModel(idBank,dbBankModel);
    }
}
