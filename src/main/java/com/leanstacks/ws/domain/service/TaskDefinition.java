package com.leanstacks.ws.domain.service;

import com.leanstacks.ws.domain.model.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TaskDefinition implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TaskDefinition.class);
    private ScheduledTask scheduledTask;

    @Override
    public void run() {
        logger.info("Running Scheduled task with id: " + scheduledTask.getId() + "url: " + scheduledTask.getUrl()
                + ", and cronSyntax:" + scheduledTask.getCronSyntax());
        try {
            String result = PingWebSiteService.getStatus(scheduledTask.getUrl());
            logger.info("Result Scheduled task: " + result);
        } catch (IOException e) {
            logger.error("Error ping url: " + scheduledTask.getUrl());
            logger.error(e.getMessage());
        }
    }

    public ScheduledTask getScheduledTask() {
        return scheduledTask;
    }

    public void setScheduledTask(ScheduledTask scheduledTask) {
        this.scheduledTask = scheduledTask;
    }
}
