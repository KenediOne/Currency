package currency_course.dao;

import currency_course.dbmodels.DBBankModel;
import currency_course.mapper.DBCurrencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class BanksDAOImpl implements BanksDAO{

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public BanksDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DBBankModel> findAll(int idBank) {
        System.out.println("Fack");
        String sql = null;
        switch (idBank){
            case 1:
                sql = "SELECT * FROM currency.currency_MB";
                break;
            case 2:
                sql = "SELECT * FROM currency.currency_NBU";
                break;
            case 3:
                sql = "SELECT * FROM currency.currency_PB";
                break;
        }
        return jdbcTemplate.query(sql,new DBCurrencyMapper());
    }
    @Override
    public List<DBBankModel> findByDate(int idBank, Date day) {
        String sql = null;
        switch (idBank){
            case 1:
                sql = "SELECT * FROM currency.currency_mb WHERE day = ?";
                break;
            case 2:
                sql = "SELECT * FROM currency.currency_nbu WHERE day = ?";
                break;
            case 3:
                sql = "SELECT * FROM currency.currency_pb WHERE day = ?";
                break;
        }
        List<DBBankModel> list = jdbcTemplate.query(sql,new DBCurrencyMapper(),day);
        return list;
    }
    @Override
    public DBBankModel findById(int idBank, int id) {
        String sql = null;
        switch (idBank){
            case 1:
                sql = "SELECT * FROM currency.currency_mb WHERE id = ?";
                break;
            case 2:
                sql = "SELECT * FROM currency.currency_nbu WHERE id = ?";
                break;
            case 3:
                sql = "SELECT * FROM currency.currency_pb WHERE id = ?";
                break;
        }
        List<DBBankModel> list = jdbcTemplate.query(sql,new DBCurrencyMapper(),id);
        return list.get(0);
    }

    @Override
    public void addDBBankModel(int idBank, DBBankModel dbBankModel) {
        System.out.println("beach"+dbBankModel.getDay());
        String sql = null;
        switch (idBank){
            case 1:
                sql = "INSERT INTO currency.currency_mb (currency_name, sell, buy,day) VALUES (?,?,?,?)";
                break;
            case 2:
                sql = "INSERT INTO currency.currency_nbu (currency_name, sell, buy,day) VALUES (?,?,?,?)";
                break;
            case 3:
                sql = "INSERT INTO currency.currency_pb (currency_name, sell, buy,day) VALUES (?,?,?,?)";
                break;
        }
        jdbcTemplate.update(sql,dbBankModel.getCurrency_name(),dbBankModel.getSell(),dbBankModel.getBuy(),dbBankModel.getDay());
    }

    @Override
    public void deleteDBBankModel(int idBank, int id) {
        String sql = null;
        switch (idBank){
            case 1:
                sql = "DELETE FROM currency.currency_mb WHERE id = ?";
                break;
            case 2:
                sql = "DELETE FROM currency.currency_nbu WHERE id = ?";
                break;
            case 3:
                sql = "DELETE FROM currency.currency_pb WHERE id = ?";
                break;
        }
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateDBBankModel(int idBank, DBBankModel dbBankModel) {
        String sql = null;
        switch (idBank){
            case 1:
                sql = "UPDATE currency.currency_mb SET currency_name=?, sell=?, buy=?, day=? WHERE id=?";
                break;
            case 2:
                sql = "UPDATE currency.currency_nbu SET currency_name=?, sell=?, buy=?, day=? WHERE id=?";
                break;
            case 3:
                sql = "UPDATE currency.currency_pb SET currency_name=?, sell=?, buy=?, day=? WHERE id=?";
                break;
        }
        jdbcTemplate.update(sql,dbBankModel.getCurrency_name(),dbBankModel.getSell(),dbBankModel.getBuy(),dbBankModel.getDay(),dbBankModel.getId());
    }
}
