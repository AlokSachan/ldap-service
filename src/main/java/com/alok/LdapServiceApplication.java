package com.alok;

import com.alok.filters.MyCustomHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class LdapServiceApplication {

	@Autowired
	private final ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(LdapServiceApplication.class, args);
	}

//    @Bean
//	public FilterRegistrationBean<MyCustomHeaderFilter> myCustomHeaderFilterFilterRegistrationBean(MyCustomHeaderFilter myCustomHeaderFilter){
//		FilterRegistrationBean<MyCustomHeaderFilter> myCustomHeaderFilterFilterRegistrationBean = new FilterRegistrationBean<>(myCustomHeaderFilter);
//		if(applicationContext.containsBean("headersLoggingFilter")) {
//			myCustomHeaderFilterFilterRegistrationBean.setOrder(-1);
//		}else {
//			myCustomHeaderFilterFilterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//		}
//		return myCustomHeaderFilterFilterRegistrationBean;
//	}

}
