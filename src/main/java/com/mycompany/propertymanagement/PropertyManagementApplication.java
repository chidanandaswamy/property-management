package com.mycompany.propertymanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "property details", version = "2.0", description = "Property-Details"))
public class PropertyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyManagementApplication.class, args);
	System.out.println("Om Guruve Namha");
	}

}
