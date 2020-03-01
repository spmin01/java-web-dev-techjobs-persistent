package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.JobData;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping
    public ResponseEntity getJobByEmployer(@RequestParam Optional<String> employer, @RequestParam Optional<List<String>> skills) {

        if(employer.isPresent() && !skills.isPresent()) {
            return new ResponseEntity(JobData.findByValue(employer.get(), jobRepository.findAll()), HttpStatus.OK);

        } else if (!employer.isPresent() && skills.isPresent()) {
            List<Job> foundJobs = new ArrayList<>();
            for (String skill : skills.get()) {
                foundJobs.addAll(JobData.findByValue(skill, jobRepository.findAll()));
            }
            return new ResponseEntity(foundJobs, HttpStatus.OK);

        } else if(employer.isPresent() && skills.isPresent()) {
            List<Job> employerJobs = JobData.findByValue(employer.get(), jobRepository.findAll());
            List<Job> jobsMatchingSkills = new ArrayList<>();

            for (String skill : skills.get()) {
                jobsMatchingSkills.addAll(JobData.findByValue(skill, employerJobs));
            }
            return new ResponseEntity(jobsMatchingSkills, HttpStatus.OK);

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job postJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteJobById(@PathVariable("id") int id) {
        Optional<Job> job = jobRepository.findById(id);
        if(!job.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            jobRepository.delete(job.get());
            return new ResponseEntity(HttpStatus.OK);
        }
    }


}