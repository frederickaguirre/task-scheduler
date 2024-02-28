package com.leanstacks.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leanstacks.ws.model.ScheduledTask;

@Repository
public interface ScheduledTaskRepository extends JpaRepository<ScheduledTask, Long> {

}
