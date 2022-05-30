package multi.dokgi.bookhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"multi.dokgi.bookhub.*.dao"})
@ComponentScan(basePackages = { "multi.dokgi.bookhub" })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BookHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookHubApplication.class, args);
	}

}
