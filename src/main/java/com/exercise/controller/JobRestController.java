package com.exercise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.entity.Job;
import com.exercise.exception.JobNotFoundException;
import com.exercise.repository.JobRepository;

import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping(value = "/job-management", produces = { MediaType.APPLICATION_JSON_VALUE })
public class JobRestController {

	private static final Logger logger = LoggerFactory.getLogger(JobRestController.class);

	private final KafkaTemplate<String, Object> template;
	private final String topicName;

	@Autowired
	private JobRepository jobRepository;

	public JobRestController(final KafkaTemplate<String, Object> template,
			@Value("${job.topic-name:job-queue}") final String topicName) {
		this.template = template;
		this.topicName = topicName;

	}

	public JobRepository getRepository() {
		return jobRepository;
	}

	public void setRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@PostMapping("/jobs")
	Long createJob() {
		Job job = new Job();
		job = jobRepository.save(new Job("New"));

		// Post to kafka
		this.template.send(topicName, job);

		return job.getId();
	}

	@GetMapping("/jobs/{id}")
	Job getJobById(@PathVariable Long id) {
		Job job = new Job();
//		Throw a 404 error if job ID does not exist
		try {
			job = jobRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new JobNotFoundException("id: " + id);
		} catch (Exception e) {
			throw e;
		}
		return job;
	}

	@GetMapping("/jobs")
	List<Job> getAllJobs() {
		List<Job> jobs = new ArrayList<Job>();
		jobRepository.findAll().forEach(job -> jobs.add(job));
		return jobs;
	}

	@KafkaListener(topics = "job-queue", clientIdPrefix = "json", groupId = "job-listener", containerFactory = "kafkaListenerContainerFactory")
	public void jobListener(@Payload Job job) {
		logger.info("Logger 1 [JSON] Payload: {}", job);
		job.setStatus("In progress");
		jobRepository.save(job);

		// Sleep for 1 to 5 seconds
		try {
			Thread.sleep((long) (Math.random() * 1_000 + 5_000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		job.setStatus("Done");
		jobRepository.save(job);
	}

}
