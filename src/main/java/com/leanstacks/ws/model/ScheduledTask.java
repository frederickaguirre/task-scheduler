package com.leanstacks.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class ScheduledTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String url;
    @NotNull
    private String cronSyntax;

    public ScheduledTask() {

    }

    public ScheduledTask(Long id, String url, String cronSyntax) {
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
