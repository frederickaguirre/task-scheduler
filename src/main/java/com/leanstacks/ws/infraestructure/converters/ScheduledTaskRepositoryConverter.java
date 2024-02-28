package com.leanstacks.ws.infraestructure.converters;

import com.leanstacks.ws.domain.model.ScheduledTask;
import com.leanstacks.ws.infraestructure.persistence.entities.ScheduledTaskEntity;
import com.leanstacks.ws.infraestructure.shared.RepositoryConverter;

public class ScheduledTaskRepositoryConverter  implements RepositoryConverter<ScheduledTaskEntity, ScheduledTask> {
    @Override
    public ScheduledTaskEntity mapToTable(final ScheduledTask persistenceObject) {
        return new ScheduledTaskEntity(persistenceObject.getId(), persistenceObject.getUrl(), persistenceObject.getCronSyntax());
    }

    @Override
    public ScheduledTask mapToEntity(final ScheduledTaskEntity entityObject) {
        return new ScheduledTask(entityObject.getId(), entityObject.getUrl(), entityObject.getCronSyntax());
    }
}
