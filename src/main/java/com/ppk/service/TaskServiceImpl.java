package com.ppk.service;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

	@Override
	public String execute() {
		try {
			Thread.sleep(5000);
			System.out.println("Slow task executed");
			return "Task finished";
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

}