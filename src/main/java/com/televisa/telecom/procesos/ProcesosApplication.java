package com.televisa.telecom.procesos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

import com.televisa.telecom.procesos.impl.FilesServiceImpl;


@SpringBootApplication(scanBasePackages = {"com.televisa.telecom.procesos"}) //contains these three:
/*@Configuration
@ComponentScan({"com.televisa.telecom.procesos","com.televisa.telecom.procesos.impl"})
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories("com.televisa.telecom.procesos.repository")*/
public class ProcesosApplication extends SpringBootServletInitializer implements WebApplicationInitializer{
	
	/*@Bean
	public FilesService filesService() {
	    System.out.println("repo from bean");
	    return new FilesServiceImpl();
	}*/
	@Autowired
	FilesServiceImpl servicio;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProcesosApplication.class, args);
		/*String[] beans = ctx.getBeanDefinitionNames();
		Arrays.sort(beans);
		for(String names: beans){
			System.out.println(names);
		}*/
		System.out.println("RUNNING!");
	}
	
}
