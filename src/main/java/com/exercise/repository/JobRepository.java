package com.exercise.repository;

import org.springframework.data.repository.CrudRepository;

import com.exercise.entity.*;

public interface JobRepository extends CrudRepository<Job, Long> {

}
