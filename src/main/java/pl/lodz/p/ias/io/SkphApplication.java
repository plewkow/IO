package pl.lodz.p.ias.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"pl.lodz.p.ias.io.zasoby.repository"
})

public class SkphApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkphApplication.class, args);
	}

}
