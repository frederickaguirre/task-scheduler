package com.leanstacks.ws.domain.repository;

import com.leanstacks.ws.domain.model.ScheduledTask;

import java.util.Collection;

public interface ScheduledTaskRepository {

    Collection<ScheduledTask> getAllScheduledTasks();

    ScheduledTask saveScheduledTask(ScheduledTask scheduledTask);

    void deleteScheduledTask(Long id);

}
