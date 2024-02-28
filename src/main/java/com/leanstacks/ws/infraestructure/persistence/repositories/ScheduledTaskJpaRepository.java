package com.leanstacks.ws.infraestructure.persistence.repositories;

import com.leanstacks.ws.infraestructure.persistence.entities.ScheduledTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledTaskJpaRepository extends JpaRepository<ScheduledTaskEntity, Long> {

}
