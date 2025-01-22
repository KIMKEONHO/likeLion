package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import sample.bean.Book;
import sample.bean.MyBean;

public class MyBeanConfig {


    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    @Scope("prototype")
    public MyBean myBeanPrototype() {
        return new MyBean();
    }
}
