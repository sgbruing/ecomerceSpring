package com.letscode.store;

import com.letscode.store.model.Authority;
import com.letscode.store.model.AuthorityKey;
import com.letscode.store.model.User;
import com.letscode.store.repository.AuthorityRepository;
import com.letscode.store.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);

	}

	@Bean
	public CommandLineRunner test(UserRepository userRepository,
								  AuthorityRepository authorityRepository) {
		return (args) -> {


		};
	}

}
