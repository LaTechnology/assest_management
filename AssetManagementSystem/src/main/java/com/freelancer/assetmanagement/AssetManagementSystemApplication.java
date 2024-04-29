package com.freelancer.assetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableSwagger2
@EnableJpaAuditing(auditorAwareRef="auditorAwareImpl")
public class AssetManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementSystemApplication.class, args);
	}
	
//	@Bean
//	public AuditorAware<String> auditorProvider(){
//		return new AuditorAwareImpl();
//	}

}
