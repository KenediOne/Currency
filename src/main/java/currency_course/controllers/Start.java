package currency_course.controllers;

import currency_course.service.BanksServiceImpl;
import currency_course.service.CurrencyForOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Start {


    @Autowired
    public CurrencyForOutput currencyForOutput;

    @GetMapping()
    public String start(){
        return "/start";
    }
    //this get request page with currencies
    @GetMapping("currency")
    public String currency(Model model){
        model.addAttribute("currency",currencyForOutput.averageCurrency());
        return "/currency";
    }
//    @GetMapping("currency")
//    public String currencyDate(Model model){
//        model.addAttribute("currencyday",currencyForOutput.averageCurrencyDay());
//        return "/currency";
//    }

}
