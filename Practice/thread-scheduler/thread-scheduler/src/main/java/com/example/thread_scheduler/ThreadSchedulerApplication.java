package com.example.thread_scheduler;

import com.example.thread_scheduler.service.OrderService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
public class ThreadSchedulerApplication {

	@Autowired
	private OrderService orderLoggingService;

	public static void main(String[] args) {
		SpringApplication.run(ThreadSchedulerApplication.class, args);
	}

	@PostConstruct
	public void startOrderLogging() {
		orderLoggingService.startLoggingOrdersPeriodically();
	}

	// Shut down the thread pool when the application shuts down
	@PreDestroy
	public void stopOrderLogging() {
		orderLoggingService.shutdownExecutor();
	}


}
