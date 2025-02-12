package org.homerunball.pillmein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class PillmeinApplication {
	public static void main(String[] args) {
		SpringApplication.run(PillmeinApplication.class, args);
	}
}
