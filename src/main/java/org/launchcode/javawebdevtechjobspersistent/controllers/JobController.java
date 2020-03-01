package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.JobData;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping
    public ResponseEntity getJobByEmployer(@RequestParam Optional<String> employer) {
        if(employer.isPresent()) {
            return new ResponseEntity(JobData.findByValue(employer.get(), jobRepository.findAll()), HttpStatus.OK);
        } else {
            return new ResponseEntity(jobRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getJobById(@PathVariable("id") int id) {
        if(jobRepository.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(jobRepository.findById(id), HttpStatus.OK);
        }

    }

}