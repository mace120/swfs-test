package com.mace.swfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mace.swfs.filters.IpAddressFilter;

@SpringBootApplication
@EnableJpaAuditing
public class SwfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwfsApplication.class, args);
	}
	
	@Bean
	public IpAddressFilter getIpAddressFilter() {
		return new IpAddressFilter();
	}
}
