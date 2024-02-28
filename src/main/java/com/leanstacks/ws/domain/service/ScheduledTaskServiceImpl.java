package com.leanstacks.ws.domain.service;

import com.leanstacks.ws.domain.model.ScheduledTask;
import com.leanstacks.ws.domain.repository.ScheduledTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskServiceImpl.class);
    private final ScheduledTaskRepository repository;
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    private final TaskScheduler taskScheduler;

    private TaskDefinition taskDefinition;

    @Autowired
    public ScheduledTaskServiceImpl(ScheduledTaskRepository repository, TaskScheduler taskScheduler, TaskDefinition taskDefinition) {
        this.repository = repository;
        this.taskScheduler = taskScheduler;
        this.taskDefinition = taskDefinition;
    }

    @Override
    public Collection<ScheduledTask> findAll() {
        logger.info("> findAll");
        return repository.getAllScheduledTasks();
    }

    @Transactional
    @Override
    public ScheduledTask create(ScheduledTask scheduledTask) {
        logger.info("> create");
        ScheduledTask savedScheduledTask = repository.saveScheduledTask(scheduledTask);
        scheduleATask(savedScheduledTask);
        logger.info("< create");
        return savedScheduledTask;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("> delete");
        repository.deleteScheduledTask(id);
        removeScheduledTask(id);
        logger.info("< delete");
    }

    private void scheduleATask(ScheduledTask scheduledTask) {
        taskDefinition.setScheduledTask(scheduledTask);

        logger.info("Scheduling task with job id: " + scheduledTask.getId() + " and cron expression: " + scheduledTask.getCronSyntax());

        ScheduledFuture<?> scheduledTaskResult = taskScheduler.schedule(taskDefinition, new CronTrigger(scheduledTask.getCronSyntax(), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(String.valueOf(scheduledTask.getId()), scheduledTaskResult);
    }

    private void removeScheduledTask(Long id) {
        logger.info("removing a Scheduled Task with id: " + id);
        String jobId = String.valueOf(id);
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
