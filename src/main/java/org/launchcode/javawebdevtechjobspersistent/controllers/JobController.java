package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.hibernate.validator.constraints.pl.REGON;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/{id}")
    public ResponseEntity getJobById(@PathVariable("id") int id) {
        if(jobRepository.findById(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(jobRepository.findById(id), HttpStatus.OK);
        }

    }

}