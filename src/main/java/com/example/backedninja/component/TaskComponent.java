package com.example.backedninja.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")
public class TaskComponent {
	
	private static final Log logger = LogFactory.getLog(TaskComponent.class);
	
	// Este metodo se va a estar repitiendo todo el rato
	@Scheduled(fixedDelay = 5000)
	public void doTask() {
		logger.info("TIME IS: " + new Date());
	}

}
