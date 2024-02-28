package com.leanstacks.ws.infraestructure.acl.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ScheduledTaskDTO implements Serializable {

    private Long id;
    private String url;
    private String cronSyntax;

    public ScheduledTaskDTO() {
    }

    public ScheduledTaskDTO(Long id, String url, String cronSyntax) {
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
