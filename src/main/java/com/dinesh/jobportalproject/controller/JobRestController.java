package com.dinesh.jobportalproject.controller;


import com.dinesh.jobportalproject.model.JobPost;
import com.dinesh.jobportalproject.serivce.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {
    @Autowired
    private JobService service;

    @GetMapping(path = "jobPosts", produces = {"application/json"})
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getjob(@PathVariable("postId") int postId){

        return service.getJob(postId);
    }

    @PostMapping(path = "jobPost", consumes = {"application/json", "application/xml"})
    public void addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
         return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJobPost(postId);
        return "deleted... " + postId;
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable String keyword){
        return service.search(keyword);

    }

    @RequestMapping("/")
    public String loadData(){
        service.load();
        return "Success";
    }
}
