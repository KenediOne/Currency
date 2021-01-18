package currency_course.queryBanks;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
public class Decryption {

    private Map<Integer,String> currencyNameOfMonoBank = new HashMap<>();
    Decryption(){
        currencyNameOfMonoBank.put(840,"USD");
        currencyNameOfMonoBank.put(978, "EUR");


    }
    public String nameCurrency(int number){
        return currencyNameOfMonoBank.get(number);
    }
}
