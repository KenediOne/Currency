package currency_course.cron;

import currency_course.queryBanks.QueryMonoBank;
import currency_course.queryBanks.QueryNationalBank;
import currency_course.queryBanks.QueryPrivatBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class CronForBanks {

    @Autowired
    public QueryMonoBank queryMonoBank;

    @Autowired
    public QueryNationalBank queryNationalBank;

    @Autowired
    public QueryPrivatBank queryPrivatBank;



    @Scheduled(cron = " 0 0/5 * * * *")
    public void cronForBanks(){

        //queryMonoBank.query();
        //queryNationalBank.query();
        queryPrivatBank.query();

    }



}
