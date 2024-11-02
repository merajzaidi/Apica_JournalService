package com.apica.Jornal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class JornalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JornalApplication.class, args);
	}

}
