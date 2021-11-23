package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Job;
import com.example.repository.JobRepository;



//import com.example.entity.Student;

@SpringBootApplication
public class SpringKafkaPostgresApplication implements CommandLineRunner {

	@Autowired
	JobRepository jobRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaPostgresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		jobRepository.save(new Job("Started"));
//
//		jobRepository.findById(1l).ifPresent(x -> System.out.println(x.getStatus()));

	}

}
