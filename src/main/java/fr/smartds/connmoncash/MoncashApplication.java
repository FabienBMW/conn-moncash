package fr.smartds.connmoncash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "fr.smartds.connmoncash.feignclients")
public class MoncashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoncashApplication.class, args);
	}

}
