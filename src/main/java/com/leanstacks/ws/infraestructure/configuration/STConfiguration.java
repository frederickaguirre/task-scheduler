package com.leanstacks.ws.infraestructure.configuration;

import com.leanstacks.ws.infraestructure.converters.ScheduledTaskDTOConverter;
import com.leanstacks.ws.infraestructure.converters.ScheduledTaskRepositoryConverter;
import com.leanstacks.ws.infraestructure.persistence.repositories.ScheduledTaskJpaRepository;
import com.leanstacks.ws.infraestructure.persistence.repositories.ScheduledTaskRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class STConfiguration {

    @Autowired
    ScheduledTaskJpaRepository repository;

    @Bean
    ScheduledTaskRepositoryImpl createScheduledTaskRepositoryImpl() {
        return new ScheduledTaskRepositoryImpl(new ScheduledTaskRepositoryConverter(), repository);
    }

    @Bean
    ScheduledTaskDTOConverter createScheduledTaskDTOConverter() {
        return new ScheduledTaskDTOConverter();
    }
}
