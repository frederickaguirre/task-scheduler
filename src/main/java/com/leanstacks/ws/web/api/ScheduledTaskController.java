package com.leanstacks.ws.web.api;

import com.leanstacks.ws.model.ScheduledTask;
import com.leanstacks.ws.service.ScheduledTaskService;
import com.leanstacks.ws.service.TaskDefinitionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class ScheduledTaskController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskController.class);

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    @GetMapping
    public List<ScheduledTask> getScheduledTasks() {
        logger.info("> getScheduledTasks");
        List<ScheduledTask> scheduledTasks = scheduledTaskService.findAll();
        logger.info("< getScheduledTasks");

        return scheduledTasks;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduledTask createScheduledTask(@RequestBody final ScheduledTask scheduledTask) {

        //TODO: dejar la persistencia y la programación en una sola transaccion

        logger.info("> createScheduledTasks");
        taskDefinitionBean.setScheduledTask(scheduledTask);
        ScheduledTask savedScheduledTask = scheduledTaskService.create(scheduledTask);
        logger.info("- createScheduledTasks saved");
        scheduledTaskService.scheduleATask(savedScheduledTask.getId(), taskDefinitionBean, scheduledTask.getCronSyntax());
        logger.info("- createScheduledTasks scheduled");

        logger.info("< createScheduledTasks");

        return savedScheduledTask;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduledTask(@PathVariable("id") final Long id) {
        //TODO: dejar la persistencia y la programación en una sola transaccion

        scheduledTaskService.delete(id);
        scheduledTaskService.removeScheduledTask(id);
    }

}
