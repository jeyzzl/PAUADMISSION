package adm;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ConfigurationPropertiesScan("adm.ConConfiguration") 
@ComponentScan({"adm"})
public class AdmisionApplication{
	public static void main(String[] args) {
		SpringApplication.run(AdmisionApplication.class, args);
	}
}
