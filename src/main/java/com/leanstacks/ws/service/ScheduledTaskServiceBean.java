package com.leanstacks.ws.service;

import com.leanstacks.ws.model.ScheduledTask;
import com.leanstacks.ws.repository.ScheduledTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduledTaskServiceBean implements ScheduledTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskServiceBean.class);
    private final ScheduledTaskRepository repository;
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();
    @Autowired
    private final TaskScheduler taskScheduler;

    @Autowired
    public ScheduledTaskServiceBean(ScheduledTaskRepository repository, TaskScheduler taskScheduler) {
        this.repository = repository;
        this.taskScheduler = taskScheduler;
    }


    @Override
    public List<ScheduledTask> findAll() {
        logger.info("> findAll");
        List<ScheduledTask> scheduledTasks = repository.findAll();
        logger.info("< findAll");
        return scheduledTasks;
    }

    @Transactional
    @Override
    public ScheduledTask create(ScheduledTask scheduledTask) {
        logger.info("> create");
        ScheduledTask savedScheduledTask = repository.save(scheduledTask);
        logger.info("< create");
        return savedScheduledTask;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        logger.info("> delete");
        repository.deleteById(id);
        logger.info("< delete");
    }

    @Override
    public void scheduleATask(Long jobId, TaskDefinitionBean tasklet, String cronExpression) {
        logger.info("Scheduling task with job id: " + jobId + " and cron expression: " + cronExpression);
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(tasklet, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(String.valueOf(jobId), scheduledTask);
    }

    @Override
    public void removeScheduledTask(Long id) {
        logger.info("removing a Scheduled Task with id: " + id);
        String jobId = String.valueOf(id);
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }
}
