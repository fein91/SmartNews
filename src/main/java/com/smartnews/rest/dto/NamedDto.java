package com.smartnews.rest.dto;

public abstract class NamedDto {
    private final long id;
    private final String name;

    public NamedDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
