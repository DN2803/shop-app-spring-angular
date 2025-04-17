package com.example.mvc_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.mvc_demo.repositories.BaseRepositoryImpl;

@SpringBootApplication
@EnableConfigurationProperties()
@EnableJpaRepositories(
	basePackages = "com.example.mvc_demo",
    repositoryBaseClass = BaseRepositoryImpl.class
)
public class MvcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcDemoApplication.class, args);
	}

}
