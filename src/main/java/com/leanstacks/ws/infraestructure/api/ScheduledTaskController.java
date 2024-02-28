package com.leanstacks.ws.infraestructure.api;

import com.leanstacks.ws.domain.service.ScheduledTaskService;
import com.leanstacks.ws.infraestructure.acl.dto.ScheduledTaskDTO;
import com.leanstacks.ws.infraestructure.converters.ScheduledTaskDTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class ScheduledTaskController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskController.class);
    private final ScheduledTaskService scheduledTaskService;
    private final ScheduledTaskDTOConverter scheduledTaskDTOConverter;

    @Autowired
    public ScheduledTaskController(ScheduledTaskService scheduledTaskService, ScheduledTaskDTOConverter scheduledTaskDTOConverter) {
        this.scheduledTaskService = scheduledTaskService;
        this.scheduledTaskDTOConverter = scheduledTaskDTOConverter;
    }

    @GetMapping
    public Collection<ScheduledTaskDTO> getScheduledTasks() {
        logger.info("> getScheduledTasks");
        return scheduledTaskService.findAll().stream().map(scheduledTaskDTOConverter::mapToDTO)
                .collect(Collectors.toList());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduledTaskDTO createScheduledTask(@RequestBody final ScheduledTaskDTO dto) {
        logger.info("> createScheduledTasks");
        return scheduledTaskDTOConverter.mapToDTO(scheduledTaskService.create(scheduledTaskDTOConverter.mapToEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduledTask(@PathVariable("id") final Long id) {
        logger.info("> delete ScheduledTasks");
        scheduledTaskService.delete(id);
    }

}
