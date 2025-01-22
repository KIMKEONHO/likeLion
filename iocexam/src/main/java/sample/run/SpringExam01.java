package sample.run;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sample.bean.MyBean;
import sample.config.MyBeanConfig;

public class SpringExam01 {
    public static void main(String[] args) {
        MyBean bean = new MyBean();
        bean.setName("kim");

        System.out.println(bean.getName());

        //spring ioc container 을 이용해서 사용

        //스프링 제공 공장을 통해서 사용
        //1. BeanFactory -- 단순한 일만 한다. aop 같은 기술은 사용할 숭 ㅓㅄ다.
        //BeanFactory beanFactory = new BeanFactory();

        // 빈 생성을 관리할 거기 떄문에 어떤 빈을 관리할지 알고 있어야함
        System.out.println("ApplicationContext 생성전");
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
        System.out.println("ApplicationContext 생성후");

        // 사용하고자 할때
        MyBean bean1 = (MyBean) context.getBean("myBean");

        MyBean bean2 = (MyBean) context.getBean("myBeanPrototype");

        if(bean1==bean2){
            System.out.println("bean1==bean2");
        }else {
            System.out.println("bean1 != bean2");
        }
    }
}
