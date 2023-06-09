package com.springbank.user.cmd.api;

import com.springbank.user.core.configuration.AxonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(AxonConfiguration.class)
@SpringBootApplication
public class UserCommandApplication
{
	public static void main(String[] args) {
		SpringApplication.run(UserCommandApplication.class, args);
	}

}
