package com.qualys.host.hostnamescanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HostnamescannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostnamescannerApplication.class, args);
	}

}
