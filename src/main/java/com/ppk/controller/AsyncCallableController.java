package com.ppk.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ppk.service.TaskService;

@RestController
public class AsyncCallableController {
    private final TaskService taskService;
    
    @Autowired
    public AsyncCallableController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @RequestMapping(value = "/callable", method = RequestMethod.GET, produces = "text/html")
    public Callable<String> executeSlowTask() {
    	System.out.println("Request received");
        Callable<String> callable = taskService::execute;
        System.out.println("Servlet thread released");
        return callable;
    }
}