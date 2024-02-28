package com.leanstacks.ws.infraestructure.persistence.repositories;

import com.leanstacks.ws.domain.model.ScheduledTask;
import com.leanstacks.ws.domain.repository.ScheduledTaskRepository;
import com.leanstacks.ws.infraestructure.converters.ScheduledTaskRepositoryConverter;
import com.leanstacks.ws.infraestructure.persistence.entities.ScheduledTaskEntity;

import java.util.Collection;
import java.util.stream.Collectors;

public class ScheduledTaskRepositoryImpl implements ScheduledTaskRepository {

    private final ScheduledTaskRepositoryConverter scheduledTaskRepositoryConverter;
    private final ScheduledTaskJpaRepository scheduledTaskJpaRepository;

    public ScheduledTaskRepositoryImpl(ScheduledTaskRepositoryConverter scheduledTaskRepositoryConverter, ScheduledTaskJpaRepository scheduledTaskJpaRepository) {
        this.scheduledTaskRepositoryConverter = scheduledTaskRepositoryConverter;
        this.scheduledTaskJpaRepository = scheduledTaskJpaRepository;
    }

    @Override
    public Collection<ScheduledTask> getAllScheduledTasks() {
        return scheduledTaskJpaRepository.findAll().stream().map(scheduledTaskRepositoryConverter::mapToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduledTask saveScheduledTask(ScheduledTask scheduledTask) {
        ScheduledTaskEntity saved = scheduledTaskJpaRepository.save(scheduledTaskRepositoryConverter.mapToTable(scheduledTask));
        return scheduledTaskRepositoryConverter.mapToEntity(saved);
    }

    @Override
    public void deleteScheduledTask(Long id) {
        scheduledTaskJpaRepository.deleteById(id);

    }
}
