package com.leanstacks.ws.domain.service;

import com.leanstacks.ws.domain.model.ScheduledTask;

import java.util.Collection;
import java.util.List;

public interface ScheduledTaskService {

    Collection<ScheduledTask> findAll();

    ScheduledTask create(ScheduledTask scheduledTask);

    void delete(Long id);

}
