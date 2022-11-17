package com.network.ipmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.network.ipmanagement.repository")
public class IpManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpManagementApplication.class, args);
	}

}
