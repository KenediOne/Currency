package currency_course.configuration;

import currency_course.dao.BanksDAO;
import currency_course.dao.BanksDAOImpl;
import currency_course.queryBanks.QueryMonoBank;
import currency_course.queryBanks.QueryNationalBank;
import currency_course.queryBanks.QueryPrivatBank;
import currency_course.service.BanksService;
import currency_course.service.BanksServiceImpl;
import currency_course.service.CurrencyForOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("currency_course")
@EnableWebMvc
@EnableScheduling
public class SpringConfiguration implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    //this is a constructor and we get a class ApplicationContext
    @Autowired
    public SpringConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    //this class allows you to need to connect to the database
    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    //this class configures settings for connecting to the database
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5434/currency_course");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("1234");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }

    //this class with database queries
    @Bean
    public BanksDAO getBanksDAO(){
        return new BanksDAOImpl(getJdbcTemplate());
    }
    //this class is a service that refers to the class with queries to the database
    @Bean
    public BanksService getBanksService(){
        return new BanksServiceImpl(getBanksDAO());
    }
    //this class of receiving, processing and writing json file to the database
    @Bean
    public QueryPrivatBank getQueryPrivatBank(){
        return new QueryPrivatBank(getBanksService());
    }
    //this class of receiving, processing and writing json file to the database
    @Bean
    public QueryMonoBank getQueryMonoBank(){
        return new QueryMonoBank(getBanksService());
    }
    //this class of receiving, processing and writing json file to the database
    @Bean
    public QueryNationalBank getQueryNationalBank(){
        return new QueryNationalBank(getBanksService());
    }
    //this class of processing information for output
    @Bean
    public CurrencyForOutput getCurrencyForOutput(){
        return new CurrencyForOutput(getBanksService());
    }
    //this resource handling class
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }
    //this resource handling class
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
    //this resource handling class
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
