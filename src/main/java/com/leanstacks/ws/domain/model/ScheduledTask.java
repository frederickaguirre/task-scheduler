package com.leanstacks.ws.domain.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class ScheduledTask implements Serializable {

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
