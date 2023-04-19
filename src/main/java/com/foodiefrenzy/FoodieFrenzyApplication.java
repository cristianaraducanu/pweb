package com.foodiefrenzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.foodiefrenzy.repository")
@EntityScan(basePackages = "com.foodiefrenzy.entity")
public class FoodieFrenzyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieFrenzyApplication.class, args);
	}

}
