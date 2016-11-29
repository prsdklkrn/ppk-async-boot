package com.ppk.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.ppk.service.TaskService;

@RestController
public class AsyncDeferredController {
	private final TaskService taskService;

	@Autowired
	public AsyncDeferredController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/deferred", method = RequestMethod.GET, produces = "text/html")
	public DeferredResult<String> executeSlowTask() {
		System.out.println("Request received");
		DeferredResult<String> deferredResult = new DeferredResult<>();
		CompletableFuture.supplyAsync(taskService::execute)
				.whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
		System.out.println("Servlet thread released");
		return deferredResult;
	}
}