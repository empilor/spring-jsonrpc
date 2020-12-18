package com.vzelenin.edu.spring;

import com.vzelenin.edu.spring.jsonrpc.exceptions.ServiceThrowsExceptions;
import com.vzelenin.edu.spring.jsonrpc.client.ClientService;
import com.vzelenin.edu.spring.jsonrpc.service.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringJsonrpcApplication implements CommandLineRunner {
	public static final Logger LOGGER = LoggerFactory.getLogger(SpringJsonrpcApplication.class);
	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringJsonrpcApplication.class, args);
	}


	public void run(String... args) {
		ClientService clientService = context.getBean(ClientService.class);
		try {
			LOGGER.info("Selecting user count");
			int userCount = clientService.getUserCount();
			LOGGER.info("User count: {}", userCount);

			LOGGER.info("Looking for user Jade");
			User jade = clientService.findUserByUserName("jade");
			LOGGER.info("User Jade: {}", jade);

			LOGGER.info("Check how JsonRPC client gets result with RuntimeException - Throwing own RuntimeException");
			clientService.getOwnException();
		} catch(Exception ex) {
			LOGGER.error("Caught Exception during jsonrpc call: {}", ex.getMessage(), ex);
		}

		try {
			LOGGER.info("Check how JsonRPC client gets result with RuntimeException - Throwing RuntimeException");
			clientService.getRuntimeException();
		} catch(Exception ex) {
			LOGGER.error("Caught Exception during jsonrpc call: {}", ex.getMessage(), ex);
		}
	}
}
