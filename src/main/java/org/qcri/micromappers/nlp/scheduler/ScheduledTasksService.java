package org.qcri.micromappers.nlp.scheduler;

import org.apache.log4j.Logger;
import org.qcri.micromappers.nlp.service.TextDisambiguityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Kushal
 *
 */
@Service
public class ScheduledTasksService
{
	@Autowired
	private TextDisambiguityService textDisambiguityService;

	private static Logger logger = Logger.getLogger(ScheduledTasksService.class);


	@Scheduled(fixedDelay = 10 * 60 * 1000) // 10 minutes - in milliseconds
	private void scheduledTaskProcessTextDisambiguiton() {
		try {
			logger.info("scheduledTaskProcessTextDisambiguiton running after a fixed delay of 10 minutes");
			textDisambiguityService.processText();
		} catch (Exception e) {
			logger.error("Exception while classifying disambiguate text.", e);
		}
	}
}
