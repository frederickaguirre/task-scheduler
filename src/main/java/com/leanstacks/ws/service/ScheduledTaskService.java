package com.leanstacks.ws.service;

import com.leanstacks.ws.model.ScheduledTask;

import java.util.List;

public interface ScheduledTaskService {

    List<ScheduledTask> findAll();

    ScheduledTask create(ScheduledTask scheduledTask);

    void delete(Long id);

    void scheduleATask(Long id, TaskDefinitionBean taskDefinitionBean, String cronSyntax);

    void removeScheduledTask(Long id);

}
