package com.ppk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ppk.service.TaskService;

@RestController
public class BlockingController {
	private final TaskService taskService;

	@Autowired
	public BlockingController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/block", method = RequestMethod.GET, produces = MediaType.APPLICATION_XHTML_XML_VALUE)
	public String executeSlowTask() {
		System.out.println("Request received");
		String result = taskService.execute();
		System.out.println("Servlet thread released");
		return result;
	}
}