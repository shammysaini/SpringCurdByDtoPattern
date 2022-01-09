package boot;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import boot.controller.HomeController;
import boot.service.impl.UserServiceImpl;

@SpringBootApplication
public class SpringCompleteApplication {
 
	public static void main(String[] args) {
		
		//new File(HomeController.uploadPath).mkdir();
		SpringApplication.run(SpringCompleteApplication.class, args);
	}
	
	

}
