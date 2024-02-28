package com.leanstacks.ws.infraestructure.converters;

import com.leanstacks.ws.domain.model.ScheduledTask;
import com.leanstacks.ws.infraestructure.acl.dto.ScheduledTaskDTO;
import com.leanstacks.ws.infraestructure.shared.RestConverter;

public class ScheduledTaskDTOConverter implements RestConverter<ScheduledTaskDTO, ScheduledTask> {

    @Override
    public ScheduledTask mapToEntity(final ScheduledTaskDTO dto) {
        return new ScheduledTask(dto.getId(), dto.getUrl(), dto.getCronSyntax());
    }

    @Override
    public ScheduledTaskDTO mapToDTO(final ScheduledTask entity) {
        return new ScheduledTaskDTO(entity.getId(), entity.getUrl(), entity.getCronSyntax());
    }
}