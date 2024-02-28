package com.leanstacks.ws.infraestructure.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class ScheduledTaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String url;
    @NotNull
    private String cronSyntax;

    public ScheduledTaskEntity() {

    }

    public ScheduledTaskEntity(Long id, String url, String cronSyntax) {
        this.id = id;
        this.url = url;
        this.cronSyntax = cronSyntax;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getCronSyntax() {
        return cronSyntax;
    }
}
