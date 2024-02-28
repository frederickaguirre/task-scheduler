package com.leanstacks.ws.service;

import com.leanstacks.ws.model.ScheduledTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TaskDefinitionBean implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(TaskDefinitionBean.class);
    private ScheduledTask scheduledTask;

    @Override
    public void run() {
        logger.info("Running Scheduled task url "+scheduledTask.getUrl()
        +" cronSyntax:" +scheduledTask.getCronSyntax());
        try {
            String result = PingWebSiteService.getStatus(scheduledTask.getUrl());
            logger.info("Result ping url: " + result);
        } catch (IOException e) {
            logger.error("Error ping url: "+scheduledTask.getUrl());
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
