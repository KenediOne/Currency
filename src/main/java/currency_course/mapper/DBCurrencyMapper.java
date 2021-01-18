package currency_course.mapper;

import currency_course.dbmodels.DBBankModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCurrencyMapper implements RowMapper<DBBankModel> {

    public DBBankModel mapRow(ResultSet resultSet,int i) throws SQLException{
        DBBankModel dbBankModel = new DBBankModel();
        dbBankModel.setId(resultSet.getInt("id"));
        dbBankModel.setCurrency_name(resultSet.getString("currency_name"));
        dbBankModel.setSell(resultSet.getDouble("sell"));
        dbBankModel.setBuy(resultSet.getDouble("buy"));
        dbBankModel.setDay(resultSet.getDate("day"));
        return dbBankModel;
    }

}
