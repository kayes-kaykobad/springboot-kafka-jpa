package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Job;
import com.example.repository.JobRepository;

@RestController
@RequestMapping(value = "/job-management", produces = { MediaType.APPLICATION_JSON_VALUE })
public class JobRestController {
	@Autowired
    private JobRepository jobRepository;
	
	public JobRepository getRepository() {
        return jobRepository;
    }
 
    public void setRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    @PostMapping("/jobs")
    Long createJob() {
    	Job job =new Job();
        job = jobRepository.save(new Job("New"));
        return job.getId(); 
    }
 
    @GetMapping("/jobs/{id}")
    String getJobById(@PathVariable Long id) {
    	Job job =new Job();
        job = jobRepository.findById(id).get();
        return job.getStatus();
    }
}
