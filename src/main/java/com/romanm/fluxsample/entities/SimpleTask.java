package com.romanm.fluxsample.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SimpleTask {
    private String id;
    private String description;
    private LocalDate created;
    private LocalDate updated;

    public SimpleTask() {
       this.id = UUID.randomUUID().toString();
       LocalDate localDate = LocalDate.now();
       this.created = localDate;
       this.created = localDate;
    }

    public SimpleTask(String description) {
        this();
        this.description = description;
    }
}
